package by.artempvn.task01.parser;

import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.validator.DataValidator;

public class ShapeDataParser {

	private static final String SEPARATOR = "\\s+";
	private static final int NUMBER_SHAPE_VALUE = 13;

	public double[] parseStringShapeValue(String data) throws CustomException {
		if (data == null) {
			throw new CustomException("null data");
		}
		DataValidator validator = new DataValidator();
		double[] shapeData = new double[NUMBER_SHAPE_VALUE];
		if (!validator.isCorrectData(data)) {
			throw new CustomException("incorrect data");
		}
		String[] splitData = data.split(SEPARATOR);
		for (int i = 0; i < shapeData.length; i++) {
			shapeData[i] = Double.parseDouble(splitData[i]);
		}
		return shapeData;
	}
}
