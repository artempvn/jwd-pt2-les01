package test.artempvn.task01.service;

import static org.testng.Assert.assertEquals;
import java.util.Optional;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.task01.entity.Point;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.service.GeometryService;

public class GeometryServiceTest {
	GeometryService service;

	@BeforeClass
	public void setUp() {
		service = new GeometryService();
	}

	@Test(dataProvider = "calculateTriangleSquareTestPositive")
	public void calculateTriangleSquareTestPositive(Point a, Point b, Point c,
			double expected) throws CustomException {
		double actual = service.calculateTriangleSquare(a, b, c);
		assertEquals(actual, expected, 0.01, " Test failed as...");
	}

	@DataProvider
	public Object[][] calculateTriangleSquareTestPositive() {
		return new Object[][] {
				{ new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0),
						0.5 },
				{ new Point(5, 6, 7), new Point(4, 0, 7), new Point(-1, 2, -3),
						34.37 } };
	}

	@Test(dataProvider = "calculateTriangleSquareTestNegative",
			expectedExceptions = CustomException.class)
	public void calculateTriangleSquareTestNegative(Point a, Point b, Point c)
			throws CustomException {
		service.calculateTriangleSquare(a, b, c);
	}

	@DataProvider
	public Object[][] calculateTriangleSquareTestNegative() {
		return new Object[][] {
				{ new Point(0, 0, 0), new Point(1, 1, 1), new Point(2, 2, 2) },
				{ null, null, null } };
	}

	@Test(dataProvider = "calculateLengthBetweenPointsTestPositive")
	public void calculateLengthBetweenPointsTestPositive(Point a, Point b,
			double expected) throws CustomException {
		double actual = service.calculateLengthBetweenPoints(a, b);
		assertEquals(actual, expected, 0.01, " Test failed as...");
	}

	@DataProvider
	public Object[][] calculateLengthBetweenPointsTestPositive() {
		return new Object[][] {
				{ new Point(0, 0, 0), new Point(1, 1, 1), 1.73 },
				{ new Point(5, 6, 7), new Point(4, 0, 7), 6.08 } };
	}

	@Test(dataProvider = "calculateLengthBetweenPointsTestNegative",
			expectedExceptions = CustomException.class)
	public void calculateLengthBetweenPointsTestNegative(Point a, Point b)
			throws CustomException {
		service.calculateLengthBetweenPoints(a, b);
	}

	@DataProvider
	public Object[][] calculateLengthBetweenPointsTestNegative() {
		return new Object[][] { { null, new Point(1, 1, 1) },
				{ new Point(1, 1, 1), null } };
	}

	@Test(dataProvider 
			= "calculatePointIntersectionLinePlaneParallelXYTestPositive")
	public void calculatePointIntersectionLinePlaneParallelXYTestPositive(
			Point a, Point b, double coordinateZ, Optional<Point> expected)
			throws CustomException {
		Optional<Point> actual = service
				.calculatePointIntersectionLinePlaneParallelXY(a, b,
						coordinateZ);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] calculatePointIntersectionLinePlaneParallelXYTestPositive() {
		return new Object[][] {
				{ new Point(0, 0, 0), new Point(0, 0, 1), 0.5,
						Optional.of(new Point(0, 0, 0.5)) },
				{ new Point(5, 6, 7), new Point(4, 0, 8), 10,
						Optional.empty() } };
	}

	@Test(dataProvider 
			= "calculatePointIntersectionLinePlaneParallelXYTestNegative",
			expectedExceptions = CustomException.class)
	public void calculatePointIntersectionLinePlaneParallelXYTestNegative(
			Point a, Point b) throws CustomException {
		service.calculatePointIntersectionLinePlaneParallelXY(a, b, 10);
	}

	@DataProvider
	public Object[][] calculatePointIntersectionLinePlaneParallelXYTestNegative() {
		return new Object[][] { { null, new Point(1, 1, 1) },
				{ new Point(1, 1, 1), null } };
	}

	@Test(dataProvider = "isThreePointsOnLineTestPositive")
	public void isThreePointsOnLineTestPositive(Point a, Point b, Point c,
			boolean expected) throws CustomException {
		boolean actual = service.isThreePointsOnLine(a, b, c);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isThreePointsOnLineTestPositive() {
		return new Object[][] {
				{ new Point(0, 0, 0), new Point(1, 1, 0), new Point(2, 2, 3),
						false },
				{ new Point(0, 0, 0), new Point(1, 1, 1), new Point(2, 2, 2),
						true } };
	}

	@Test(dataProvider = "isThreePointsOnLineTestNegative",
			expectedExceptions = CustomException.class)
	public void isThreePointsOnLineTestNegative(Point a, Point b, Point c)
			throws CustomException {
		service.isThreePointsOnLine(a, b, c);
	}

	@DataProvider
	public Object[][] isThreePointsOnLineTestNegative() {
		return new Object[][] {
				{ null, new Point(1, 1, 1), new Point(0, 0, 0) },
				{ new Point(1, 1, 1), null, new Point(0, 0, 0) },
				{ new Point(1, 1, 1), new Point(0, 0, 0), null } };
	}

	@AfterClass
	public void tierDown() {
		service = null;
	}
}
