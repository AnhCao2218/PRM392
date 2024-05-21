package com.caodnhe150776.myapplication.slot5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.caodnhe150776.myapplication.R;

import java.util.List;

public class Demo51Adapter extends BaseAdapter{
    private List<Demo51Product>mlist;

    public Demo51Adapter(List<Demo51Product> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    public Demo51Adapter() {
    }

    private Context context;
    static class Demo51ViewHolder  {

        ImageView img;
        TextView tvID, tvName,tvPrice;


    }
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Demo51ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.demo51_item_view,parent,false);
            holder= new Demo51ViewHolder();
            holder.img=convertView.findViewById(R.id.demo51_itemview_img);
            holder.tvID=convertView.findViewById(R.id.demo51_itemview_tvID);
            holder.tvName=convertView.findViewById(R.id.demo51_itemview_tvName);
            holder.tvPrice=convertView.findViewById(R.id.demo51_itemview_tvPrice);
            convertView.setTag(holder);
        }else {
            holder=(Demo51ViewHolder) convertView.getTag();
        }
        Demo51Product product=mlist.get(position);
        if (product!=null){
            holder.img.setImageResource(R.drawable.android);
            holder.tvID.setText(product.getId());
            holder.tvName.setText(product.getName());
            holder.tvPrice.setText(String.valueOf(product.getPrice()));
        }
        return convertView;
    }
}
