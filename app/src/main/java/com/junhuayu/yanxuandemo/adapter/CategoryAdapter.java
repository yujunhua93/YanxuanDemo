package com.junhuayu.yanxuandemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhuayu.yanxuandemo.R;
import com.junhuayu.yanxuandemo.beans.CateAd;
import com.junhuayu.yanxuandemo.beans.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {

    private Context context;
    private List<Category> categoryBeans;

    private List<Item> data;

    private static final int HEADER = 1;
    private static final int CONTENT= 2;

    private OnItemClickListener onItemClickListener;
    public CategoryAdapter(Context context, List<Category> categoryBeans) {
        this.context = context;
        this.categoryBeans = categoryBeans;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.content_item,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
//        Category categoryBean = categoryBeans.get(position);
        holder.cateTv.setText(position+1+"");
//        holder.cateTv.setText(Html.fromHtml(categoryBean.getName()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(position,v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
//        return  categoryBeans == null ? 0 : categoryBeans.size();
    }



    public void setData(Category category) {
        if (data == null) data = new ArrayList<>();
        else data.clear();
        if (category == null)
            return;

        List<CateAd> cateAds = category.getCateAds();
        if (cateAds != null && !cateAds.isEmpty()) {
            data.add(new Item(HEADER, cateAds));
        }

        List<Category> categories = category.getChild();
        if (categories != null && !categories.isEmpty()) {
            data.add(new Item(CONTENT, categories));
        }

    }


    public class Holder extends RecyclerView.ViewHolder {
        ImageView cateIv;
        TextView cateTv;
        public Holder(View itemView) {
            super(itemView);
            cateIv = itemView.findViewById(R.id.cate_iv);
            cateTv = itemView.findViewById(R.id.cate_tv);
        }
    }

    public void notifyData(List<Category> categoryBeans){
        this.categoryBeans = categoryBeans;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    private class Item {
        int type;
        Object item;

        public Item(int type, Object item) {
            this.type = type;
            this.item = item;
        }
    }
}
