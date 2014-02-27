package cs275.game.mazeCraze;

public class Orientation {
	private float _x, _y, _z, _angle;

	Orientation(float x, float y, float z) {
		_x = x;
		_y = y;
		_z = z;
	}

	Orientation(float x, float y, float z, float angle) {
		_x = x;
		_y = y;
		_z = z;
		_angle = angle;
	}

	Orientation(Orientation other) {
		_x = other._x;
		_y = other._y;
		_z = other._z;
		_angle = other._angle;
	}

	public void right() {
		_angle += (Math.PI / 2.0);
	}

	public void left() {
		_angle -= (Math.PI / 2.0);
	}

	public boolean checkForward() {
		int tempx = Math.round(_x - 0.5f + (float) Math.cos(_angle));
		int tempz = Math.round(_z - 0.5f + (float) Math.sin(_angle));
		return GLcanvas.checkGrid(tempx,tempz);
	}

	public void forward() {
		_x += (float) Math.cos(_angle);
		_z += (float) Math.sin(_angle);
	}

	public void logistic(float directionTime, float positionTime, Orientation desired, Orientation starting) {
		_angle = (float) (starting._angle + desired.angleBetween(starting) / (1 + Math.exp(-directionTime)));
		_x = (float) (starting._x + (desired._x - starting._x) / (1 + Math.exp(-positionTime)));
		_z = (float) (starting._z + (desired._z - starting._z) / (1 + Math.exp(-positionTime)));
	}

	public float getLookX() {
		return _x - (float) (0.5 * Math.cos(_angle));
	}

	public float getLookY() {
		return _y;
	}

	public float getLookZ() {
		return _z - (float) (0.5 * Math.sin(_angle));
	}

	public float angleBetween(Orientation other) {
		float diff = _angle - other._angle;
		while (diff > Math.PI)
			diff -= 2 * Math.PI;
		while (diff < -Math.PI)
			diff += 2 * Math.PI;
		return diff;
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

	public void setPosition(Orientation other) {
		_x = other._x;
		_y = other._y;
		_z = other._z;
	}

	public void setRotation(Orientation other) {
		_angle = other._angle;
	}
}
