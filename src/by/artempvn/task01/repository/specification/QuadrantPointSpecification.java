package by.artempvn.task01.repository.specification;

import java.util.Iterator;
import java.util.Set;
import by.artempvn.task01.entity.Point;
import by.artempvn.task01.entity.Tetrahedron;
import by.artempvn.task01.repository.QuadrantType;
import by.artempvn.task01.repository.Specification;

public class QuadrantPointSpecification implements Specification {

	private QuadrantType quadrant;

	public QuadrantPointSpecification(QuadrantType quadrant) {
		this.quadrant = quadrant;
	}

	@Override
	public boolean test(Tetrahedron tetrahedron) {
		Set<Point> points = tetrahedron.getPointsReadOnly();
		boolean isShapePlacedInQuadrant = true;
		Iterator<Point> iterator = points.iterator();
		while (iterator.hasNext() && isShapePlacedInQuadrant) {
			Point point = iterator.next();
			boolean matchX = point.getCoordinateX() > 0 == quadrant
					.isCoordinateXPositive();
			if (point.getCoordinateX() == 0) {
				matchX = true;
			}
			boolean matchY = point.getCoordinateY() > 0 == quadrant
					.isCoordinateYPositive();
			if (point.getCoordinateY() == 0) {
				matchY = true;
			}
			boolean matchZ = point.getCoordinateZ() > 0 == quadrant
					.isCoordinateZPositive();
			if (point.getCoordinateZ() == 0) {
				matchZ = true;
			}
			isShapePlacedInQuadrant = matchX && matchY && matchZ;
		}
		return isShapePlacedInQuadrant;
	}
}
