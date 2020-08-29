package by.artempvn.task01.entity;

public class ShapeParameter {
	private double volume;
	private double squareSurface;
	private double perimeter;

	public ShapeParameter(double volume, double squareSurface,
			double perimeter) {
		this.volume = volume;
		this.squareSurface = squareSurface;
		this.perimeter = perimeter;
	}

	public double getVolume() {
		return volume;
	}

	public double getSquareSurface() {
		return squareSurface;
	}

	public double getPerimeter() {
		return perimeter;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public void setSquareSurface(double squareSurface) {
		this.squareSurface = squareSurface;
	}

	public void setPerimeter(double perimeter) {
		this.perimeter = perimeter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(perimeter);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(squareSurface);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(volume);
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
		ShapeParameter other = (ShapeParameter) obj;
		if (Double.doubleToLongBits(perimeter) != Double
				.doubleToLongBits(other.perimeter)) {
			return false;
		}
		if (Double.doubleToLongBits(squareSurface) != Double
				.doubleToLongBits(other.squareSurface)) {
			return false;
		}
		if (Double.doubleToLongBits(volume) != Double
				.doubleToLongBits(other.volume)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"ShapeParameter [volume=%.2f, squareSurface=%.2f, perimeter=%.2f]",
				volume, squareSurface, perimeter);
	}
}
