 package com.nexuslink.app;

 import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Environment;

import com.facebook.stetho.Stetho;
import com.nexuslink.DaoMaster;
import com.nexuslink.DaoSession;
import com.nexuslink.util.GlideImageLoader;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.io.File;

import cn.alien95.resthttp.request.RestHttp;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;


 /**
 * Created by ASUS-NB on 2016/12/17.
 */

public class BaseApplication extends Application {

    public static Context mContext;
    {
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("4258197523", "30268867be9ea03cd1f41c8a93f8795f");    }
    //===============================================数据库
    public static  SQLiteDatabase db;


    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        Config.DEBUG = true;
        mContext = getApplicationContext();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
        //创建数据库
        db = new DaoMaster.DevOpenHelper(mContext,"PopSport",null).getWritableDatabase();
        //舒适化图片加载库
        RestHttp.initialize(this);
        //初始化相册
        initGalleryFinal();
    }

     private void initGalleryFinal() {
         //设置主题
        //ThemeConfig.CYAN
        //绿色主题
         File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();
         //小米手机必须这样获得public绝对路径
         String filesName = "Pop图片";
         File appDir = new File(file ,filesName);

         ThemeConfig theme = new ThemeConfig.Builder()
                 .setTitleBarBgColor(Color.rgb(0x4C, 0xAF, 0x50))
                 .setFabNornalColor(Color.rgb(0x4C, 0xAF, 0x50))
                 .setFabPressedColor(Color.rgb(0x38, 0x8E, 0x3C))
                 .setCheckSelectedColor(Color.rgb(0x4C, 0xAF, 0x50))
                 .setCropControlColor(Color.rgb(0x4C, 0xAF, 0x50))
                 .build();
        //配置功能
         FunctionConfig functionConfig = new FunctionConfig.Builder()
                 .setMutiSelectMaxSize(15)
                 .setEnableCamera(true)
                 .setEnableEdit(true)
                 .setEnableCrop(true)
                 .setEnableRotate(true)
                 .setCropSquare(true)
                 .setEnablePreview(true)
                 .build();

        //配置imageloader
         ImageLoader imageloader = new GlideImageLoader();
         CoreConfig coreConfig = new CoreConfig.Builder(getApplicationContext(), imageloader, theme)
                 .setTakePhotoFolder(appDir)
                 .setFunctionConfig(functionConfig)
                .build();
         GalleryFinal.init(coreConfig);
     }

     /*
     提供全局context
      */
    public static Context getContext(){
        return mContext;
    }

     public static DaoSession getDaosession(){
         DaoSession daoSession;
         DaoMaster daoMaster;
         DaoMaster.DevOpenHelper helper;
         SQLiteDatabase database;
         helper = new DaoMaster.DevOpenHelper(BaseApplication.getContext(),"Pop-Db",null);
         database = helper.getWritableDatabase();
         daoMaster = new DaoMaster(database);
         daoSession = daoMaster.newSession();
         return daoSession;
     }
}
