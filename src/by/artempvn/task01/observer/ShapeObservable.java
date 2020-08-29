package by.artempvn.task01.observer;

public interface ShapeObservable {

	void attach();

	void detach();

	void notifyObserver();
}
