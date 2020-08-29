package by.artempvn.task01.service;

import by.artempvn.task01.entity.Point;
import by.artempvn.task01.exception.CustomException;

public class MatrixService {

	private static final int NUMBER_POINTS = 4;

	public double calculateDeterminantOrder3(Point[] points)
			throws CustomException {
		if (points == null || points.length != NUMBER_POINTS) {
			throw new CustomException("incorrect input");
		}
		double[][] matrix = createMatrixByPoints(points);
		double determinant = (matrix[0][0] * matrix[1][1] * matrix[2][2])
				+ (matrix[0][1] * matrix[1][2] * matrix[2][0])
				+ (matrix[0][2] * matrix[1][0] * matrix[2][1])
				- (matrix[0][2] * matrix[1][1] * matrix[2][0])
				- (matrix[0][0] * matrix[1][2] * matrix[2][1])
				- (matrix[0][1] * matrix[1][0] * matrix[2][2]);
		return determinant;
	}

	private double[][] createMatrixByPoints(Point[] points) {
		double[][] matrix = {
				{ points[1].getCoordinateX() - points[0].getCoordinateX(),
						points[1].getCoordinateY() - points[0].getCoordinateY(),
						points[1].getCoordinateZ()
								- points[0].getCoordinateZ() },
				{ points[2].getCoordinateX() - points[0].getCoordinateX(),
						points[2].getCoordinateY() - points[0].getCoordinateY(),
						points[2].getCoordinateZ()
								- points[0].getCoordinateZ() },
				{ points[3].getCoordinateX() - points[0].getCoordinateX(),
						points[3].getCoordinateY() - points[0].getCoordinateY(),
						points[3].getCoordinateZ()
								- points[0].getCoordinateZ() } };
		return matrix;
	}
}
