package com.example.celllayout;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;

public class SmoothHorizontalScrollView extends HorizontalScrollView {

	public SmoothHorizontalScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public SmoothHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public SmoothHorizontalScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	private onScrollPageListener mListener;
	private int mScrollXDelta;
	
	public interface onScrollPageListener{
		public void onScrollPage(boolean toRight);
	}
	
	public void setOnScrollPageListener(onScrollPageListener listener){
		mListener = listener;
	}
	
	public int getScrollXDelta() {
		return mScrollXDelta;
	}
	
	@Override
	protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
		mScrollXDelta = 0;
		if (getChildCount() == 0) return mScrollXDelta;

        int width = getWidth();
        int screenLeft = getScrollX();
        int screenRight = screenLeft + width;

        int fadingEdge = getHorizontalFadingEdgeLength();

        // leave room for left fading edge as long as rect isn't at very left
        if (rect.left > 0) {
            screenLeft += fadingEdge;
        }

        // leave room for right fading edge as long as rect isn't at very right
        if (rect.right < getChildAt(0).getWidth()) {
            screenRight -= fadingEdge;
        }

        if (rect.right > screenRight && rect.left > screenLeft) {
            // need to move right to get it in view: move right just enough so
            // that the entire rectangle is in view (or at least the first
            // screen size chunk).

//            if (rect.width() > width) {
//                // just enough to get screen size chunk on
//                scrollXDelta += (rect.left - screenLeft);
//            } else {
                // get entire rect at right of screen
                mScrollXDelta += rect.left - screenLeft;//(rect.right - screenRight);
//            }

            // make sure we aren't scrolling beyond the end of our content
            int right = getChildAt(0).getRight();
            int distanceToRight = right - screenRight;
            mScrollXDelta = Math.min(mScrollXDelta, distanceToRight);
            if (mListener != null){
            	mListener.onScrollPage(true);
            }

        } else if (rect.left < screenLeft && rect.right < screenRight) {
            // need to move right to get it in view: move right just enough so that
            // entire rectangle is in view (or at least the first screen
            // size chunk of it).

//            if (rect.width() > width) {
//                // screen size chunk
//                scrollXDelta -= (screenRight - rect.right);
//            } else {
                // entire rect at left
                mScrollXDelta -= screenRight - rect.right;//(screenLeft - rect.left);
//            }

            // make sure we aren't scrolling any further than the left our content
            mScrollXDelta = Math.max(mScrollXDelta, -getScrollX());
            if (mListener != null){
            	mListener.onScrollPage(false);
            }
        }
        return mScrollXDelta;
	}
	@Override
	protected void onDetachedFromWindow() {
		Log.e("wangbo", "SmoothHorizontalScrollView onDetachedFromWindow");
		super.onDetachedFromWindow();
	}
	
	@Override
	protected void onAttachedToWindow() {
		Log.e("wangbo", "SmoothHorizontalScrollView onAttachedToWindow");
		super.onAttachedToWindow();
	}
}
