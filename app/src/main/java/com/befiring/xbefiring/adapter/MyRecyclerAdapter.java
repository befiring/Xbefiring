package com.befiring.xbefiring.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.befiring.xbefiring.R;
import com.befiring.xbefiring.activity.LoginActivity;
import com.befiring.xbefiring.activity.SecondActivity;
import com.befiring.xbefiring.bean.User;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>{

    private List<String> mItems;

    public MyRecyclerAdapter(List<String> items){
        mItems=items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String item=mItems.get(position);
        holder.mTextView.setText(item);

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=v.getContext();
                Bundle args=new Bundle();
                User user=new User();
                user.setName("befiring");
                user.setAge(2);
                user.setSex(1);
                args.putParcelable("user",user);
                Intent intent=new Intent(context,LoginActivity.class);
                intent.putExtras(args);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView=(TextView)itemView.findViewById(R.id.list_item);
        }
    }
}
