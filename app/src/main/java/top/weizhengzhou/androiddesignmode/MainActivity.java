package top.weizhengzhou.androiddesignmode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import top.weizhengzhou.androiddesignmode.chapter_one.image_loader.ImageLoader;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.image_view);
        imageLoader = new ImageLoader();
        imageLoader.displayImage("http://img.zcool.cn/community/0142135541fe180000019ae9b8cf86.jpg@1280w_1l_2o_100sh.png" , imageView);
    }
}
