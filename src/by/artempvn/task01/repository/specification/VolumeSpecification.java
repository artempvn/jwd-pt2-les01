package by.artempvn.task01.repository.specification;

import by.artempvn.task01.entity.Tetrahedron;
import by.artempvn.task01.exception.CustomException;
import by.artempvn.task01.repository.Specification;
import by.artempvn.task01.service.impl.TetrahedronServiceImpl;

public class VolumeSpecification implements Specification {

	private double minVolume;
	private double maxVolume;

	public VolumeSpecification(double minVolume, double maxVolume) {
		this.minVolume = minVolume;
		this.maxVolume = maxVolume;
	}

	@Override
	public boolean test(Tetrahedron shape) {
		TetrahedronServiceImpl service = new TetrahedronServiceImpl();
		double shapeVolume = 0;
		try {
			shapeVolume = service.calculateVolume(shape);
		} catch (CustomException e) {
			// impossible exception
		}
		return (shapeVolume >= minVolume && shapeVolume <= maxVolume);
	}
}
