package com.example.pratiksahayideaindiablrcom.nytimes.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pratiksahayideaindiablrcom.nytimes.R;
import com.example.pratiksahayideaindiablrcom.nytimes.listener.ItemSelectListner;
import com.example.pratiksahayideaindiablrcom.nytimes.model.DataItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataDisplayAdapter extends RecyclerView.Adapter<DataDisplayAdapter.ViewHolder>
{

    Context context;
    List<DataItem> list;
    ItemSelectListner itemSelectListner;

    private LayoutInflater layoutInflater;

    public DataDisplayAdapter(Context context, List<DataItem> list, ItemSelectListner itemSelectListner)
    {
        this.context=context;
        this.list=list;
        this.layoutInflater=LayoutInflater.from(context);
        this.itemSelectListner=itemSelectListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=layoutInflater.inflate(R.layout.adapter_data_display,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.tv_author.setText(list.get(i).getByline());
        viewHolder.tv_title.setText(list.get(i).getTitle());
        if (list.get(i).getMultimedia().size()>0)
        Picasso.with(context).load(list.get(i).getMultimedia().get(0).getUrl()).into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelectListner.onSelect(list.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        public TextView tv_title;

        @BindView(R.id.tv_author)
        public TextView tv_author;

        @BindView(R.id.img)
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void filter(List<DataItem> dataItemList)
    {
        list.clear();
        list.addAll(dataItemList);
        notifyDataSetChanged();
    }
}
