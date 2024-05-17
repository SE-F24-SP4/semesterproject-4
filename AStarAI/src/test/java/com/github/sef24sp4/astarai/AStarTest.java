package com.github.sef24sp4.astarai;

import com.github.sef24sp4.common.ai.map.MapNode;
import com.github.sef24sp4.common.ai.map.NotAdjacentNodeException;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.BasicVector;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.vector.IVector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AStarTest {

	@Mock
	private MapNode startMapNode;
	@Mock
	private MapNode goalMapNode;
	@Mock
	private MapNode node01;
	private IVector node01Center;
	@Mock
	private MapNode node02;
	private IVector node02Center;
	@Mock
	private MapNode node12;
	private IVector node12Center;
	@Mock
	private MapNode node11; //invalid
	private IVector node11Center;
	@Mock
	private ICollidableEntity entity;

	private Coordinates startCoordinates;
	private Coordinates goalCoordinates;


	@BeforeEach
	void setUp() {
		this.startCoordinates = new Coordinates(0, 0);
		this.node01Center = new BasicVector(0, 1);
		this.node02Center = new BasicVector(0, 2);
		this.node11Center = new BasicVector(1, 1);
		this.node12Center = new BasicVector(1, 2);
		this.goalCoordinates = new Coordinates(2, 2);
	}

	@Test
	@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
	void calculatePathTest() throws NotAdjacentNodeException {

		when(this.startMapNode.getNeighboringNodes()).thenReturn(Set.of(this.node01, this.node11));
		lenient().when(this.startMapNode.calculateHeuristicsFor(this.goalMapNode)).thenReturn(Math.sqrt(2) + Math.sqrt(2));
		when(this.startMapNode.getCenterCoordinates()).thenReturn(this.startCoordinates);

		//node01
		when(this.node01.getSafeCoordinatesForEntity(this.entity, this.startCoordinates)).thenReturn(Optional.of(this.node01Center));
		when(this.node01.getNeighboringNodes()).thenReturn(Set.of(this.node02, this.node11, this.node12, this.startMapNode));
		when(this.node01.calculateHeuristicsFor(this.goalMapNode)).thenReturn((Math.sqrt(3)));
		when(this.node01.getCenterCoordinates()).thenReturn(this.node01Center);

		//node02
		when(this.node02.getSafeCoordinatesForEntity(this.entity, this.node01Center)).thenReturn(Optional.of(this.node02Center));
		lenient().when(this.node02.getSafeCoordinatesForEntity(this.entity, this.startCoordinates)).thenThrow(new NotAdjacentNodeException());
		lenient().when(this.node02.getNeighboringNodes()).thenReturn(Set.of(this.node01, this.node11, this.node12));
		when(this.node02.calculateHeuristicsFor(this.goalMapNode)).thenReturn(2.0);
		when(this.node02.getCenterCoordinates()).thenReturn(this.node02Center);

		//node11
		when(this.node11.getSafeCoordinatesForEntity(eq(this.entity), any())).thenReturn(Optional.empty());
		when(this.node11.getCenterCoordinates()).thenReturn(this.node11Center);


		//node12
		when(this.node12.getSafeCoordinatesForEntity(this.entity, this.node01Center)).thenReturn(Optional.of(this.node12Center));
		when(this.node12.getNeighboringNodes()).thenReturn(Set.of(this.node01, this.node02, this.node11, this.goalMapNode));
		when(this.node12.calculateHeuristicsFor(this.goalMapNode)).thenReturn(1.0);
		when(this.node12.getCenterCoordinates()).thenReturn(this.node12Center);

		//goalNode
		lenient().when(this.goalMapNode.getSafeCoordinatesForEntity(this.entity, this.node12Center)).thenReturn(Optional.of(this.goalCoordinates));
		when(this.goalMapNode.getCenterCoordinates()).thenReturn(this.goalCoordinates);

		//making Nodes to use in AStar
		Node entityStartNode = new Node(this.startCoordinates, this.startMapNode);
		Node goalNode = new Node(this.goalCoordinates, this.goalMapNode);

		AStar aStar = new AStar(entityStartNode, goalNode);
		List<Node> path = aStar.calculatePath(this.entity);

		assertSame(path.get(0).getMapNode(), this.node01);
		assertSame(path.get(1).getMapNode(), this.node12);
		assertSame(path.get(2).getMapNode(), this.goalMapNode);
	}

	@Test
	void hasSameMapNodeTest() {

		Node node1 = new Node(new Coordinates(0, 0), this.node01);
		Node node2 = new Node(new Coordinates(0, 0), this.node01);

		assert (node1.hasSameMapNode(node2));
	}

	@Test
	void getNeighboringNodes() {
		Node currentNode = new Node(this.startCoordinates, this.startMapNode);
		when(this.startMapNode.getNeighboringNodes()).thenReturn(Set.of(this.node01, this.node11));
		when(this.node01.getCenterCoordinates()).thenReturn(new BasicVector(0, 1));
		when(this.node11.getCenterCoordinates()).thenReturn(new BasicVector(1, 1));

		Collection<Node> neighboringNodes = currentNode.getNeighboringNodesFromMapNode();

		assertTrue(neighboringNodes.stream().anyMatch(node -> node.getCoordinates().equals(this.node01.getCenterCoordinates())));
		assertFalse(neighboringNodes.stream().anyMatch(node -> node.getCoordinates().equals(this.node02.getCenterCoordinates())));
	}

}







