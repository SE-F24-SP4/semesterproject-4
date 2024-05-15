package com.github.sef24sp4.core.javafxbindings;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.core.interfaces.EntityToGraphicsMapper;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Maps {@link IEntity} to {@link Polygon JavaFX's Polygon} objects.
 *
 * @see EntityToGraphicsMapper
 */
public class EntityToJavaFxMapper implements EntityToGraphicsMapper<IEntity, Polygon> {
	private final Map<IEntity, Polygon> map = new HashMap<>();
	private final List<Node> graphicsNodes;

	public EntityToJavaFxMapper(final List<Node> graphicsNodes) {
		this.graphicsNodes = graphicsNodes;
	}

	@Override
	public boolean add(final IEntity entity) {
		Polygon polygon = new Polygon(entity.getPolygonValuesArray());
		this.map.put(entity, polygon);
		return this.graphicsNodes.add(polygon);
	}

	@Override
	public boolean remove(final IEntity entity) {
		final Node polygon = this.map.remove(entity);
		if (polygon != null) return this.graphicsNodes.remove(polygon);
		return false;
	}

	@Override
	public Map<IEntity, Polygon> getMap() {
		return this.map;
	}

	@Override
	public void render() {
		this.map.forEach(((entity, polygon) -> {
			polygon.setTranslateX(entity.getX());
			polygon.setTranslateY(entity.getY());
			polygon.setRotate(Math.toDegrees(entity.getRotation()));
		}));
	}
}
