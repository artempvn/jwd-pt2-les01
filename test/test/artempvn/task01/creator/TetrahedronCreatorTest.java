package test.artempvn.task01.creator;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.task01.creator.TetrahedronCreator;
import by.artempvn.task01.exception.CustomException;

public class TetrahedronCreatorTest {
	TetrahedronCreator creator;

	@BeforeClass
	public void setUp() {
		creator = new TetrahedronCreator();
	}

	@Test(dataProvider = "createShapesTestPositive")
	public void createShapesTestPositive(String path, int expected)
			throws CustomException {
		int actual = creator.createShapes(path);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] createShapesTestPositive() {
		return new Object[][] { { "input/data.txt", 10 },
				{ "input/test.txt", 0 } };
	}

	@AfterClass
	public void tierDown() {
		creator = null;
	}
}
