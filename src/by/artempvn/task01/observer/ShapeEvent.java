package by.artempvn.task01.observer;

import java.util.EventObject;
import by.artempvn.task01.entity.Tetrahedron;

public class ShapeEvent extends EventObject {

	public ShapeEvent(Object source) {
		super(source);
	}

	@Override
	public Tetrahedron getSource() {
		return (Tetrahedron) super.getSource();
	}
}
