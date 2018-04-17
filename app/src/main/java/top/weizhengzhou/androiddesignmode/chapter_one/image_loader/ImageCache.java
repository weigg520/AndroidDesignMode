package top.weizhengzhou.androiddesignmode.chapter_one.image_loader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Administrator on 2018/4/16/016.
 */

public class ImageCache {
    //图片缓存
    LruCache<String ,Bitmap> mImageCache;

    public ImageCache(){
        initImageCache();
    }

    /**
     * 初始化
     */
    private void initImageCache() {
        //计算可以使用的最大内存
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
        //取四分之一
        final int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String , Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() /1024;
            }
        };
    }

    public void put(String url , Bitmap bitmap){
        mImageCache.put(url , bitmap);
    }

    public Bitmap get(String url){
        return mImageCache.get(url);
    }
}
