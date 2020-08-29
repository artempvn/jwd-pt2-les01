package by.artempvn.task01.entity;

import static java.lang.Math.*;

public class Point {

	private static final int ACCURACY = 2;
	private double coordinateX;
	private double coordinateY;
	private double coordinateZ;

	public Point(double coordinateX, double coordinateY, double coordinateZ) {
		this.coordinateX = round(coordinateX * pow(10, ACCURACY))
				/ pow(10, ACCURACY);
		this.coordinateY = round(coordinateY * pow(10, ACCURACY))
				/ pow(10, ACCURACY);
		this.coordinateZ = round(coordinateZ * pow(10, ACCURACY))
				/ pow(10, ACCURACY);
	}

	public double getCoordinateX() {
		return coordinateX;
	}

	public double getCoordinateY() {
		return coordinateY;
	}

	public double getCoordinateZ() {
		return coordinateZ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(coordinateX);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(coordinateY);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(coordinateZ);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Point other = (Point) obj;
		if (Double.doubleToLongBits(coordinateX) != Double
				.doubleToLongBits(other.coordinateX)) {
			return false;
		}
		if (Double.doubleToLongBits(coordinateY) != Double
				.doubleToLongBits(other.coordinateY)) {
			return false;
		}
		if (Double.doubleToLongBits(coordinateZ) != Double
				.doubleToLongBits(other.coordinateZ)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"Point [coordinateX=%.2f, coordinateY=%.2f, coordinateZ=%.2f]",
				coordinateX, coordinateY, coordinateZ);
	}
}
