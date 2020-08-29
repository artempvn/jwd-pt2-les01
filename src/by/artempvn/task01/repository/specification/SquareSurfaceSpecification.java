package by.artempvn.task01.repository.specification;

import by.artempvn.task01.entity.Tetrahedron;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.repository.Specification;
import by.artempvn.task01.service.impl.TetrahedronServiceImpl;

public class SquareSurfaceSpecification implements Specification {

	private double minSquare;
	private double maxSquare;

	public SquareSurfaceSpecification(double minSquare, double maxSquare) {
		this.minSquare = minSquare;
		this.maxSquare = maxSquare;
	}

	@Override
	public boolean test(Tetrahedron shape) {
		TetrahedronServiceImpl service = new TetrahedronServiceImpl();
		double shapeSquare = 0;
		try {
			shapeSquare = service.calculateSquareSurface(shape);
		} catch (CustomException e) {
			// impossible exception
		}
		return (shapeSquare >= minSquare && shapeSquare <= maxSquare);
	}
}
