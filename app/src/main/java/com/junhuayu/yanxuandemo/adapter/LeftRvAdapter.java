package com.junhuayu.yanxuandemo.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.junhuayu.yanxuandemo.R;
import com.junhuayu.yanxuandemo.beans.Category;

import java.util.List;

public class LeftRvAdapter extends RecyclerView.Adapter<LeftRvAdapter.Holder> {

    private List<Category> categoryBeans;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private OnItemCheckListener onItemCheckListener;
    private int check;


    public LeftRvAdapter(Context context, List<Category> categoryBeans) {
        this.context = context;
        this.categoryBeans = categoryBeans;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.cate_left_item,parent,false));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.tv.setText(position+1+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
        if (position == check){
            holder.tv.setBackgroundResource(R.drawable.itemselect_bg);
            holder.tv.setTextColor(context.getResources().getColor(R.color.main));
            holder.tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
        }else {
            holder.tv.setBackgroundColor(context.getResources().getColor(R.color.bg));
            holder.tv.setTextColor(context.getResources().getColor(R.color.text));
            holder.tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        }
    }

    @Override
    public int getItemCount() {
        if (categoryBeans != null && categoryBeans.size() > 0){
            return  categoryBeans.size() ;
        }else {
            return 6;
        }

    }

    public void setCheck(int position) {
        this.check = position;
        notifyDataSetChanged();
        if (onItemCheckListener != null){
            onItemCheckListener.onItemCheck(position);
        }
    }


    public Category getdata(int position) {
        return categoryBeans.get(position);
    }

//    public List<Category> getdata(int position) {
//        return categoryBeans.get(position).getChild();
//    }

    public void notifyData(List<Category> category) {
        this.categoryBeans = category;
        notifyDataSetChanged();
    }



    public class Holder extends RecyclerView.ViewHolder {
        TextView tv;
        public Holder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.item_tv);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public interface OnItemCheckListener{
        void onItemCheck(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemCheckListener(OnItemCheckListener onItemCheckListener){
        this.onItemCheckListener = onItemCheckListener;
    }
}
