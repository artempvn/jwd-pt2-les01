package test.artempvn.task01.entity;

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

public class TetrahedronTest {
	Tetrahedron shape;
	Point a;
	Point b;
	Point c;
	Point d;
	Point e;

	@BeforeClass
	public void setUp() {
		shape = new Tetrahedron(1,
				new HashSet<Point>(
						Arrays.asList(new Point(0, 0, 0), new Point(1, 0, 0),
								new Point(0, 1, 0), new Point(2, 2, 2))));
		a = new Point(0, 0, 0);
		b = new Point(0, 1, 0);
		c = new Point(-1, -1, -1);
		d = new Point(-1, -1, 0);
		e = new Point(1, 1, 1);
	}

	@Test(dataProvider = "changePointTestPositive")
	public void changePointTestPositive(Point existingPoint, Point newPoint,
			boolean expected) throws CustomException {
		boolean actual = shape.changePoint(existingPoint, newPoint);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] changePointTestPositive() {
		return new Object[][] { { a, c, true }, { d, d, false },
				{ b, b, false }, { null, b, false }, { b, null, false },
				{ b, e, false } };
	}

	@AfterClass
	public void tierDown() {
		shape = null;
		a = null;
		b = null;
		c = null;
		d = null;
		e = null;
	}
}
