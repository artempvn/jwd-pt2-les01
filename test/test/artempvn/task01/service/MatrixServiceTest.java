package test.artempvn.task01.service;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.task01.entity.Point;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.service.MatrixService;

public class MatrixServiceTest {
	MatrixService service;

	@BeforeClass
	public void setUp() {
		service = new MatrixService();
	}

	@Test(dataProvider = "calculateDeterminantOrder3TestPositive")
	public void calculateDeterminantOrder3TestPositive(Point[] points,
			double expected) throws CustomException {
		double actual = service.calculateDeterminantOrder3(points);
		assertEquals(actual, expected, 0.001, " Test failed as...");
	}

	@DataProvider
	public Object[][] calculateDeterminantOrder3TestPositive() {
		return new Object[][] {
				{ new Point[] { new Point(0, 0, 0), new Point(1, 0, 0),
						new Point(0, 1, 0), new Point(0, 0, 5) }, 5 } };
	}

	@Test(dataProvider = "calculateDeterminantOrder3TestNegative",
			expectedExceptions = CustomException.class)
	public void calculateDeterminantOrder3TestNegative(Point[] points)
			throws CustomException {
		service.calculateDeterminantOrder3(points);
	}

	@DataProvider
	public Object[][] calculateDeterminantOrder3TestNegative() {
		return new Object[][] { { new Point[] { new Point(0, 0, 0),
				new Point(1, 0, 0), new Point(0, 1, 0) } }, { null } };
	}

	@AfterClass
	public void tierDown() {
		service = null;
	}
}
