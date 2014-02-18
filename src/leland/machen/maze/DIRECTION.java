package leland.machen.maze;

enum DIRECTION {
	NORTH(0.0f), EAST(Math.PI / 2.0), SOUTH(Math.PI), WEST(3.0 * Math.PI / 2.0);

	private float _angle;

	DIRECTION(double angle) {
		_angle = (float) angle;
	}

	public DIRECTION right() {
		return DIRECTION.values()[negmod(this.ordinal() + 1, 4)];
	}

	public DIRECTION left() {
		return DIRECTION.values()[negmod(this.ordinal() - 1, 4)];
	}

	private int negmod(int a, int b) {
		return (a % b + b) % b;
	}

	public Coordinate getCoord() {
		return new Coordinate((float) Math.cos(_angle), 0, (float) Math.sin(_angle));
	}

	public float angleTo(float angle) {
		float diff = _angle - angle;
		while (diff > Math.PI)
			diff -= 2 * Math.PI;
		while (diff < -Math.PI)
			diff += 2 * Math.PI;
		return diff;
	}
}