package io.petros.posts.util;

import io.petros.posts.util.matcher.RecyclerViewMatcher;

/**
 * Showcasing how custom matcher can be utilised.
 */
public final class MatcherEspressoUtilities {

    private MatcherEspressoUtilities() {
        throw new AssertionError();
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

}
