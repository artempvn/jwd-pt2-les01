package by.artempvn.task01.repository;

public enum QuadrantType {
	I(true, true, true), II(false, true, true), III(false, false, true),
	IV(true, false, true), V(true, true, false), VI(false, true, false),
	VII(false, false, false), VIII(true, false, false);

	private boolean isCoordinateXPositive;
	private boolean isCoordinateYPositive;
	private boolean isCoordinateZPositive;

	private QuadrantType(boolean isCoordinateXPositive,
			boolean isCoordinateYPositive, boolean isCoordinateZPositive) {
		this.isCoordinateXPositive = isCoordinateXPositive;
		this.isCoordinateYPositive = isCoordinateYPositive;
		this.isCoordinateZPositive = isCoordinateZPositive;
	}

	public boolean isCoordinateXPositive() {
		return isCoordinateXPositive;
	}

	public boolean isCoordinateYPositive() {
		return isCoordinateYPositive;
	}

	public boolean isCoordinateZPositive() {
		return isCoordinateZPositive;
	}
}
