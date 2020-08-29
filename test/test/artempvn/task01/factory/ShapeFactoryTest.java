package test.artempvn.task01.factory;

import static org.testng.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashSet;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.task01.entity.Point;
import by.artempvn.task01.entity.Tetrahedron;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.factory.ShapeFactory;

public class ShapeFactoryTest {
	ShapeFactory factory;

	@BeforeClass
	public void setUp() {
		factory = new ShapeFactory();
	}

	@Test(dataProvider = "createTetrahedronTestPositive")
	public void createTetrahedronTestPositive(int id, double[] pointsData,
			Tetrahedron expected) throws CustomException {
		Tetrahedron actual = factory.createTetrahedron(id, pointsData);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] createTetrahedronTestPositive() {
		return new Object[][] { { 1,
				new double[] { 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 5 },
				new Tetrahedron(1,
						new HashSet<Point>(Arrays.asList(new Point(0, 0, 0),
								new Point(1, 0, 0), new Point(0, 1, 0),
								new Point(0, 0, 5)))) } };
	}

	@Test(dataProvider = "createTetrahedronTestNegative",
			expectedExceptions = CustomException.class)
	public void createTetrahedronTestNegative(int id, double[] pointsData)
			throws CustomException {
		factory.createTetrahedron(id, pointsData);
	}

	@DataProvider
	public Object[][] createTetrahedronTestNegative() {
		return new Object[][] {
				{ 1, new double[] { 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 5 } },
				{ 2, null },
				{ -1, new double[] { 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 5 } },
				{ 1, new double[] { 101, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 5 } },
				{ 3, new double[] { 0, 101, 0, 1, 0, 0, 0, 1, 0, 0, 0, 5 } },
				{ 4, new double[] { 0, 0, 101, 1, 0, 0, 0, 1, 0, 0, 0, 5 } },
				{ 5, new double[] { 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 5 } },
				{ 6, new double[] { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 5 } } };
	}

	@AfterClass
	public void tierDown() {
		factory = null;
	}
}
