package com.junhuayu.yanxuandemo.VerticalViewPager;

import android.support.v4.view.ViewPager;
import android.view.View;

public class VerticalTransformer implements ViewPager.PageTransformer{

    @Override
    public void transformPage(View view, float position) {
        view.setTranslationX(view.getWidth() * -position);
        float yPosition = position * view.getHeight();
        view.setTranslationY(yPosition);
    }
}
