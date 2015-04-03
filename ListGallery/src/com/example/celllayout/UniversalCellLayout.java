package com.example.celllayout;
/*
 * Method addViewToPage() determine the location and size of childView.
 * Every child view in CellLayout based on consistent brick (same width/height unit)
 */
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;

public class UniversalCellLayout extends ViewGroup {
	private static final int ITEM_COUNT_Y_DEFAULT = 2;
	private static final int ITEM_COUNT_X_DEFAULT = 6;
	private static final int GAP_BETWEEN_GAPS = 8;
	
    private int mCellWidth = 200;
    private int mCellHeight = 220;

    private int mCountX = ITEM_COUNT_X_DEFAULT;
    private int mCountY = ITEM_COUNT_Y_DEFAULT;
    
    private int mWidthGap = GAP_BETWEEN_GAPS;
    private int mHeightGap = GAP_BETWEEN_GAPS;
    
	public UniversalCellLayout(Context context) {
		super(context);
	}

	public UniversalCellLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public UniversalCellLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                PageItemLayoutParams lp = (PageItemLayoutParams) child.getLayoutParams();

                int childLeft = lp.x + getPaddingLeft();
                int childTop = lp.y + getPaddingTop();
                child.layout(childLeft, childTop, childLeft + lp.width, childTop + lp.height);
            }
        }
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
		
		int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
		
		if (widthSpecMode == MeasureSpec.UNSPECIFIED || heightSpecMode == MeasureSpec.UNSPECIFIED) {
//            throw new RuntimeException("CellLayout cannot have UNSPECIFIED dimensions");
        }
		
		// Initial values correspond to widthSpecMode == MeasureSpec.EXACTLY
        int newWidth = widthSpecSize;
        int newHeight = heightSpecSize;
        
        //set the measurement of CellLayout
        //the parent of page may be scroll-view ,which has unspecified measurement
//        if (widthSpecMode == MeasureSpec.AT_MOST) {
        newWidth = getPaddingLeft() + getPaddingRight() + (mCountX * mCellWidth) +
            ((mCountX - 1) * mWidthGap);
        newHeight = getPaddingTop() + getPaddingBottom() + (mCountY * mCellHeight) +
            ((mCountY - 1) * mHeightGap);
        setMeasuredDimension(newWidth, newHeight);
//        }
        
        //set the measurement of child view, like item view
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child);
        }
	}
	
	private void measureChild(View child){
		final int cellWidth = mCellWidth;
        final int cellHeight = mCellHeight;
        PageItemLayoutParams lp = (PageItemLayoutParams) child.getLayoutParams();

        //calculate the location and width/height of item
        lp.setup(cellWidth, cellHeight, mWidthGap, mHeightGap, false, mCountX);
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(lp.width, MeasureSpec.EXACTLY);
        int childheightMeasureSpec = MeasureSpec.makeMeasureSpec(lp.height,
                MeasureSpec.EXACTLY);
        child.measure(childWidthMeasureSpec, childheightMeasureSpec);
	}
	
	/*
	 * Method to add the child view to specific location with its size 
	 * @child  child view to be added to CellLayout
	 * @ x    the x position of child view, x-axis value, mCellWidth as the x-axis unit length
	 * @ y    the y position of child view, y-axis value, mCellWidth as the y-axis unit length
	 * @spanX the size of child view in x-axis
	 * @spanY the size of child view in y-axis 
	 */
	public boolean addViewToPage(View child, int x, int y, int spanX, int spanY){
		ViewGroup.LayoutParams genericLp = child.getLayoutParams();
		PageItemLayoutParams lp;
		if (genericLp == null || !(genericLp instanceof PageItemLayoutParams)){
			lp = new PageItemLayoutParams(x, y, spanX, spanY);
		} else {
			lp = (PageItemLayoutParams)genericLp;
			lp.cellX = x;
			lp.cellY = y;
			lp.cellHSpan = spanX;
			lp.cellVSpan = spanY;
		}
		
        if (lp.cellX >= 0 && lp.cellX <= mCountX - 1 && lp.cellY >= 0 && lp.cellY <= mCountY - 1) {
            // If the horizontal or vertical span is set to -1, it is taken to
            // mean that it spans the extent of the CellLayout
            if (lp.cellHSpan < 0) lp.cellHSpan = mCountX;
            if (lp.cellVSpan < 0) lp.cellVSpan = mCountY;

            addView(child, -1, lp);

            return true;
        }
        return false;
	}
	
	public static class PageItemLayoutParams extends ViewGroup.MarginLayoutParams {
        /**
         * Horizontal location of the item in the grid.
         */
        @ViewDebug.ExportedProperty
        public int cellX;

        /**
         * Vertical location of the item in the grid.
         */
        @ViewDebug.ExportedProperty
        public int cellY;

        /**
         * Number of cells spanned horizontally by the item.
         */
        @ViewDebug.ExportedProperty
        public int cellHSpan;

        /**
         * Number of cells spanned vertically by the item.
         */
        @ViewDebug.ExportedProperty
        public int cellVSpan;

        // X coordinate of the view in the layout.
        @ViewDebug.ExportedProperty
        int x;
        // Y coordinate of the view in the layout.
        @ViewDebug.ExportedProperty
        int y;

        public PageItemLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            cellHSpan = 1;
            cellVSpan = 1;
        }

        public PageItemLayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            cellHSpan = 1;
            cellVSpan = 1;
        }

        public PageItemLayoutParams(PageItemLayoutParams source) {
            super(source);
            this.cellX = source.cellX;
            this.cellY = source.cellY;
            this.cellHSpan = source.cellHSpan;
            this.cellVSpan = source.cellVSpan;
        }

        public PageItemLayoutParams(int cellX, int cellY, int cellHSpan, int cellVSpan) {
            super(PageItemLayoutParams.MATCH_PARENT, PageItemLayoutParams.MATCH_PARENT);
            this.cellX = cellX;
            this.cellY = cellY;
            this.cellHSpan = cellHSpan;
            this.cellVSpan = cellVSpan;
        }

        public void setup(int cellWidth, int cellHeight, int widthGap, int heightGap,
                boolean invertHorizontally, int colCount) {
            /*if (invertHorizontally) {
                myCellX = colCount - myCellX - cellHSpan;
            }*/

            width = cellHSpan * cellWidth + ((cellHSpan - 1) * widthGap) -
                    leftMargin - rightMargin;
            height = cellVSpan * cellHeight + ((cellVSpan - 1) * heightGap) -
                    topMargin - bottomMargin;
            x = (int) (cellX * (cellWidth + widthGap) + leftMargin);
            y = (int) (cellY * (cellHeight + heightGap) + topMargin);
        }

        public String toString() {
            return "(" + this.cellX + ", " + this.cellY + ")";
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getWidth() {
            return width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getHeight() {
            return height;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getX() {
            return x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getY() {
            return y;
        }
    }
}
