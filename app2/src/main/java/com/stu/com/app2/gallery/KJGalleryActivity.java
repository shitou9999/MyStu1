package com.stu.com.app2.gallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.stu.com.app2.R;

/**
 * 简略版ViewPager
 */
public class KJGalleryActivity extends Activity {

    public static final String URL_KEY = "KJGalleryActivity_url";
    public static final String URL_INDEX = "KJGalleryActivity_index";

    private TextView textView;

    private String[] imageUrls;
    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kjgallery);

        Intent from = getIntent();
        imageUrls = from.getStringArrayExtra(URL_KEY);
        index = from.getIntExtra(URL_INDEX, 0);
        initWidget();
    }

    public void initWidget() {
        textView = (TextView) findViewById(R.id.page_title);
        if (imageUrls.length < 2) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(String.format("%d/%d", index + 1, imageUrls.length));
        }

        HackyViewPager mViewPager = (HackyViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new SamplePagerAdapter(this, imageUrls));
        mViewPager.setCurrentItem(index);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                textView.setText(String.format("%d/%d", position + 1, imageUrls.length));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public static void toGallery(Context cxt, int index, String... urls) {
        for (String url : urls) {
            if (TextUtils.isEmpty(url)) {
                return;
            }
        }
        Intent intent = new Intent();
        intent.putExtra(KJGalleryActivity.URL_INDEX, index);
        intent.putExtra(KJGalleryActivity.URL_KEY, urls);
        intent.setClass(cxt, KJGalleryActivity.class);
        cxt.startActivity(intent);
    }

    public static void toGallery(Context cxt, String... urls) {
        toGallery(cxt, 0, urls);
    }
}