package io.petros.posts.util.matcher;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class RecyclerTypeSafeMatcher extends TypeSafeMatcher<View> {

    private final int recyclerViewId;
    private final int position;
    private final int targetViewId;

    private Resources resources = null;
    private View childItemView;

    public RecyclerTypeSafeMatcher(final int recyclerViewId, final int position, final int targetViewId) {
        this.recyclerViewId = recyclerViewId;
        this.position = position;
        this.targetViewId = targetViewId;
    }

    @Override
    protected boolean matchesSafely(final View itemView) {
        resources = itemView.getResources();
        if (childItemView == null) {
            final RecyclerView recyclerView = (RecyclerView) itemView.getRootView().findViewById(recyclerViewId);
            if (recyclerView != null && recyclerView.getId() == recyclerViewId) {
                childItemView = recyclerView.findViewHolderForAdapterPosition(position).itemView;
            } else {
                return false;
            }
        }

        if (targetViewId == -1) {
            return itemView == childItemView;
        } else {
            final View targetView = childItemView.findViewById(targetViewId);
            return itemView == targetView;
        }
    }

    @Override
    public void describeTo(final Description description) {
        String idDescription = Integer.toString(recyclerViewId);
        if (this.resources != null) {
            try {
                idDescription = this.resources.getResourceName(recyclerViewId);
            } catch (final Resources.NotFoundException rnfe) {
                idDescription = String.format("%s (resource name not found)",
                        new Object[]{Integer.valueOf(recyclerViewId)});
            }
        }
        description.appendText("with id: " + idDescription);
    }

}
