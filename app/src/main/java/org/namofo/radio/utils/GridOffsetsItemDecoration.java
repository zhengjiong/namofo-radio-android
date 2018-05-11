package org.namofo.radio.utils;

import android.graphics.Rect;
import android.support.annotation.IntDef;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This class can only be used in the RecyclerView which use a GridLayoutManager
 * or StaggeredGridLayoutManager, but it's not always work for the StaggeredGridLayoutManager,
 * because we can't figure out which position should belong to the last column or the last row
 */
public class GridOffsetsItemDecoration extends RecyclerView.ItemDecoration {
    public static final int GRID_OFFSETS_HORIZONTAL = GridLayoutManager.HORIZONTAL;
    public static final int GRID_OFFSETS_VERTICAL = GridLayoutManager.VERTICAL;

    private final SparseArray<OffsetsCreator> mTypeOffsetsFactories = new SparseArray<>();

    @IntDef({
            GRID_OFFSETS_HORIZONTAL,
            GRID_OFFSETS_VERTICAL
    })
    @Retention(RetentionPolicy.SOURCE)
    private @interface Orientation {
    }

    @Orientation
    private int mOrientation;
    private int mVerticalItemOffsets;
    private int mHorizontalItemOffsets;

    private boolean mIsOffsetEdge;
    private boolean mIsOffsetLast;

    /**
     * -------------------------    NOTICE   README    -----------
     * You need know grid header type count. if Header count is singular, no problem.
     * If header count is double. I'm sorry There is a bug about Decoration.
     */
    private boolean mIsSingular;
    private int mSingular = 0;

    public GridOffsetsItemDecoration(@Orientation int orientation) {
        setOrientation(orientation);
        mIsOffsetLast = true;
        mIsOffsetEdge = true;
    }

    public void setOrientation(int orientation) {
        this.mOrientation = orientation;
    }

    public void setVerticalItemOffsets(int verticalItemOffsets) {
        this.mVerticalItemOffsets = verticalItemOffsets;
    }

    public void setHorizontalItemOffsets(int horizontalItemOffsets) {
        this.mHorizontalItemOffsets = horizontalItemOffsets;
    }

    public void setSingular(boolean singular) {
        mIsSingular = singular;
        mSingular = mIsSingular ? 1 : 0;
    }

    public void setSingular(int singular) {
        mSingular = singular % 2;
    }

    public void setOffsetEdge(boolean isOffsetEdge) {
        this.mIsOffsetEdge = isOffsetEdge;
    }

    public void setOffsetLast(boolean isOffsetLast) {
        this.mIsOffsetLast = isOffsetLast;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        int adapterPosition = parent.getChildAdapterPosition(view);
        int horizontalOffsets = getHorizontalOffsets(parent, view);
        int verticalOffsets = getVerticalOffsets(parent, view);

        boolean isFirstRow = isFirstRow(adapterPosition, spanCount, childCount);
        boolean isLastRow = isLastRow(adapterPosition, spanCount, childCount);
        boolean isFirstColumn = isFirstColumn(adapterPosition, spanCount, childCount);
        boolean isLastColumn = isLastColumn(adapterPosition, spanCount, childCount);

        outRect.set(0, 0, horizontalOffsets, verticalOffsets);
        outRect.bottom = mOrientation != GRID_OFFSETS_VERTICAL && isLastRow ? 0 : verticalOffsets;
        outRect.right = mOrientation != GRID_OFFSETS_HORIZONTAL && isLastColumn ? 0 : horizontalOffsets;

        if (mIsOffsetEdge) {
            outRect.top = isFirstRow ? verticalOffsets : 0;
            if (isFirstColumn) {
                outRect.right = horizontalOffsets / 2;
            } else {
                outRect.right = horizontalOffsets;
            }
            if (isLastColumn) {
                outRect.left = horizontalOffsets / 2;
            } else {
                outRect.left = horizontalOffsets;
            }
            outRect.bottom = verticalOffsets;
        }

        if (!mIsOffsetLast) {
            if (mOrientation == GRID_OFFSETS_VERTICAL && isLastRow) {
                outRect.bottom = 0;
            } else if (mOrientation == GRID_OFFSETS_HORIZONTAL && isLastColumn) {
                outRect.right = 0;
            }
        }
    }

