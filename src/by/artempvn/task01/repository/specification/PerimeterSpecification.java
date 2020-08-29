package by.artempvn.task01.repository.specification;

import by.artempvn.task01.entity.Tetrahedron;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.repository.Specification;
import by.artempvn.task01.service.impl.TetrahedronServiceImpl;

public class PerimeterSpecification implements Specification {

	private double minPerimeter;
	private double maxPerimeter;

	public PerimeterSpecification(double minPerimeter, double maxPerimeter) {
		this.minPerimeter = minPerimeter;
		this.maxPerimeter = maxPerimeter;
	}

	@Override
	public boolean test(Tetrahedron shape) {
		TetrahedronServiceImpl service = new TetrahedronServiceImpl();
		double shapePerimeter = 0;
		try {
			shapePerimeter = service.calculatePerimeter(shape);
		} catch (CustomException e) {
			// impossible exception
		}
		return (shapePerimeter >= minPerimeter
				&& shapePerimeter <= maxPerimeter);
	}
}
