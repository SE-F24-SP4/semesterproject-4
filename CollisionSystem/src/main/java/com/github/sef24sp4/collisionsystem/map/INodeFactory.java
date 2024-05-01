package com.github.sef24sp4.collisionsystem.map;

/**
 * A {@link FunctionalInterface} defining a factory method for creating {@link INode} instances.
 */
@FunctionalInterface
public interface INodeFactory {
	/**
	 * The factory method which creates the {@link INode}.
	 *
	 * @param column The column this node is in.
	 * @param row    The row this node is in.
	 * @param parent The containing {@link IGridMap} which this node will be contained in.
	 * @return A newly created instance of {@link INode}.
	 */
	public INode create(final int column, final int row, final IGridMap parent);
}
