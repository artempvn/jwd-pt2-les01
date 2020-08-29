package by.artempvn.task01.validator;

import by.artempvn.task01.entity.Point;

public class DataValidator {

	private static final int MAX_ID = 400;
	private static final int MIN_ID = 1;
	private static final double MAX_COORDINATE_VALUE = 100.;
	private static final String DATA_REGEX =
			"\\d{1,3}\\s+(-?\\d{1,3}(\\.\\d{1,3})?\\b\\s*){12}";

	public boolean isCorrectCoordinate(double coordinate) {
		return (Math.abs(coordinate) <= MAX_COORDINATE_VALUE);
	}

	public boolean isCorrectId(long id) {
		return (id >= MIN_ID && id <= MAX_ID);
	}

	public boolean isCorrectData(String data) {
		return (data != null && data.matches(DATA_REGEX));
	}

	public boolean isCoordinateZLayBetweenPoints(Point a, Point b,
			double coordinateZ) {
		if (a == null || b == null) {
			return false;
		}
		boolean isCoordinateZLayBetweenPoints = (a
				.getCoordinateZ() > coordinateZ
				&& b.getCoordinateZ() < coordinateZ)
				|| (a.getCoordinateZ() < coordinateZ
						&& b.getCoordinateZ() > coordinateZ);
		return isCoordinateZLayBetweenPoints;
	}

}
