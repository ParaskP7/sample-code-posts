package io.petros.posts.util;

import static io.petros.posts.util.GeneralUtilities.SLASH;

public final class AvatarUtilities {

    private static final String PROFILE_PIC_URL_PREFIX = "https://api.adorable.io/avatars";
    private static final int PROFILE_PIC_SIZE = 150;
    private static final String PROFILE_PIC_URL_SUFFIX = ".png";

    private AvatarUtilities() {
        throw new AssertionError();
    }

    public static String getUri(final String email) {
        return PROFILE_PIC_URL_PREFIX + SLASH + PROFILE_PIC_SIZE + SLASH + email + PROFILE_PIC_URL_SUFFIX;
    }

}
