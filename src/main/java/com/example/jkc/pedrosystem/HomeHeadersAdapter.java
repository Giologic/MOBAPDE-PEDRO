package com.example.jkc.pedrosystem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.security.SecureRandom;

/**
 * Created by JKC on 14/03/2016.
 */
public class HomeHeadersAdapter extends HomeAdapter
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

    ImageLoader imageLoader = null;

    @Override
    public long getHeaderId(int position) {
        return getItem(position).getOrg().getId();
    }



    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_header, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView.findViewById(R.id.tv_home_header);
        textView.setText(getItem(position).getOrg().getName());
        textView.setBackgroundColor(getRandomColor());
    }

    private int getRandomColor() {
        SecureRandom rgen = new SecureRandom();
        return Color.HSVToColor(150, new float[]{
                rgen.nextInt(359), 1, 1
        });
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home, parent, false);
        return new HomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        holder.tvOrgName.setText(getItem(position).getOrg().getName());
        holder.tvMessage.setText(getItem(position).getContents());
        holder.tvDate.setText(getItem(position).getDate().toString());
        byte[] data = getItem(position).getImg();
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        holder.ivPub.setImageBitmap(bmp);

    }
}