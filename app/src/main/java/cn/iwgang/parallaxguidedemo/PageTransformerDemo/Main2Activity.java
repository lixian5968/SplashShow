package cn.iwgang.parallaxguidedemo.PageTransformerDemo;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.parallaxguidedemo.R;

public class Main2Activity extends AppCompatActivity {

    private ViewPager mViewPager;
    private int[] mImgIds = new int[]{R.drawable.android_guide_step_1,
            R.drawable.android_guide_step_2, R.drawable.android_guide_step_3};
    private List<ImageView> mImageViews = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initData();
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mViewPager.setPageTransformer(true, new DepthPageTransformer());
//        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                container.addView(mImageViews.get(position));
                return mImageViews.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {

                container.removeView(mImageViews.get(position));
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public int getCount() {
                return mImgIds.length;
            }
        });

    }

    private void initData() {
        for (int imgId : mImgIds) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imgId);
            mImageViews.add(imageView);
        }
    }

}
