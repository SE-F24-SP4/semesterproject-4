package com.github.sef24sp4.common.ai.map;

/**
 * May be thrown by
 * {@link MapNode#getSafeCoordinatesForEntity(com.github.sef24sp4.common.entities.ICollidableEntity, com.github.sef24sp4.common.vector.IVector)}
 * if required by the implementation.
 */
public class NotAdjacentNodeException extends Exception {
	public NotAdjacentNodeException(final String message) {
		super(message);
	}

	public NotAdjacentNodeException() {
		this("Not adjacent node");
	}

	/**
	 * Creates an exception with a default message given the two arguments.
	 * <p/>
	 * The default message has the following format. "<i>node.toString() is not Adjacent to the current node (currentNode.toString())</i>"
	 *
	 * @param currentNode The current node.
	 * @param node        The node which was not adjacent to {@code currentNode}.
	 */
	public NotAdjacentNodeException(final MapNode currentNode, final MapNode node) {
		this(String.format("%s is not Adjacent to the current node (%s)", node, currentNode));
	}

	public NotAdjacentNodeException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
