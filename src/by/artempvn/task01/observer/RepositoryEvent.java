package by.artempvn.task01.observer;

import java.util.EventObject;
import by.artempvn.task01.repository.TetrahedronRepository;

public class RepositoryEvent extends EventObject {

	public RepositoryEvent(Object source) {
		super(source);
	}

	@Override
	public TetrahedronRepository getSource() {
		return (TetrahedronRepository) super.getSource();
	}
}
