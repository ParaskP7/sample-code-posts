package io.petros.posts.service.detector;

import android.net.NetworkInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import io.petros.posts.PreconfiguredRobolectricTestRunner;
import io.petros.posts.RobolectricGeneralTestHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(PreconfiguredRobolectricTestRunner.class)
public class InternetAvailabilityDetectorTest extends RobolectricGeneralTestHelper {

    private InternetAvailabilityDetector internetAvailabilityDetector;
    @Mock private NetworkInfo activeNetworkInfoMock;

    @Before
    public void setUp() {
        setUpMocks();
        setUpDetector();
    }

    private void setUpDetector() {
        internetAvailabilityDetector = new InternetAvailabilityDetector(applicationMock);
    }

    @Test
    public void givenActiveNetworkInfoIsNotAvailable_WhenConnectivityIsChecked_ThenItResultsToDisconnectedFromInternet() {
        when(connectivityManagerMock.getActiveNetworkInfo()).thenReturn(null);

        final boolean isAvailable = internetAvailabilityDetector.isAvailable();

        verify(connectivityManagerMock, times(1)).getActiveNetworkInfo();
        verifyNoMoreInteractions(connectivityManagerMock);
        verifyZeroInteractions(activeNetworkInfoMock);
        assertThat(isAvailable).isFalse();
    }

    @Test
    public void givenActiveNetworkInfoIsAvailable_WhenConnectivityIsChecked_ThenItResultsToConnectedToInternet() {
        when(connectivityManagerMock.getActiveNetworkInfo()).thenReturn(activeNetworkInfoMock);

        final boolean isAvailable = internetAvailabilityDetector.isAvailable();

        verify(connectivityManagerMock, times(1)).getActiveNetworkInfo();
        verifyNoMoreInteractions(connectivityManagerMock);
        verify(activeNetworkInfoMock, times(2)).getType();
        verifyNoMoreInteractions(activeNetworkInfoMock);
        assertThat(isAvailable).isTrue();
    }

}
