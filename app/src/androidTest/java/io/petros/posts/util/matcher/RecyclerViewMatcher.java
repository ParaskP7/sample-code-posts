package io.petros.posts.util.matcher;

import android.view.View;

import org.hamcrest.Matcher;

public class RecyclerViewMatcher {

    private final int recyclerViewId;

    public RecyclerViewMatcher(final int recyclerViewId) {
        this.recyclerViewId = recyclerViewId;
    }

    public Matcher<View> atPosition(final int position) {
        return atPositionOnView(position, -1);
    }

    public Matcher<View> atPositionOnView(final int position, final int targetViewId) {
        return new RecyclerTypeSafeMatcher(recyclerViewId, position, targetViewId);
    }

}
