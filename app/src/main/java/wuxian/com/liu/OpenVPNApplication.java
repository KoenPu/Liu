/**
  * Generated by smali2java 1.0.0.558
  * Copyright (C) 2013 Hensence.com
  */

package wuxian.com.liu;

import android.app.Application;
import android.content.Context;

public class OpenVPNApplication extends Application {
    public static Context context;
    
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    
    public static String resString(int res_id) {
        return context.getString(res_id);
    }
}
