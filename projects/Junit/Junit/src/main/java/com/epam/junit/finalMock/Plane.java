package com.epam.junit.finalMock;

public final class Plane {
	public static final int ENGINE_ID_RIGHT = 2;
	public static final int ENGINE_ID_LEFT = 1;

	public boolean verifyAllSystems() {
		throw new UnsupportedOperationException("Fail if not mocked!");
	}

	public void startEngine(int engineId) {
		throw new UnsupportedOperationException(
				"Fail if not mocked! [engineId=" + engineId + "]");
	}
}