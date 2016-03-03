package cn.iwgang.parallaxguidedemo.PageTransformerDemo;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

public class DepthPageTransformer implements ViewPager.PageTransformer {


    //出来的时候图片的缩放程度
    private static final float MIN_SCALE = 0.5f;

    public void transformPage(View view, float position) {



        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left. 关闭屏幕左侧的时候
            view.setAlpha(0);

        }
        //显示的效果
        else if (position <= 0) { // [-1,0]
            //如果为空 会导致 viewpager 显示的为空
            // Use the default slide transition when moving to the left page 向左移动页面时使用默认的幻灯片切换
            //当前显示的界面 显示的效果
            view.setAlpha((float) 1);
            //图片右移动
            view.setTranslationX(0);
            view.setScaleX((float) 1);
            view.setScaleY((float) 1);

        }
        //图片出来的效果
        else if (position <= 1) { // (0,1]
            // Fade the page out. 淡出的页面了。出现的界面
            view.setAlpha(1 - position);

            // Counteract the default slide transition  抵消默认的幻灯片切换
            //默认的时一个出现 另外一个出现 现在表示为 两者之间有落差感
            view.setTranslationX(pageWidth * -position);
            //可以保持 手指的移动 与界面的移动之和  保持在整个界面之下
            Log.e("lxsetTranslationX", pageWidth * -position + "");


            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}