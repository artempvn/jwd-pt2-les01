package by.artempvn.task01.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.observer.ShapeEvent;
import by.artempvn.task01.observer.ShapeObservable;
import by.artempvn.task01.observer.ShapeObserver;
import by.artempvn.task01.service.Shape3DService;
import by.artempvn.task01.service.impl.TetrahedronServiceImpl;

public class Tetrahedron extends Shape implements ShapeObservable {

	private final static Logger logger = LogManager
			.getLogger(Tetrahedron.class);

	private Set<Point> points;
	private ShapeObserver<ShapeEvent> observer;

	public Tetrahedron(int id, Set<Point> points) {
		setId(id);
		this.points = points;
	}

	public Set<Point> getPointsReadOnly() {
		return Collections.unmodifiableSet(points);
	}

	public boolean changePoint(Point existingPoint, Point newPoint) {
		if (existingPoint == null || newPoint == null
				|| points.contains(newPoint)
				|| !points.contains(existingPoint)) {
			return false;
		}
		Set<Point> newPoints = new HashSet<Point>(points);
		newPoints.remove(existingPoint);
		boolean isUpdated = false;
		newPoints.add(newPoint);
		TetrahedronServiceImpl service = new TetrahedronServiceImpl();
		try {
			if (service.isCorrectShape(newPoints)) {
				points.remove(existingPoint);
				isUpdated = points.add(newPoint);
				notifyObserver();
			}
		} catch (CustomException e) {
			// impossible exception
		}
		return isUpdated;
	}

	public void updateWarehouseData() {
		attach();
		notifyObserver();
		detach();
	}

	@Override
	public void attach() {
		if (observer == null) {
			observer = new ShapeObserver<ShapeEvent>() {
				@Override
				public void actionPerformed(ShapeEvent shape) {
					ShapeWarehouse warehouse = ShapeWarehouse.getInstance();
					Shape3DService<Tetrahedron> service 
					= new TetrahedronServiceImpl();
					double perimeter = 0;
					double squareSurface = 0;
					double volume = 0;
					try {
						perimeter = service
								.calculatePerimeter(shape.getSource());
						squareSurface = service
								.calculateSquareSurface(shape.getSource());
						volume = service.calculateVolume(shape.getSource());
					} catch (CustomException e) {
						// Impossible exception
					}
					ShapeParameter parameter = new ShapeParameter(volume,
							squareSurface, perimeter);
					try {
						warehouse.put(shape.getSource().getId(), parameter);
					} catch (CustomException e) {
						// Impossible exception
					}
					logger.log(Level.INFO, "Warehouse was updated, id = "
							+ shape.getSource().getId());
				}
			};
		}
	}

	@Override
	public void detach() {
		observer = null;
	}

	@Override
	public void notifyObserver() {
		if (observer != null) {
			observer.actionPerformed(new ShapeEvent(this));
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// field "id" doesn't act here to match with "equals" method
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Tetrahedron other = (Tetrahedron) obj;
		// field "id" doesn't act here for logic reason
		if (points == null) {
			if (other.points != null) {
				return false;
			}
		} else if (!points.equals(other.points)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("Tetrahedron [id=%s, points=%s]", getId(), points);
	}
}
