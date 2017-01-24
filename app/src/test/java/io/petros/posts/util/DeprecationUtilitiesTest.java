package io.petros.posts.util;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.util.ReflectionHelpers;

import io.petros.posts.PreconfiguredRobolectricTestRunner;
import io.petros.posts.R;
import io.petros.posts.RobolectricGeneralTestHelper;

import static io.petros.posts.util.WhiteboxTestUtilities.SDK_INT;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(PreconfiguredRobolectricTestRunner.class)
public class DeprecationUtilitiesTest extends RobolectricGeneralTestHelper {

    @Mock private View viewMock;

    @Before
    public void setUp() {
        setUpMocks();
        setUpView();
    }

    private void setUpView() {
        when(viewMock.getContext()).thenReturn(context);
    }

    @Test
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setBackgroundTestForJellyBeanAndAfter() {
        DeprecationUtilities.setBackground(viewMock, R.drawable.snackbar__design_snackbar_background);

        verify(viewMock, times(1)).setBackground(any(Drawable.class));
        verifyNoMoreInteractions(connectivityManagerMock);
    }

    @Test
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Ignore("android.content.res.Resources$NotFoundException: Unable to find resource ID #0x0 in packages")
    public void setBackgroundTestForPriorJellyBean() {
        ReflectionHelpers.setStaticField(Build.VERSION.class, SDK_INT, Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1);

        DeprecationUtilities.setBackground(viewMock, R.drawable.snackbar__design_snackbar_background);

        verify(viewMock, times(1)).setBackgroundDrawable(any(Drawable.class));
        verifyNoMoreInteractions(connectivityManagerMock);
    }

}
