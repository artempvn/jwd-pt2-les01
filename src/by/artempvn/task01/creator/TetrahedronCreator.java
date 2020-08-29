package by.artempvn.task01.creator;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.task01.entity.Tetrahedron;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.factory.ShapeFactory;
import by.artempvn.task01.parser.ShapeDataParser;
import by.artempvn.task01.reader.DataReader;
import by.artempvn.task01.repository.TetrahedronRepository;

public class TetrahedronCreator {
	private final static Logger logger = LogManager
			.getLogger(TetrahedronCreator.class);

	public int createShapes(String path) {
		DataReader reader = new DataReader();
		Tetrahedron shape;
		ShapeFactory factory = new ShapeFactory();
		TetrahedronRepository repository = TetrahedronRepository.getInstance();
		int numberAddedShapes = 0;
		ShapeDataParser parser = new ShapeDataParser();
		List<String> strings = new ArrayList<>();
		try {
			strings = reader.readFile(path);
		} catch (CustomException e) {
			logger.log(Level.ERROR, "Can't read file");
		}
		for (String string : strings) {
			try {
				double[] values = parser.parseStringShapeValue(string);
				int id = (int) values[0];
				double[] pointsValue = new double[12];
				for (int i = 0; i < pointsValue.length; i++) {
					pointsValue[i] = values[i + 1];
				}
				shape = factory.createTetrahedron(id, pointsValue);
				if (repository.add(shape)) {
					shape.updateWarehouseData();
					numberAddedShapes++;
				}
			} catch (CustomException e) {
				logger.log(Level.WARN, "Can't create shape");
			}
		}
		return numberAddedShapes;
	}
}
