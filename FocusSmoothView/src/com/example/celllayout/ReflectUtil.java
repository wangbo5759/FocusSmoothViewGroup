package com.example.celllayout;
import android.view.View;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtil {

	 public static boolean getBooleanField(Class paramClass, String paramString)
	  {
	    try
	    {
	      boolean bool = paramClass.getField(paramString).getBoolean(paramClass);
	      return bool;
	    }
	    catch (NoSuchFieldException localNoSuchFieldException)
	    {
	      localNoSuchFieldException.printStackTrace();
	      return false;
	    }
	    catch (IllegalArgumentException localIllegalArgumentException)
	    {
	      localIllegalArgumentException.printStackTrace();
	      return false;
	    }
	    catch (IllegalAccessException localIllegalAccessException)
	    {
	      localIllegalAccessException.printStackTrace();
	    }
	    return false;
	  }

	 public static int getIntField(Object paramObject, Class paramClass, String paramString)
	 {
	    try
	    {
	      Field localField = paramClass.getDeclaredField(paramString);
	      localField.setAccessible(true);
	      int i = localField.getInt(paramClass);
	      return i;
	    }
	    catch (IllegalAccessException localIllegalAccessException)
	    {
	      return 0;
	    }
	    catch (IllegalArgumentException localIllegalArgumentException)
	    {
	      return 0;
	    }
	    catch (NoSuchFieldException localNoSuchFieldException)
	    {
	    }
	    return 0;
	 }
	 public static void setBooleanField(Class paramClass, String paramString, boolean paramBoolean)
	  {
	    try
	    {
	      paramClass.getField(paramString).setBoolean(paramClass, paramBoolean);
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

	  public static void setIntField(Class paramClass, String paramString, int paramInt)
	  {
	    try
	    {
	      paramClass.getField(paramString).setInt(paramClass, paramInt);
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
	  
	  public static boolean view_isRootNamespace(Object paramObject)
	  {	Method method;
	    try
	    {
	      method = View.class.getDeclaredMethod("isRootNamespace");
	      method.setAccessible(true);
	      return ((Boolean)method.invoke(paramObject, null)).booleanValue();
	    } catch (IllegalArgumentException localIllegalArgumentException){
	      localIllegalArgumentException.printStackTrace();
	      return false;
	    } catch (IllegalAccessException localIllegalAccessException){
	      localIllegalAccessException.printStackTrace();
	      return false;
	    } catch (InvocationTargetException localInvocationTargetException) {
	      localInvocationTargetException.printStackTrace();
	    } catch (NoSuchMethodException noSuchMethodException){
	    	noSuchMethodException.printStackTrace();
	    }
	    return false;
	  }

	  public static boolean invokeMethodForBoolean(Class invokeClass, String methodName, Object paramObject){
		  	Method method;
		    try
		    {
		      method = invokeClass.getDeclaredMethod(methodName);
		      method.setAccessible(true);
		      if ((Boolean)method.invoke(paramObject, null) != null){
		    	  return ((Boolean)method.invoke(paramObject, null)).booleanValue();
		      }
		    } catch (IllegalArgumentException localIllegalArgumentException){
		      localIllegalArgumentException.printStackTrace();
		      return false;
		    } catch (IllegalAccessException localIllegalAccessException){
		      localIllegalAccessException.printStackTrace();
		      return false;
		    } catch (InvocationTargetException localInvocationTargetException) {
		      localInvocationTargetException.printStackTrace();
		    } catch (NoSuchMethodException noSuchMethodException){
		    	noSuchMethodException.printStackTrace();
		    }
		    return false;
	  }
	  public static float invokeMethodForFloat(Class invokeClass, String methodName, Object paramObject){
		  	Method method;
		    try
		    {
		      method = invokeClass.getDeclaredMethod(methodName);
		      method.setAccessible(true);
		      return ((Float)method.invoke(paramObject, null)).floatValue();
		    } catch (IllegalArgumentException localIllegalArgumentException){
		      localIllegalArgumentException.printStackTrace();
		      return 0f;
		    } catch (IllegalAccessException localIllegalAccessException){
		      localIllegalAccessException.printStackTrace();
		      return 0f;
		    } catch (InvocationTargetException localInvocationTargetException) {
		      localInvocationTargetException.printStackTrace();
		    } catch (NoSuchMethodException noSuchMethodException){
		    	noSuchMethodException.printStackTrace();
		    }
		    return 0f;
	  }
	  /*public static boolean view_setFrame(Object paramObject, Class paramClass, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	  {
	    try
	    {
	      Method[] arrayOfMethod = paramClass.getDeclaredMethods();
	      int i = arrayOfMethod.length;
	      for (int j = 0; ; j++)
	      {
	        Object localObject = null;
	        if (j < i)
	        {
	          Method localMethod = arrayOfMethod[j];
	          if ("setFrame".equals(localMethod.getName()))
	            localObject = localMethod;
	        }
	        else
	        {
	          boolean bool1 = false;
	          if (localObject != null)
	          {
	            Object[] arrayOfObject = new Object[4];
	            arrayOfObject[0] = new Integer(paramInt1);
	            arrayOfObject[1] = new Integer(paramInt2);
	            arrayOfObject[2] = new Integer(paramInt3);
	            arrayOfObject[3] = new Integer(paramInt4);
//	            boolean bool2 = ((Boolean)localObject.invoke(paramObject, arrayOfObject)).booleanValue();
	            bool1 = bool2;
	          }
	          return bool1;
	        }
	      }
	    }
	    catch (IllegalArgumentException localIllegalArgumentException)
	    {
	      localIllegalArgumentException.printStackTrace();
	      return false;
	    }
	    catch (IllegalAccessException localIllegalAccessException)
	    {
	      localIllegalAccessException.printStackTrace();
	      return false;
	    }
	    catch (InvocationTargetException localInvocationTargetException)
	    {
	      localInvocationTargetException.printStackTrace();
	    }
	    return false;
	  }*/
}
