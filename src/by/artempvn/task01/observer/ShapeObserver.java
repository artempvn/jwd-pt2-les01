package by.artempvn.task01.observer;

import java.util.EventObject;

public interface ShapeObserver<T extends EventObject> {
	void actionPerformed(T t);
}
