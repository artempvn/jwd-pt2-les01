package by.artempvn.task01.repository.specification;

import java.util.Iterator;
import java.util.Set;
import by.artempvn.task01.entity.Point;
import by.artempvn.task01.entity.Tetrahedron;
import by.artempvn.task01.repository.Specification;

public class ReferencePointDistanceFromOriginSpecification
		implements Specification {

	private double minDistanceFromOrigin;
	private double maxDistanceFromOrigin;

	public ReferencePointDistanceFromOriginSpecification(
			double minDistanceFromOrigin, double maxDistanceFromOrigin) {
		this.minDistanceFromOrigin = minDistanceFromOrigin;
		this.maxDistanceFromOrigin = maxDistanceFromOrigin;
	}

	@Override
	public boolean test(Tetrahedron tetrahedron) {
		Set<Point> points = tetrahedron.getPointsReadOnly();
		boolean isShapePlacedFromOrigin = true;
		Iterator<Point> iterator = points.iterator();
		while (iterator.hasNext() && isShapePlacedFromOrigin) {
			Point point = iterator.next();
			boolean matchX = point.getCoordinateX() >= Math
					.abs(minDistanceFromOrigin)
					&& point.getCoordinateX() <= Math
							.abs(maxDistanceFromOrigin);
			boolean matchY = point.getCoordinateY() >= Math
					.abs(minDistanceFromOrigin)
					&& point.getCoordinateY() <= Math
							.abs(maxDistanceFromOrigin);
			boolean matchZ = point.getCoordinateZ() >= Math
					.abs(minDistanceFromOrigin)
					&& point.getCoordinateZ() <= Math
							.abs(maxDistanceFromOrigin);
			isShapePlacedFromOrigin = matchX && matchY && matchZ;
		}
		return isShapePlacedFromOrigin;
	}
}
