package com.example.celllayout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;

import java.lang.reflect.Field;


public class ViewCompat
{
  private static IViewCompat sImpl = new ViewCompatDonut();
  protected static  int translateFrom ;
  protected static  int translateTo ;

  static
  {
    if (11 <= Build.VERSION.SDK_INT)
    {
      sImpl = new ViewCompatHoneycomb();
    }
  }

  public static void offsetChildrenLeftAndRight(ViewGroup paramViewGroup, int paramInt)
  {
    sImpl.offsetChildrenLeftAndRight(paramViewGroup, paramInt);
  }

  public static void offsetChildrenTopAndBottom(ViewGroup paramViewGroup, int paramInt)
  {
    sImpl.offsetChildrenTopAndBottom(paramViewGroup, paramInt);
  }

  public static void setIntField(Object paramObject, String paramString, int paramInt)
  {
    try
    {
      Field localField = View.class.getDeclaredField(paramString);
      localField.setAccessible(true);
      localField.setInt(paramObject, paramInt);
      return;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      localNoSuchFieldException.printStackTrace();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
    }
  }

  static class ViewCompatDonut implements IViewCompat
  {
	    public void offsetChildrenLeftAndRight(ViewGroup paramViewGroup, int paramInt) {
		      int i = paramViewGroup.getChildCount();
		      for (int j = 0; j < i; j++)
		      {
		        View localView = paramViewGroup.getChildAt(j);
		        ViewCompat.setIntField(localView, "mLeft", paramInt + localView.getLeft());
		        ViewCompat.setIntField(localView, "mRight", paramInt + localView.getRight());
		      }
		      
	    }

	    public void offsetChildrenTopAndBottom(ViewGroup paramViewGroup, int paramInt) {
		      int i = paramViewGroup.getChildCount();
		      for (int j = 0; j < i; j++)
		      {
		        View localView = paramViewGroup.getChildAt(j);
		        ViewCompat.setIntField(localView, "mTop", paramInt + localView.getTop());
		        ViewCompat.setIntField(localView, "mBottom", paramInt + localView.getBottom());
		      }
	    }
  }

  static class ViewCompatHoneycomb implements IViewCompat
  {
    @SuppressLint({"NewApi"})
    public void offsetChildrenLeftAndRight(ViewGroup paramViewGroup, int paramInt)
    {
	      int i = paramViewGroup.getChildCount();
	      for (int j = 0; j < i; j++)
	      {
		        View localView = paramViewGroup.getChildAt(j);
		        localView.setLeft(paramInt + localView.getLeft());
		        localView.setRight(paramInt + localView.getRight());
	      }
    }

    @SuppressLint({"NewApi"})
    public void offsetChildrenTopAndBottom(ViewGroup paramViewGroup, int paramInt)
    {	 
    	
    	  int i = paramViewGroup.getChildCount();
	      for (int j = 0; j < i; j++)
	      {
		        final View localView = paramViewGroup.getChildAt(j);
		        localView.setTop(paramInt + localView.getTop());
		        localView.setBottom(paramInt + localView.getBottom());
	      }
	      
	      
    }

	  static class ViewCompatNull implements IViewCompat
	  {
		    public void offsetChildrenLeftAndRight(ViewGroup paramViewGroup, int paramInt)
		    {
			      int i = paramViewGroup.getChildCount();
			      for (int j = 0; j < i; j++)
			        paramViewGroup.getChildAt(j).offsetLeftAndRight(paramInt);
		    }
		
		    public void offsetChildrenTopAndBottom(ViewGroup paramViewGroup, int paramInt)
		    {
			      int i = paramViewGroup.getChildCount();
			      for (int j = 0; j < i; j++)
			        paramViewGroup.getChildAt(j).offsetTopAndBottom(paramInt);
		    }
	  }
  }
}
