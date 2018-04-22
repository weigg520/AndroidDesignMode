package top.weizhengzhou.androiddesignmode.chapter_one.image_loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import top.weizhengzhou.androiddesignmode.R;
import top.weizhengzhou.androiddesignmode.chapter_one.image_loader.interfac.ImageCache;

/**
 * Created by wzz on 2018/4/16/016.
 */

public class ImageLoader {
    private final String TAG = ImageLoader.class.getSimpleName();
    //内存缓存
    private ImageCache mImageCache;
    //线程池数量为CPU数量
    private ExecutorService mExecutorService ;
    //注入实现
    public void setImageCache(ImageCache cache){
        mImageCache = cache;
    }

    //初始化
    public ImageLoader(){
        mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }


    /**
     * 显示图片,并缓存
     * @param url
     * @param imageView
     */
    public void displayImage(final String url , final ImageView imageView){
        //判断使用哪一种缓存
        Bitmap bitmap =mImageCache.get(url);
        if (bitmap != null){
            imageView.setImageBitmap(bitmap);
            return;
        }
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null || bitmap.getRowBytes() == 0){
                    Log.e(TAG , "图片为空");
                    return;
                }
                if (imageView.getTag().equals(url)){
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(url , bitmap);
            }
        });
    }

    /**
     * 下载图片
     * @param imageUrl
     * @return
     */
    private Bitmap downloadImage(String imageUrl) {
       Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    //第三方框架显示
    public void displayImage(Context context ,ImageView imageView, String imageUrl){
        RequestOptions options = new RequestOptions()
                .error(R.mipmap.ic_launcher);
        Glide.with(context).load(imageUrl).apply(options).into(imageView);
    }

}
