#!/bin/sh
set -eu
SCRIPT_DIR="$(realpath "$(dirname -- "$0")")"; readonly SCRIPT_DIR;
print() { printf "%b%b" "${1-""}" "${2-"\\n"}"; }
stderr() { print "$@" 1>&2; }
reportError() { stderr "$2"; return "$1"; }

commandv() { command -v "$1" || reportError "$?" "Executable '$1' not found"; }


GIT_KEEP_FILENAME=".gitkeep"
GIT_WORKDIR="$(git rev-parse --show-toplevel)"

TEMPLATE="TEMPLATE_1"
TEMPLATE_KEYWORD="$TEMPLATE"
TEMPLATE_PATH="$SCRIPT_DIR/templates/$TEMPLATE"
TEMPLATE_GROUP_PATH="com/github/sef24sp4"

COMMON_NAME="Common"


NEW_MODULE_NAME="$1"

NEW_MODULE_PATH="$GIT_WORKDIR/$NEW_MODULE_NAME"

NEW_MODULE_PATH_SUFFIX="$(printf "%s" "$NEW_MODULE_NAME" | sed -E "s/^(${COMMON_NAME})/\1\//g;s/\/\$//g" | tr '[:upper:]' '[:lower:]')"

NEW_MODULE_CLASS_PATH="$TEMPLATE_GROUP_PATH/$NEW_MODULE_PATH_SUFFIX"

FULL_MAIN_MODULE_PATH="$NEW_MODULE_PATH/src/main/java/$NEW_MODULE_CLASS_PATH"
FULL_RESOURCES_MODULE_PATH="$NEW_MODULE_PATH/src/main/resources/$NEW_MODULE_CLASS_PATH"
FULL_TEST_MODULE_PATH="$NEW_MODULE_PATH/src/test/java/$NEW_MODULE_CLASS_PATH"




if [ -e "$NEW_MODULE_PATH" ]; then
	stderr "Skipping: $NEW_MODULE_NAME"
	exit 0
fi


stderr "Create: $NEW_MODULE_NAME"



cp -r "$TEMPLATE_PATH" "$NEW_MODULE_PATH"

# https://askubuntu.com/a/1191637
DEV_NULL="/dev/null"
for _i in "$FULL_MAIN_MODULE_PATH" "$FULL_RESOURCES_MODULE_PATH" "$FULL_TEST_MODULE_PATH"; do
	install -D "$DEV_NULL" "$_i/$GIT_KEEP_FILENAME"
done

find "$NEW_MODULE_PATH" -type "f" -exec sed -i "s/$TEMPLATE_KEYWORD/$NEW_MODULE_NAME/g" {} \;

print "<module>$NEW_MODULE_NAME</module>"
