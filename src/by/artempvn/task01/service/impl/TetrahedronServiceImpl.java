package by.artempvn.task01.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.task01.entity.Point;
import by.artempvn.task01.entity.Tetrahedron;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.service.GeometryService;
import by.artempvn.task01.service.MatrixService;
import by.artempvn.task01.service.Shape3DService;

public class TetrahedronServiceImpl implements Shape3DService<Tetrahedron> {
	// by task base is parallel to some coordinate plane, in this case it's 0XY
	private final static Logger logger = LogManager
			.getLogger(TetrahedronServiceImpl.class);
	private static final int NUMBER_BASE_POINTS = 3;
	private static final int NUMBER_OF_POINTS = 4;

	@Override
	public double calculateSquareSurface(Tetrahedron shape)
			throws CustomException {
		if (shape == null) {
			throw new CustomException("null data");
		}
		Point[] points = takePointsArray(shape);
		GeometryService geometryService = new GeometryService();
		double square = 0;
		square += geometryService.calculateTriangleSquare(points[0], points[1],
				points[2]);
		square += geometryService.calculateTriangleSquare(points[0], points[1],
				points[3]);
		square += geometryService.calculateTriangleSquare(points[1], points[2],
				points[3]);
		square += geometryService.calculateTriangleSquare(points[0], points[2],
				points[3]);
		logger.log(Level.DEBUG,
				"Square of id = " + shape.getId() + " is " + square);
		return square;
	}

	@Override
	public double calculatePerimeter(Tetrahedron shape) throws CustomException {
		if (shape == null) {
			throw new CustomException("null data");
		}
		double perimeter = 0;
		Point[] points = takePointsArray(shape);
		GeometryService service = new GeometryService();
		perimeter = service.calculateLengthBetweenPoints(points[0], points[1])
				+ service.calculateLengthBetweenPoints(points[0], points[2])
				+ service.calculateLengthBetweenPoints(points[0], points[3])
				+ service.calculateLengthBetweenPoints(points[1], points[2])
				+ service.calculateLengthBetweenPoints(points[1], points[3])
				+ service.calculateLengthBetweenPoints(points[2], points[3]);
		logger.log(Level.DEBUG,
				"Perimeter of id = " + shape.getId() + " is " + perimeter);
		return perimeter;
	}

	@Override
	public double calculateVolume(Tetrahedron shape) throws CustomException {
		if (shape == null) {
			throw new CustomException("null data");
		}
		Point[] points = takePointsArray(shape);
		MatrixService matrixService = new MatrixService();
		double determinant = matrixService.calculateDeterminantOrder3(points);
		double volume = Math.abs(1 / 6. * determinant);
		logger.log(Level.DEBUG,
				"Volume of id = " + shape.getId() + " is " + volume);
		return volume;
	}

	@Override
	public double calculateRatioOfVolumesSeparatedByPlaneParallelXY(
			Tetrahedron shape, double coordinateZ) throws CustomException {
		if (shape == null) {
			throw new CustomException("null data");
		}
		Point[] points = takePointsArray(shape);
		Point[] sortedPoints = placeApexPointOnZeroPosition(points);
		GeometryService geometryService = new GeometryService();
		Optional<Point> a = geometryService
				.calculatePointIntersectionLinePlaneParallelXY(sortedPoints[0],
						sortedPoints[1], coordinateZ);
		Optional<Point> b = geometryService
				.calculatePointIntersectionLinePlaneParallelXY(sortedPoints[0],
						sortedPoints[2], coordinateZ);
		Optional<Point> c = geometryService
				.calculatePointIntersectionLinePlaneParallelXY(sortedPoints[0],
						sortedPoints[3], coordinateZ);
		double ratio = 0;
		if (a.isPresent()) {
			Set<Point> pointsOfSeparatedShape = new HashSet<Point>();
			List<Point> pointsList = Arrays.asList(sortedPoints[0], a.get(),
					b.get(), c.get());
			pointsOfSeparatedShape.addAll(pointsList);
			Tetrahedron separatedTetrahedron = new Tetrahedron(0,
					pointsOfSeparatedShape);
			double separatedTetrahedronVolume = calculateVolume(
					separatedTetrahedron);
			double anotherPartVolume = calculateVolume(shape)
					- separatedTetrahedronVolume;
			ratio = separatedTetrahedronVolume / anotherPartVolume;
		}
		logger.log(Level.DEBUG, "Ratio of id = " + shape.getId()
				+ " and coordinate Z = " + coordinateZ + " is " + ratio);
		return ratio;
	}

