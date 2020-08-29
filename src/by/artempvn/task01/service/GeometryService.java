package by.artempvn.task01.service;

import static java.lang.Math.*;
import java.util.Optional;
import by.artempvn.task01.entity.Point;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.validator.DataValidator;

public class GeometryService {

	public double calculateTriangleSquare(Point a, Point b, Point c)
			throws CustomException {
		if (isThreePointsOnLine(a, b, c)) {
			throw new CustomException("Triangle doesn't exist");
		}
		double sideA = calculateLengthBetweenPoints(a, b);
		double sideB = calculateLengthBetweenPoints(b, c);
		double sideC = calculateLengthBetweenPoints(a, c);
		double halfPerimeter = (sideA + sideB + sideC) / 2;
		double square = sqrt((halfPerimeter - sideA) * (halfPerimeter - sideB)
				* (halfPerimeter - sideC) * halfPerimeter);
		return square;
	}

	public double calculateLengthBetweenPoints(Point a, Point b)
			throws CustomException {
		if (a == null || b == null) {
			throw new CustomException("null input");
		}
		double length = sqrt(pow(a.getCoordinateX() - b.getCoordinateX(), 2)
				+ pow(a.getCoordinateY() - b.getCoordinateY(), 2)
				+ pow(a.getCoordinateZ() - b.getCoordinateZ(), 2));
		return length;
	}

	public Optional<Point> calculatePointIntersectionLinePlaneParallelXY(
			Point a, Point b, double coordinateZ) throws CustomException {
		if (a == null || b == null) {
			throw new CustomException("null input");
		}
		Optional<Point> point = Optional.empty();
		DataValidator validator = new DataValidator();
		if ((validator.isCoordinateZLayBetweenPoints(a, b, coordinateZ))) {
			double equationValue = (coordinateZ - a.getCoordinateZ())
					/ (b.getCoordinateZ() - a.getCoordinateZ());
			double coordinateY = equationValue
					* (b.getCoordinateY() - a.getCoordinateY())
					+ a.getCoordinateY();
			double coordinateX = equationValue
					* (b.getCoordinateX() - a.getCoordinateX())
					+ a.getCoordinateX();
			point = Optional
					.of(new Point(coordinateX, coordinateY, coordinateZ));
		}
		return point;
	}

	public boolean isThreePointsOnLine(Point a, Point b, Point c)
			throws CustomException {
		if (a == null || b == null || c == null) {
			throw new CustomException("null input");
		}
		boolean isThreePointsOnLine = ((c.getCoordinateX() - a.getCoordinateX())
				/ (b.getCoordinateX()
						- a.getCoordinateX()) == (c.getCoordinateY()
								- a.getCoordinateY())
								/ (b.getCoordinateY() - a.getCoordinateY())
				&& (c.getCoordinateX() - a.getCoordinateX())
						/ (b.getCoordinateX() - a.getCoordinateX()) == (c
								.getCoordinateZ() - a.getCoordinateZ())
								/ (b.getCoordinateZ() - a.getCoordinateZ()));
		return isThreePointsOnLine;
	}
}
