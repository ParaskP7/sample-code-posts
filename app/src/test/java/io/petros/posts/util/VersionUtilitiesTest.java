package io.petros.posts.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.util.ReflectionHelpers;

import io.petros.posts.PreconfiguredRobolectricTestRunner;
import io.petros.posts.RobolectricGeneralTestHelper;

import static io.petros.posts.util.WhiteboxTestUtilities.SDK_INT;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(PreconfiguredRobolectricTestRunner.class)
public class VersionUtilitiesTest extends RobolectricGeneralTestHelper {

    @Mock private Activity activityMock;
    @Mock private Intent intentMock;
    @Mock private Bundle optionsMock;

    @Before
    public void setUp() {
        setUpMocks();
    }

    @Test
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void startActivityTestForJellyBeanAndAfter() {
        VersionUtilities.startActivity(activityMock, intentMock, optionsMock);

        verify(activityMock, times(1)).startActivity(intentMock, optionsMock);
        verifyNoMoreInteractions(activityMock);
    }

    @Test
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    public void startActivityTestForPriorJellyBean() {
        ReflectionHelpers.setStaticField(Build.VERSION.class, SDK_INT, Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1);

        VersionUtilities.startActivity(activityMock, intentMock, optionsMock);

        verify(activityMock, times(1)).startActivity(intentMock);
        verifyNoMoreInteractions(activityMock);
    }

}
