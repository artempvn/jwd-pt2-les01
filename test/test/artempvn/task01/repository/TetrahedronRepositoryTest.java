package test.artempvn.task01.repository;

import static org.testng.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.task01.comparator.TetrahedronComparatorType;
import by.artempvn.task01.entity.Point;
import by.artempvn.task01.entity.Tetrahedron;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.repository.QuadrantType;
import by.artempvn.task01.repository.Specification;
import by.artempvn.task01.repository.TetrahedronRepository;
import by.artempvn.task01.repository.specification.IdSpecification;
import by.artempvn.task01.repository.specification.PerimeterSpecification;
import by.artempvn.task01.repository.specification.QuadrantPointSpecification;
import by.artempvn.task01.repository.specification.ReferencePointDistanceFromOriginSpecification;
import by.artempvn.task01.repository.specification.SquareSurfaceSpecification;
import by.artempvn.task01.repository.specification.VolumeSpecification;

public class TetrahedronRepositoryTest {
	TetrahedronRepository repository;
	Tetrahedron shape;

	@BeforeClass
	public void setUp() {
		repository = TetrahedronRepository.getInstance();
		shape = new Tetrahedron(1,
				new HashSet<Point>(
						Arrays.asList(new Point(0, 0, 0), new Point(1, 0, 0),
								new Point(0, 1, 0), new Point(0, 0, 5))));
	}

	@Test(dataProvider = "addTestPositive")
	public void addTestPositive(Tetrahedron shape, boolean expected) {
		boolean actual = repository.add(shape);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] addTestPositive() {
		return new Object[][] { { shape, true }, { null, false },
				{ shape, false } };
	}

	@Test(dataProvider = "findShapeTestPositive",
			dependsOnMethods = "addTestPositive")
	public void findShapeTestPositive(int id, Optional<Tetrahedron> expected) {
		Optional<Tetrahedron> actual = repository.findShape(id);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] findShapeTestPositive() {
		return new Object[][] { { 1, Optional.of(shape) },
				{ 2, Optional.empty() } };
	}

	@Test(dataProvider = "queryTestPositive",
			dependsOnMethods = "findShapeTestPositive")
	public void queryTestPositive(Specification specification,
			TetrahedronComparatorType comparator, List<Tetrahedron> expected)
			throws CustomException {
		List<Tetrahedron> actual = repository.query(specification, comparator);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] queryTestPositive() {
		return new Object[][] { { new IdSpecification(1),
				TetrahedronComparatorType.ID_COMPARATOR, Arrays.asList(shape) },
				{ new PerimeterSpecification(0, 2),
						TetrahedronComparatorType.ID_COMPARATOR,
						Collections.emptyList() },
				{ new QuadrantPointSpecification(QuadrantType.I),
						TetrahedronComparatorType.ID_COMPARATOR,
						Arrays.asList(shape) },
				{ new ReferencePointDistanceFromOriginSpecification(10, 20),
						TetrahedronComparatorType.ID_COMPARATOR,
						Collections.emptyList() },
				{ new SquareSurfaceSpecification(1, 10),
						TetrahedronComparatorType.ID_COMPARATOR,
						Arrays.asList(shape) },
				{ new VolumeSpecification(0, 1),
						TetrahedronComparatorType.ID_COMPARATOR,
						Arrays.asList(shape) } };
	}

	@Test(dataProvider = "queryTestNegative",
			expectedExceptions = CustomException.class)
	public void queryTestNegative(Specification specification,
			TetrahedronComparatorType comparator) throws CustomException {
		repository.query(specification, comparator);
	}

	@DataProvider
	public Object[][] queryTestNegative() {
		return new Object[][] { { new IdSpecification(1), null },
				{ null, TetrahedronComparatorType.ID_COMPARATOR } };
	}

	@Test(dataProvider = "deleteTestPositive",
			dependsOnMethods = "queryTestPositive")
	public void deleteTestPositive(long id, boolean expected) {
		boolean actual = repository.delete(id);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] deleteTestPositive() {
		return new Object[][] { { 1, true }, { 2, false } };
	}

	@AfterClass
	public void tierDown() {
		repository.clear();
		repository = null;
		shape = null;
	}
}
