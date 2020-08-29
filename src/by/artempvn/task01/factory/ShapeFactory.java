package by.artempvn.task01.factory;

import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.task01.entity.Point;
import by.artempvn.task01.entity.Tetrahedron;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.service.impl.TetrahedronServiceImpl;
import by.artempvn.task01.validator.DataValidator;

public class ShapeFactory {
	private static final int NUMBER_DIMENSIONS = 3;
	private final static Logger logger = LogManager
			.getLogger(ShapeFactory.class);
	private static final int NUMBER_POINTS_VALUE = 12;
	private static final int NUMBER_OF_POINTS = 4;

	public Tetrahedron createTetrahedron(int id, double[] pointsData)
			throws CustomException {
		if (pointsData == null) {
			throw new CustomException("null input");
		}
		DataValidator validator = new DataValidator();
		if (!validator.isCorrectId(id)
				|| pointsData.length != NUMBER_POINTS_VALUE) {
			throw new CustomException("Invalid data input");
		}
		Tetrahedron tetrahedron;
		Point point;
		Set<Point> points = new HashSet<>();
		double coordinateX;
		double coordinateY;
		double coordinateZ;
		for (int i = 0, j = 0; i < NUMBER_OF_POINTS; i++, j += NUMBER_DIMENSIONS) {
			coordinateX = pointsData[j];
			coordinateY = pointsData[j + 1];
			coordinateZ = pointsData[j + 2];
			if (validator.isCorrectCoordinate(coordinateX)
					&& validator.isCorrectCoordinate(coordinateY)
					&& validator.isCorrectCoordinate(coordinateZ)) {
				point = new Point(coordinateX, coordinateY, coordinateZ);
				if (!points.add(point)) {
					throw new CustomException("Such point was already added");
				}
			} else {
				throw new CustomException("Invalid point data");
			}
		}
		TetrahedronServiceImpl tetrahedronService = new TetrahedronServiceImpl();
		if (tetrahedronService.isCorrectShape(points)) {
			tetrahedron = new Tetrahedron(id, points);
			logger.log(Level.INFO, "Tetrahedron was created, id = " + id);
		} else {
			throw new CustomException("Invalid point location");
		}
		return tetrahedron;
	}
}
