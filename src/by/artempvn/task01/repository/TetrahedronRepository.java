package by.artempvn.task01.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.task01.comparator.TetrahedronComparatorType;
import by.artempvn.task01.entity.ShapeWarehouse;
import by.artempvn.task01.entity.Tetrahedron;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.observer.RepositoryEvent;
import by.artempvn.task01.observer.ShapeObservable;
import by.artempvn.task01.observer.ShapeObserver;

public class TetrahedronRepository implements ShapeObservable {
	private final static Logger logger = LogManager
			.getLogger(TetrahedronRepository.class);
	private List<Tetrahedron> shapes;
	private static TetrahedronRepository shapeRepository;
	private ShapeObserver<RepositoryEvent> observer;

	private TetrahedronRepository() {
		shapes = new ArrayList<>();
		logger.log(Level.INFO, "Repository was created");
	}

	public static TetrahedronRepository getInstance() {
		if (shapeRepository == null) {
			shapeRepository = new TetrahedronRepository();
		}
		return shapeRepository;
	}

	public List<Tetrahedron> getShapesReadOnly() {
		return Collections.unmodifiableList(shapes);
	}

	public Optional<Tetrahedron> findShape(long id) {
		return shapes.stream().filter(shape -> shape.getId() == id).findFirst();
	}

	public List<Tetrahedron> query(Specification specification)
			throws CustomException {
		if (specification == null) {
			throw new CustomException("null input");
		}
		List<Tetrahedron> selectedShapes = shapes.stream().filter(specification)
				.collect(Collectors.toList());
		return selectedShapes;
	}

	public List<Tetrahedron> query(Specification specification,
			TetrahedronComparatorType comparator) throws CustomException {
		if (comparator == null) {
			throw new CustomException("null input");
		}
		List<Tetrahedron> selectedShapes = query(specification);
		selectedShapes.sort(comparator);
		return selectedShapes;
	}

	public boolean add(Tetrahedron tetrahedron) {
		boolean isAdded = false;
		if (tetrahedron != null && !isIdPresent(tetrahedron.getId())) {
			isAdded = shapes.add(tetrahedron);
			logger.log(Level.INFO,
					"Tetrahedron was added, id = " + tetrahedron.getId());
		}
		return isAdded;
	}

	public boolean delete(long id) {
		boolean isDeleted = false;
		Iterator<Tetrahedron> iterator = shapes.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getId() == id) {
				iterator.remove();
				isDeleted = true;
				logger.log(Level.INFO, "Tetrahedron was removed, id = " + id);
			}
		}
		return isDeleted;
	}

	public void clear() {
		shapes.clear();
	}

	public void deleteOldWarehouseData() {
		attach();
		notifyObserver();
		detach();
	}

	private boolean isIdPresent(long id) {
		return shapes.parallelStream().anyMatch(shape -> shape.getId() == id);
	}

	@Override
	public void attach() {
		if (observer == null) {
			observer = new ShapeObserver<RepositoryEvent>() {
				@Override
				public void actionPerformed(RepositoryEvent repository) {
					ShapeWarehouse warehouse = ShapeWarehouse.getInstance();
					Set<Integer> ids = warehouse.getShapeParametersReadOnly()
							.keySet();
					List<Integer> removedIds = new ArrayList<>();
					List<Integer> repositoryIds = new ArrayList<>();
					for (Tetrahedron shape : shapes) {
						repositoryIds.add(shape.getId());
					}
					Iterator<Integer> iterator = ids.iterator();
					while (iterator.hasNext()) {
						int id = iterator.next();
						if (!repositoryIds.contains(id)) {
							removedIds.add(id);
						}
					}
					for (int id : removedIds) {
						warehouse.remove(id);
						logger.log(Level.INFO, "Warehouse was updated: id = "
								+ id + " was removed");
					}
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
			observer.actionPerformed(new RepositoryEvent(this));
		}
	}
}
