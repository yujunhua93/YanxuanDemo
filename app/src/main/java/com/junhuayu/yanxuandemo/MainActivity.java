package com.junhuayu.yanxuandemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.junhuayu.yanxuandemo.VerticalViewPager.ScrollUtils;
import com.junhuayu.yanxuandemo.VerticalViewPager.VerticalTransformer;
import com.junhuayu.yanxuandemo.VerticalViewPager.VerticalViewPager;
import com.junhuayu.yanxuandemo.adapter.CateContentAdapter;
import com.junhuayu.yanxuandemo.adapter.LeftRvAdapter;
import com.junhuayu.yanxuandemo.beans.Category;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LeftRvAdapter.OnItemClickListener,LeftRvAdapter.OnItemCheckListener,CateContentAdapter.OnDragListener{

    private RecyclerView leftRv;

    private VerticalViewPager verticalViewPager;

    private List<Category> list;

    private LeftRvAdapter leftRvAdapter;
    private CateContentAdapter pagerAdapter;

    private int itemPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leftRv = findViewById(R.id.cate_rv);
        verticalViewPager = findViewById(R.id.cate_content);
        initNetData();
        initLeft();
        initRight();
        bindEvent();
    }


    private void initNetData() {
        list = new ArrayList<>();

    }

    private void initLeft() {
        leftRvAdapter = new LeftRvAdapter(this, list);
        leftRv.setLayoutManager(new LinearLayoutManager(this));
        leftRv.setAdapter(leftRvAdapter);
    }

    private void initRight() {
        verticalViewPager.setPageTransformer(true, new VerticalTransformer());
        verticalViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);

        ScrollUtils.setViewPagerScrollSpeed(verticalViewPager,400);
        pagerAdapter = new CateContentAdapter(this,list);
        verticalViewPager.setAdapter(pagerAdapter);
        pagerAdapter.setOnDragListener(this);
        pagerAdapter.notifyDataSetChanged();

        verticalViewPager.setAdapter(pagerAdapter);
    }

    private void bindEvent() {
        leftRvAdapter.setOnItemClickListener(this);
        leftRvAdapter.setOnItemCheckListener(this);
    }
    @Override
    public void onShowLast(int position) {
        if (itemPage <= 0){
            return;
        }
        itemPage--;
        leftRvAdapter.setCheck(itemPage);
        leftRv.smoothScrollToPosition(itemPage);
        verticalViewPager.setCurrentItem(itemPage);
    }

    @Override
    public void onShowNest(int position) {
        if (itemPage >= 5){
            return;
        }
        itemPage++;
        leftRvAdapter.setCheck(itemPage);
        leftRv.smoothScrollToPosition(itemPage);
        verticalViewPager.setCurrentItem(itemPage);
    }

    @Override
    public void onItemClick(int position) {
        this.itemPage = position;
        leftRvAdapter.setCheck(position);
    }

    @Override
    public void onItemCheck(int position) {
        verticalViewPager.setCurrentItem(itemPage);
    }
}