	@Override
	public boolean isCorrectShape(Set<Point> somePoints)
			throws CustomException {
		if (somePoints == null) {
			throw new CustomException("null data");
		}
		Point[] points = new Point[NUMBER_OF_POINTS];
		if (somePoints.size() == NUMBER_OF_POINTS) {
			somePoints.toArray(points);
		} else {
			return false;
		}
		GeometryService service = new GeometryService();
		boolean isCorrectShape = !service.isThreePointsOnLine(points[0],
				points[1], points[2])
				&& !service.isThreePointsOnLine(points[0], points[1], points[3])
				&& !service.isThreePointsOnLine(points[0], points[2], points[3])
				&& !service.isThreePointsOnLine(points[1], points[2],
						points[3]);
		return isCorrectShape;
	}

	public boolean isBaseOnCoordinatePlane(Tetrahedron shape)
			throws CustomException {
		if (shape == null) {
			throw new CustomException("null data");
		}
		Point[] points = placeApexPointOnZeroPosition(takePointsArray(shape));
		boolean isBaseOnCoordinatePlane = (points[1].getCoordinateZ() == 0
				&& points[2].getCoordinateZ() == 0
				&& points[3].getCoordinateZ() == 0);
		return isBaseOnCoordinatePlane;
	}

	private Point[] takePointsArray(Tetrahedron shape) {
		Set<Point> pointsAsSet = shape.getPointsReadOnly();
		Point[] points = new Point[NUMBER_OF_POINTS];
		pointsAsSet.toArray(points);
		return points;
	}

	private Point[] placeApexPointOnZeroPosition(Point[] points)
			throws CustomException {
		if (!isBaseParallelXY(points)) {
			throw new CustomException(
					"Base is not parallel to OXY. Can't calculate futher action.");
		}
		Point[] sortedPoints = new Point[NUMBER_OF_POINTS];
		boolean isFound = false;
		int i = 0;
		while (!isFound && i < points.length - 1) {
			if (points[i].getCoordinateZ() == points[i + 1].getCoordinateZ()) {
				i++;
			} else if (i > 0) {
				i++;
				sortedPoints[0] = points[i];
				isFound = true;
			} else {
				if (points[i].getCoordinateZ() == points[i + 2]
						.getCoordinateZ()) {
					i++;
					sortedPoints[0] = points[i];
					isFound = true;
				} else {
					sortedPoints[0] = points[i];
					isFound = true;
				}
			}
		}
		for (int j = 1, k = 0; j < sortedPoints.length; k++) {
			if (k != i) {
				sortedPoints[j] = points[k];
				j++;
			}
		}
		return sortedPoints;
	}

	private boolean isBaseParallelXY(Point[] points) {
		double baseCoordinateZ;
		if (points[0] == points[1]) {
			baseCoordinateZ = points[0].getCoordinateZ();
		} else if (points[0] == points[2]) {
			baseCoordinateZ = points[0].getCoordinateZ();
		} else {
			baseCoordinateZ = points[1].getCoordinateZ();
		}
		int count = 0;
		for (Point point : points) {
			if (point.getCoordinateZ() == baseCoordinateZ) {
				count++;
			}
		}
		return (count == NUMBER_BASE_POINTS);
	}
}