    private int getHorizontalOffsets(RecyclerView parent, View view) {
        if (mTypeOffsetsFactories.size() == 0) {
            return mHorizontalItemOffsets;
        }

        final int adapterPosition = parent.getChildAdapterPosition(view);
        final int itemType = parent.getAdapter().getItemViewType(adapterPosition);
        final OffsetsCreator offsetsCreator = mTypeOffsetsFactories.get(itemType);

        if (offsetsCreator != null) {
            return offsetsCreator.createHorizontal(parent, adapterPosition);
        }

        return 0;
    }

    private int getVerticalOffsets(RecyclerView parent, View view) {
        if (mTypeOffsetsFactories.size() == 0) {
            return mVerticalItemOffsets;
        }

        final int adapterPosition = parent.getChildAdapterPosition(view);
        final int itemType = parent.getAdapter().getItemViewType(adapterPosition);
        final OffsetsCreator offsetsCreator = mTypeOffsetsFactories.get(itemType);

        if (offsetsCreator != null) {
            return offsetsCreator.createVertical(parent, adapterPosition);
        }

        return 0;
    }

    private boolean isFirstColumn(int position, int spanCount, int childCount) {
        if (mOrientation == GRID_OFFSETS_VERTICAL) {
            return position % spanCount == mSingular;
        } else {
            int lastColumnCount = childCount % spanCount;
            lastColumnCount = lastColumnCount == 0 ? spanCount : lastColumnCount;
            return position < childCount - lastColumnCount;
        }
    }

    private boolean isLastColumn(int position, int spanCount, int childCount) {
        if (mOrientation == GRID_OFFSETS_VERTICAL) {
            return (position + 1) % spanCount == mSingular;
        } else {
            int lastColumnCount = childCount % spanCount;
            lastColumnCount = lastColumnCount == 0 ? spanCount : lastColumnCount;
            return position >= childCount - lastColumnCount;
        }
    }

    private boolean isFirstRow(int position, int spanCount, int childCount) {
        if (mOrientation == GRID_OFFSETS_VERTICAL) {
            return position < spanCount;
        } else {
            return position % spanCount == 0;
        }
    }

    private boolean isLastRow(int position, int spanCount, int childCount) {
        if (mOrientation == GRID_OFFSETS_VERTICAL) {
            int lastColumnCount = childCount % spanCount;
            lastColumnCount = lastColumnCount == 0 ? spanCount : lastColumnCount;
            return position >= childCount - lastColumnCount;
        } else {
            return (position + 1) % spanCount == 0;
        }
    }

    private int getSpanCount(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        } else {
            throw new UnsupportedOperationException("the GridDividerItemDecoration can only be used in " +
                    "the RecyclerView which use a GridLayoutManager or StaggeredGridLayoutManager");
        }
    }

    public void registerTypeDrawable(int itemType, OffsetsCreator offsetsCreator) {
        mTypeOffsetsFactories.put(itemType, offsetsCreator);
    }

    public interface OffsetsCreator {
        int createVertical(RecyclerView parent, int adapterPosition);

        int createHorizontal(RecyclerView parent, int adapterPosition);
    }

    public static RecyclerView.ItemDecoration createOffsetsItemDecoration() {
        int offset = ConvertUtils.dp2px(4F);
        GridOffsetsItemDecoration offsetsItemDecoration = new GridOffsetsItemDecoration(
                GridOffsetsItemDecoration.GRID_OFFSETS_VERTICAL);
        offsetsItemDecoration.setVerticalItemOffsets(offset);
        offsetsItemDecoration.setHorizontalItemOffsets(offset);
        offsetsItemDecoration.setOffsetEdge(true);
        offsetsItemDecoration.setOffsetLast(true);
        offsetsItemDecoration.setSingular(3);
        offsetsItemDecoration.registerTypeDrawable(0, new GridOffsetsItemDecoration.OffsetsCreator() {
            @Override
            public int createVertical(RecyclerView parent, int adapterPosition) {
                boolean isFirstRow = offsetsItemDecoration.isFirstRow(adapterPosition, offsetsItemDecoration.getSpanCount(parent), parent.getAdapter().getItemCount());
                if (isFirstRow) {
                    return 0;
                } else {
                    return offset;
                }
            }

            @Override
            public int createHorizontal(RecyclerView parent, int adapterPosition) {
                if (null != parent.getAdapter() && parent.getAdapter().getItemViewType(adapterPosition) == 0) {
                    boolean isFirstColumn = offsetsItemDecoration.isFirstColumn(adapterPosition, offsetsItemDecoration.getSpanCount(parent), parent.getAdapter().getItemCount());
                    if (isFirstColumn) {
                        return 0;
                    } else {
                        return offset;
                    }
                } else {
                    return 0;
                }
            }
        });
        return offsetsItemDecoration;
    }

}
