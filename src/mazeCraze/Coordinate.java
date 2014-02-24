package mazeCraze;

public class Coordinate {
	private float _x, _y, _z;

	Coordinate(float x, float y, float z) {
		_x = x;
		_y = y;
		_z = z;
	}

	public float getX() {
		return _x;
	}

	public float getY() {
		return _y;
	}

	public float getZ() {
		return _z;
	}

	public Coordinate getLook(float angle) {
		return new Coordinate(_x - (float) (0.5 * Math.cos(angle)), _y, _z - (float) (0.5 * Math.sin(angle)));
	}

	public Coordinate add(Coordinate other) {
		return new Coordinate(_x + other._x, _y + other._y, _z + other._z);
	}

	public Coordinate subtract(Coordinate other) {
		return new Coordinate(_x - other._x, _y - other._y, _z - other._z);
	}

	public Coordinate scale(float other) {
		return new Coordinate(_x * other, _y * other, _z * other);
	}

	public Coordinate forward(Direction d) {
		return this.add(d.getCoord());
	}
}
