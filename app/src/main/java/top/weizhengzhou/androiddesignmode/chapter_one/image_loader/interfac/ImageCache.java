package top.weizhengzhou.androiddesignmode.chapter_one.image_loader.interfac;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2018/4/22/022.
 */

public interface ImageCache {
    Bitmap get(String url);
    void put(String url , Bitmap bmp);
}
