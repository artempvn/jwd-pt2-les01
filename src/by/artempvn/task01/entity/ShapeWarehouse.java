package by.artempvn.task01.entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.task01.exception.CustomException;

public class ShapeWarehouse {
	private Map<Integer, ShapeParameter> shapeParameters;
	private static ShapeWarehouse shapeWarehouse;
	private final static Logger logger = LogManager
			.getLogger(ShapeWarehouse.class);

	private ShapeWarehouse() {
		shapeParameters = new HashMap<>();
		logger.log(Level.INFO, "Warehouse was created");
	}

	public static ShapeWarehouse getInstance() {
		if (shapeWarehouse == null) {
			shapeWarehouse = new ShapeWarehouse();
		}
		return shapeWarehouse;
	}

	public Map<Integer, ShapeParameter> getShapeParametersReadOnly() {
		return Collections.unmodifiableMap(shapeParameters);
	}

	public ShapeParameter get(int id) {
		return shapeParameters.get(id);
	}

	public void put(int id, ShapeParameter value) throws CustomException {
		if (value == null) {
			throw new CustomException("null input");
		}
		shapeParameters.put(id, value);
	}

	public void remove(int id) {
		shapeParameters.remove(id);
	}
}
