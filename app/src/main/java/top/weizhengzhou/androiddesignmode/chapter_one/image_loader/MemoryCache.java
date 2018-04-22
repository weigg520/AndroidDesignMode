package top.weizhengzhou.androiddesignmode.chapter_one.image_loader;

import android.graphics.Bitmap;
import android.util.LruCache;

import top.weizhengzhou.androiddesignmode.chapter_one.image_loader.interfac.ImageCache;

/**
 * Created by Administrator on 2018/4/22/022.
 */

public class MemoryCache implements ImageCache {
    //图片缓存
    LruCache<String ,Bitmap> mLruCache;

    public MemoryCache(){
        initImageCache();
    }
    @Override
    public Bitmap get(String url) {
        return mLruCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bmp) {
        mLruCache.put(url , bmp);
    }

    //初始化内存池
    private void initImageCache() {
        //计算可以使用的最大内存
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
        //取四分之一
        final int cacheSize = maxMemory / 4;
        mLruCache = new LruCache<String , Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() /1024;
            }
        };
    }
}
