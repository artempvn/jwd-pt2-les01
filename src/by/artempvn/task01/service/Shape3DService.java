package by.artempvn.task01.service;

import java.util.Set;
import by.artempvn.task01.entity.Point;
import by.artempvn.task01.entity.Shape;
import by.artempvn.task01.exception.CustomException;

public interface Shape3DService<T extends Shape> {

	double calculateSquareSurface(T shape) throws CustomException;

	double calculatePerimeter(T shape) throws CustomException;

	double calculateVolume(T shape) throws CustomException;

	double calculateRatioOfVolumesSeparatedByPlaneParallelXY(T shape,
			double coordinateZ) throws CustomException;

	boolean isCorrectShape(Set<Point> points) throws CustomException;

}
