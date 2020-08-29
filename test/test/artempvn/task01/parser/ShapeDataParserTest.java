package test.artempvn.task01.parser;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.parser.ShapeDataParser;

public class ShapeDataParserTest {
	ShapeDataParser parser;

	@BeforeClass
	public void setUp() {
		parser = new ShapeDataParser();
	}

	@Test(dataProvider = "parseStringShapeValueTestPositive")
	public void parseStringShapeValueTestPositive(String data,
			double[] expected) throws CustomException {
		double[] actual = parser.parseStringShapeValue(data);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] parseStringShapeValueTestPositive() {
		return new Object[][] { { "1 0 0 0 1 0 0 0 1 0 0 0 5",
				new double[] { 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 5 } } };
	}

	@Test(dataProvider = "parseStringShapeValueTestNegative",
			expectedExceptions = CustomException.class)
	public void parseStringShapeValueTestNegative(String data)
			throws CustomException {
		parser.parseStringShapeValue(data);

	}

	@DataProvider
	public Object[][] parseStringShapeValueTestNegative() {
		return new Object[][] { { "1 0 0 0 1 0 0 0 1 0 0 0" }, { null } };
	}

	@AfterClass
	public void tierDown() {
		parser = null;
	}
}
