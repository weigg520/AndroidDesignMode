package top.weizhengzhou.androiddesignmode.chapter_one.image_loader;

import android.graphics.Bitmap;

import top.weizhengzhou.androiddesignmode.chapter_one.image_loader.interfac.ImageCache;

/**
 * Created by Administrator on 2018/4/22/022.
 */

public class DoubleCache implements ImageCache{
    ImageCache mMemoryCache = new MemoryCache();
    DiskCache mDiskCache = new DiskCache();

    //先从内存缓存中获取图片。如果没有，再从SD卡中获取
    public Bitmap get(String url){
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null){
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    //将图片缓存到内存和SD卡
    public void put(String url , Bitmap bmp){
        mMemoryCache.put(url , bmp);
        mDiskCache.put(url, bmp);
    }
}
