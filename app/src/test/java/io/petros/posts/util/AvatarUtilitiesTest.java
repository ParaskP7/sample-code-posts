package io.petros.posts.util;

import org.junit.Test;

import io.petros.posts.GeneralTestHelper;

import static org.assertj.core.api.Assertions.assertThat;

public class AvatarUtilitiesTest extends GeneralTestHelper {

    @Test
    public void getUri() {
        final String email = user.getEmail();

        final String uri = AvatarUtilities.getUri(email);

        assertThat(uri).isEqualTo("https://api.adorable.io/avatars/150/" + email + ".png");
    }

}
