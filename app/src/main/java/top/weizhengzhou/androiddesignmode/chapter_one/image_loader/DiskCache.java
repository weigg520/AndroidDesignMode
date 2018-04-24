package top.weizhengzhou.androiddesignmode.chapter_one.image_loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import top.weizhengzhou.androiddesignmode.chapter_one.image_loader.interfac.ImageCache;

/**
 * Created by Administrator on 2018/4/17/017.
 */

public class DiskCache implements ImageCache{
    private final String TAG = DiskCache.class.getSimpleName();

    private static String cacheDir = Environment.getDownloadCacheDirectory().getAbsolutePath()+ "/";

    //从缓存中获取图片
    public Bitmap get(String url){
        return BitmapFactory.decodeFile(cacheDir + url.replaceAll("/" , "x"));
    }

    //将图片缓存到内存
    public void put(String url , Bitmap bmp){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(cacheDir + url);
            Log.e(TAG , cacheDir + url);
            bmp.compress(Bitmap.CompressFormat.PNG , 100 , fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
