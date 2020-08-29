package test.artempvn.task01.reader;

import static org.testng.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.reader.DataReader;

public class DataReaderTest {
	DataReader reader;

	@BeforeClass
	public void setUp() {
		reader = new DataReader();
	}

	@Test
	public void readFileTest() throws CustomException {
		List<String> actual = reader.readFile("input/test.txt");
		List<String> expected = Arrays.asList("first string", "second string");
		assertEquals(actual, expected, " Test failed as...");
	}

	@AfterClass
	public void tierDown() {
		reader = null;
	}
}
