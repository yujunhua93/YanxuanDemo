package com.junhuayu.yanxuandemo.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.junhuayu.yanxuandemo.R;
import com.junhuayu.yanxuandemo.beans.CateAd;
import com.junhuayu.yanxuandemo.beans.Category;
import com.junhuayu.yanxuandemo.widgets.RightScrollView;

import java.util.ArrayList;
import java.util.List;

public class CateContentAdapter extends PagerAdapter implements RightScrollView.OnSlideListenerInterface, CategoryAdapter.OnItemClickListener {

    private Context context;

    private List<Category> categoryList;

    private RecyclerView recyclerView;

    private RightScrollView nestRefreshLayout;

    private LinearLayout cateLayout;

    private View mCurrentView;

    List<CateAd> slider = new ArrayList<>();

    private int mChildCount = 0;

    private Category data;

    public OnDragListener onDragListener;

    private CategoryAdapter categoryAdapter;

    private int lastPostion = -1;

    public CateContentAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return 6;
//        return categoryList == null ? 0 : categoryList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }


    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (lastPostion != position){
            lastPostion =  position;
////            View view = LayoutInflater.from(context).inflate(R.layout.fragment_category_content, null);
            View view = (View) object;
            if (view == null)
                return;
            nestRefreshLayout = view.findViewById(R.id.nest_refresh);
            recyclerView = view.findViewById(R.id.cate_content_rv);
            cateLayout = view.findViewById(R.id.cate_ll);
            nestRefreshLayout.setOnSlideListener(this);
            data = new Category();
//            data = categoryList.get(position);
//            slider = data.getCateAds();



            GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };

            recyclerView.setLayoutManager(gridLayoutManager);
            categoryAdapter = new CategoryAdapter(context,data.getChild());
            categoryAdapter.setOnItemClickListener(this);
            recyclerView.setAdapter(categoryAdapter);
        }

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_layout, null);


        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null){
            parent.removeView(view);
        }
        container.addView(view);


        return view;

    }


    public void setdata(List<Category> categoryList) {

    }

    @Override
    public void OnSlideListener(float coordinate) {
        //上滑
        if (coordinate > 0 && Math.abs(coordinate) > 500) {
            if (onDragListener!= null) {
                onDragListener.onShowNest(0);
            }
        } else if (coordinate < 0 && Math.abs(coordinate) > 500) {

            if (onDragListener!= null) {
                onDragListener.onShowLast(0);
            }
        }
    }

    @Override
    public void onItemClick(int position, View v) {
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(v, "scaleX", 1f, 0.8f, 1f);
        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(v, "scaleY", 1f, 0.8f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(300);
        set.play(scaleXAnimation).with(scaleYAnimation);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }

    public void setData(List<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    public interface OnDragListener{
        void onShowLast(int position);
        void onShowNest(int position);
    }

    public void setOnDragListener(OnDragListener onDragListener){
        this.onDragListener = onDragListener;
    }


//    @Override
//    public void notifyDataSetChanged() {
//        mChildCount = getCount();
//        super.notifyDataSetChanged();
//    }
}
