package by.artempvn.task01.repository.specification;

import by.artempvn.task01.entity.Tetrahedron;
import by.artempvn.task01.repository.Specification;

public class IdSpecification implements Specification {

	private long id;

	public IdSpecification(long id) {
		this.id = id;
	}

	@Override
	public boolean test(Tetrahedron tetrahedron) {
		return tetrahedron.getId() == id;
	}

}
