package test.artempvn.task01.validator;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.task01.entity.Point;
import by.artempvn.task01.validator.DataValidator;

public class DataValidatorTest {
	DataValidator validator;

	@BeforeClass
	public void setUp() {
		validator = new DataValidator();
	}

	@Test(dataProvider = "isCorrectCoordinateTest")
	public void isCorrectCoordinateTest(double coordinate, boolean expected) {
		boolean actual = validator.isCorrectCoordinate(coordinate);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isCorrectCoordinateTest() {
		return new Object[][] { { 10, true }, { -101, false } };
	}

	@Test(dataProvider = "isCorrectIdTest")
	public void isCorrectIdTest(int id, boolean expected) {
		boolean actual = validator.isCorrectId(id);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isCorrectIdTest() {
		return new Object[][] { { 10, true }, { 0, false }, { 741, false } };
	}

	@Test(dataProvider = "isCorrectDataTest")
	public void isCorrectDataTest(String data, boolean expected) {
		boolean actual = validator.isCorrectData(data);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isCorrectDataTest() {
		return new Object[][] { { "1 5 0 0 1 0 0 5 2 0 3 20 5", true },
				{ "id -110 0 0 1 0 0 0 2 0 0 20 5", false }, { null, false } };
	}

	@Test(dataProvider = "isCoordinateZLayBetweenPointsTest")
	public void isCoordinateZLayBetweenPointsTest(Point a, Point b,
			double coordinateZ, boolean expected) {
		boolean actual = validator.isCoordinateZLayBetweenPoints(a, b,
				coordinateZ);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isCoordinateZLayBetweenPointsTest() {
		return new Object[][] {
				{ new Point(0, 0, 0), new Point(0, 0, 10), 5, true },
				{ new Point(0, 0, 10), new Point(0, 0, 0), 5, true },
				{ new Point(0, 0, 5), new Point(0, 0, 1), 6, false },
				{ new Point(0, 0, 5), new Point(0, 0, 1), 0, false },
				{ null, new Point(0, 0, 1), 4, false },
				{ new Point(0, 0, 5), null, 4, false } };
	}

	@AfterClass
	public void tierDown() {
		validator = null;
	}
}
