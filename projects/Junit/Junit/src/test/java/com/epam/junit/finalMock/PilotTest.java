package com.epam.junit.finalMock;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;

@PrepareForTest
public class PilotTest extends PowerMockTestCase {
	@Test
	public void testReadyForFlight() {
		Plane planeMock = PowerMockito.mock(Plane.class);
		Pilot pilot = new Pilot(planeMock);

		Mockito.when(planeMock.verifyAllSystems()).thenReturn(true);

		// testing method
		boolean actualStatus = pilot.readyForFlight();

		Assert.assertEquals(actualStatus, true);
		Mockito.verify(planeMock).startEngine(Plane.ENGINE_ID_LEFT);
		Mockito.verify(planeMock).startEngine(Plane.ENGINE_ID_RIGHT);
	}
}
