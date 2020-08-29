package by.artempvn.task01.comparator;

import java.util.Comparator;
import java.util.Set;
import by.artempvn.task01.entity.Point;
import by.artempvn.task01.entity.Tetrahedron;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.service.impl.TetrahedronServiceImpl;

public enum TetrahedronComparatorType implements Comparator<Tetrahedron> {

	ID_COMPARATOR {
		@Override
		public int compare(Tetrahedron shape1, Tetrahedron shape2) {
			return ((int) (shape1.getId() - shape2.getId()));
		}
	},

	COORDINATE_X_COMPARATOR {
		@Override
		public int compare(Tetrahedron shape1, Tetrahedron shape2) {
			double shape1MinX = findMinCoordinateValue(shape1, COORDINATE_X);
			double shape2MinX = findMinCoordinateValue(shape2, COORDINATE_X);
			int compare = 0;
			if (shape1MinX - shape2MinX > 0) {
				compare = 1;
			} else if (shape1MinX - shape2MinX < 0) {
				compare = -1;
			}
			return compare;
		}
	},

	COORDINATE_Y_COMPARATOR {
		@Override
		public int compare(Tetrahedron shape1, Tetrahedron shape2) {
			double shape1MinY = findMinCoordinateValue(shape1, COORDINATE_Y);
			double shape2MinY = findMinCoordinateValue(shape2, COORDINATE_Y);
			int compare = 0;
			if (shape1MinY - shape2MinY > 0) {
				compare = 1;
			} else if (shape1MinY - shape2MinY < 0) {
				compare = -1;
			}
			return compare;
		}
	},

	COORDINATE_Z_COMPARATOR {
		@Override
		public int compare(Tetrahedron shape1, Tetrahedron shape2) {
			double shape1MinZ = findMinCoordinateValue(shape1, COORDINATE_Z);
			double shape2MinZ = findMinCoordinateValue(shape2, COORDINATE_Z);
			int compare = 0;
			if (shape1MinZ - shape2MinZ > 0) {
				compare = 1;
			} else if (shape1MinZ - shape2MinZ < 0) {
				compare = -1;
			}
			return compare;
		}
	},

	PERIMETER_COMPARATOR {
		@Override
		public int compare(Tetrahedron shape1, Tetrahedron shape2) {
			TetrahedronServiceImpl service = new TetrahedronServiceImpl();
			double shape1Perimeter = 0;
			double shape2Perimeter = 0;
			try {
				shape1Perimeter = service.calculatePerimeter(shape1);
				shape2Perimeter = service.calculatePerimeter(shape2);
			} catch (CustomException e) {
				// impossible exception
			}
			int compare = 0;
			if (shape1Perimeter - shape2Perimeter > 0) {
				compare = 1;
			} else if (shape1Perimeter - shape2Perimeter < 0) {
				compare = -1;
			}
			return compare;
		}
	},

	VOLUME_COMPARATOR {
		@Override
		public int compare(Tetrahedron shape1, Tetrahedron shape2) {
			TetrahedronServiceImpl service = new TetrahedronServiceImpl();
			double shape1Volume = 0;
			double shape2Volume = 0;
			try {
				shape1Volume = service.calculateVolume(shape1);
				shape2Volume = service.calculateVolume(shape2);
			} catch (CustomException e) {
				// impossible exception
			}
			int compare = 0;
			if (shape1Volume - shape2Volume > 0) {
				compare = 1;
			} else if (shape1Volume - shape2Volume < 0) {
				compare = -1;
			}
			return compare;
		}
	},

	SQUARE_SURFACE_COMPARATOR {
		@Override
		public int compare(Tetrahedron shape1, Tetrahedron shape2) {
			TetrahedronServiceImpl service = new TetrahedronServiceImpl();
			double shape1Square = 0;
			double shape2Square = 0;
			try {
				shape1Square = service.calculateSquareSurface(shape1);
				shape2Square = service.calculateSquareSurface(shape2);
			} catch (CustomException e) {
				// impossible exception
			}
			int compare = 0;
			if (shape1Square - shape2Square > 0) {
				compare = 1;
			} else if (shape1Square - shape2Square < 0) {
				compare = -1;
			}
			return compare;
		}
	};

	private static final int MAX_COORDINATE_VALUE = 100;
	private static final String COORDINATE_X = "X";
	private static final String COORDINATE_Y = "Y";
	private static final String COORDINATE_Z = "Z";

	private static double findMinCoordinateValue(Tetrahedron shape,
			String coordinate) {
		Set<Point> points = shape.getPointsReadOnly();
		double minCoordinateValue = MAX_COORDINATE_VALUE;
		for (Point point : points) {
			switch (coordinate) {
			case COORDINATE_X:
				if (minCoordinateValue > Math.abs(point.getCoordinateX())) {
					minCoordinateValue = Math.abs(point.getCoordinateX());
				}
				break;
			case COORDINATE_Y:
				if (minCoordinateValue > Math.abs(point.getCoordinateY())) {
					minCoordinateValue = Math.abs(point.getCoordinateY());
				}
				break;
			case COORDINATE_Z:
				if (minCoordinateValue > Math.abs(point.getCoordinateZ())) {
					minCoordinateValue = Math.abs(point.getCoordinateZ());
				}
				break;
			}
		}
		return minCoordinateValue;
	}
}
