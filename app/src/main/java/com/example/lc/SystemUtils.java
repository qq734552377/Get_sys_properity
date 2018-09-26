package com.example.lc;

import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Method;

public class SystemUtils {

    private final static String TAG = "SystemUtils";

    static{
        System.loadLibrary("native-lib");
    }


    public static String getNativeString(){
        return getString();
    }


    public static String getSystemPropertyForJava(String key,String defVal){
        try {
            Class clazz = Class.forName("android.os.SystemProperties");
            Method getter = clazz.getDeclaredMethod("get", String.class);
            String value = (String) getter.invoke(null, key);
            if (!TextUtils.isEmpty(value)) {
                return value;
            }
        } catch (Exception e) {
            Log.d(TAG, "Unable to read system properties");
        }
        return defVal;

    }
    public static void setSystemPropertyForJava(String key, String value) {
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method set = c.getMethod("set", String.class, String.class);
            set.invoke(c, key, value );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static String getSystemPropertyForNative(String key,String defVal){
        return getProperity(key,defVal);
    }


    public static void setSystemPropertyForNative(String key,String defVal){
        setProperity(key,defVal);
    }


    private static native String getString();

    private static native String getProperity(String key,String defVal);
    private static native void setProperity(String key,String defVal);

}
