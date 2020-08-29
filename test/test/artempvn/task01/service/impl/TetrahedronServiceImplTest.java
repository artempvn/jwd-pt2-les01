package test.artempvn.task01.service.impl;

import static org.testng.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.task01.entity.Point;
import by.artempvn.task01.entity.Tetrahedron;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.service.impl.TetrahedronServiceImpl;

public class TetrahedronServiceImplTest {
	TetrahedronServiceImpl service;

	@BeforeClass
	public void setUp() {
		service = new TetrahedronServiceImpl();
	}

	@Test(dataProvider = "calculateSquareSurfaceTestPositive")
	public void calculateSquareSurfaceTestPositive(Tetrahedron shape,
			double expected) throws CustomException {
		double actual = service.calculateSquareSurface(shape);
		assertEquals(actual, expected, 0.01, " Test failed as...");
	}

	@DataProvider
	public Object[][] calculateSquareSurfaceTestPositive() {
		return new Object[][] {
				{ new Tetrahedron(1,
						new HashSet<Point>(Arrays.asList(new Point(0, 0, 0),
								new Point(1, 0, 0), new Point(0, 1, 0),
								new Point(0, 0, 5)))),
						9.07 } };
	}

	@Test(expectedExceptions = CustomException.class)
	public void calculateSquareSurfaceTestNegative() throws CustomException {
		service.calculateSquareSurface(null);
	}

	@Test(dataProvider = "calculatePerimeterTestPositive")
	public void calculatePerimeterTestPositive(Tetrahedron shape,
			double expected) throws CustomException {
		double actual = service.calculatePerimeter(shape);
		assertEquals(actual, expected, 0.01, " Test failed as...");
	}

	@DataProvider
	public Object[][] calculatePerimeterTestPositive() {
		return new Object[][] {
				{ new Tetrahedron(1,
						new HashSet<Point>(Arrays.asList(new Point(0, 0, 0),
								new Point(1, 0, 0), new Point(0, 1, 0),
								new Point(0, 0, 5)))),
						18.61 } };
	}

	@Test(expectedExceptions = CustomException.class)
	public void calculatePerimeterTestNegative() throws CustomException {
		service.calculatePerimeter(null);
	}

	@Test(dataProvider = "calculateVolumeTestPositive")
	public void calculateVolumeTestPositive(Tetrahedron shape, double expected)
			throws CustomException {
		double actual = service.calculateVolume(shape);
		assertEquals(actual, expected, 0.01, " Test failed as...");
	}

	@DataProvider
	public Object[][] calculateVolumeTestPositive() {
		return new Object[][] {
				{ new Tetrahedron(1,
						new HashSet<Point>(Arrays.asList(new Point(0, 0, 0),
								new Point(1, 0, 0), new Point(0, 1, 0),
								new Point(0, 0, 5)))),
						0.83 } };
	}

	@Test(expectedExceptions = CustomException.class)
	public void calculateVolumeTestNegative() throws CustomException {
		service.calculateVolume(null);
	}

	@Test(dataProvider 
			= "calculateRatioOfVolumesSeparatedByPlaneParallelXYTestPositive")
	public void calculateRatioOfVolumesSeparatedByPlaneParallelXYTestPositive(
			Tetrahedron shape, double coordinateZ, double expected)
			throws CustomException {
		double actual = service
				.calculateRatioOfVolumesSeparatedByPlaneParallelXY(shape,
						coordinateZ);
		assertEquals(actual, expected, 0.001, " Test failed as...");
	}

	@DataProvider
	public Object[][] calculateRatioOfVolumesSeparatedByPlaneParallelXYTestPositive() {
		return new Object[][] {
				{ new Tetrahedron(1,
						new HashSet<Point>(Arrays.asList(new Point(0, 0, 0),
								new Point(1, 0, 0), new Point(0, 1, 0),
								new Point(0, 0, 5)))),
						4, 0.008 },
				{ new Tetrahedron(1,
						new HashSet<Point>(Arrays.asList(new Point(0, 0, 0),
								new Point(1, 0, 0), new Point(0, 1, 0),
								new Point(0, 0, 5)))),
						6, 0 } };
	}

	@Test(expectedExceptions = CustomException.class)
	public void calculateRatioOfVolumesSeparatedByPlaneParallelXYTestNegative()
			throws CustomException {
		service.calculateRatioOfVolumesSeparatedByPlaneParallelXY(null, 0);
	}

	@Test(dataProvider = "isCorrectShapeTestPositive")
	public void isCorrectShapeTestPositive(Set<Point> somePoints,
			boolean expected) throws CustomException {
		boolean actual = service.isCorrectShape(somePoints);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isCorrectShapeTestPositive() {
		return new Object[][] {
				{ new HashSet<Point>(Arrays.asList(new Point(0, 0, 0),
						new Point(1, 0, 0), new Point(0, 1, 0))), false },
				{ new HashSet<Point>(
						Arrays.asList(new Point(0, 0, 0), new Point(1, 1, 1),
								new Point(2, 2, 2), new Point(0, 0, 5))),
						false },
				{ new HashSet<Point>(
						Arrays.asList(new Point(0, 0, 0), new Point(1, 0, 0),
								new Point(0, 1, 0), new Point(0, 0, 5))),
						true } };
	}

	@Test(expectedExceptions = CustomException.class)
	public void isCorrectShapeTestNegative() throws CustomException {
		service.isCorrectShape(null);
	}

	@Test(dataProvider = "isBaseOnCoordinatePlaneTestPositive")
	public void isBaseOnCoordinatePlaneTestPositive(Tetrahedron shape,
			boolean expected) throws CustomException {
		boolean actual = service.isBaseOnCoordinatePlane(shape);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isBaseOnCoordinatePlaneTestPositive() {
		return new Object[][] {
				{ new Tetrahedron(1,
						new HashSet<Point>(Arrays.asList(new Point(0, 0, 0),
								new Point(1, 0, 0), new Point(0, 1, 0),
								new Point(0, 0, 5)))),
						true },
				{ new Tetrahedron(1,
						new HashSet<Point>(Arrays.asList(new Point(0, 0, 1),
								new Point(1, 0, 1), new Point(0, 1, 1),
								new Point(0, 0, 5)))),
						false } };
	}

	@Test(expectedExceptions = CustomException.class)
	public void isBaseOnCoordinatePlaneTestNegative() throws CustomException {
		service.isBaseOnCoordinatePlane(null);
	}

	@AfterClass
	public void tierDown() {
		service = null;
	}
}
