package io.petros.posts.util;

public final class WhiteboxTestUtilities {

    // App specific.
    public static final String APPLICATION_COMPONENT = "applicationComponent";

    // View specific.
    public static final String POST_RECYCLER_VIEW_ADAPTER = "postRecyclerViewAdapter";

    // Dialog specific.
    public static final String PROGRESS_DIALOG = "progressDialog";

    // Model specific.
    public static final String ALL_POSTS = "allPosts";

    // ROBOLECTRIC // ******************************************************************************************************************************************

    public static final String SDK_INT = "SDK_INT";

    private WhiteboxTestUtilities() {
        throw new AssertionError();
    }

}
