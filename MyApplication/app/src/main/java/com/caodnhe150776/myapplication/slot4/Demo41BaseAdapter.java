package com.caodnhe150776.myapplication.slot4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.caodnhe150776.myapplication.R;

import java.util.List;

public class Demo41BaseAdapter extends BaseAdapter {

    private Context context;
    private List<Demo41Student> list;

    public Demo41BaseAdapter(Context context, List<Demo41Student> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Demo41ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.demo41_item_view,parent,false);

            holder=new Demo41ViewHolder();
            holder.img_hinh=convertView.findViewById(R.id.demo41_itemview_hinh);
            holder.tvTen=convertView.findViewById(R.id.demo41_itemview_ten);
            holder.tvTuoi=convertView.findViewById(R.id.demo41_itemview_tuoi);
            convertView.setTag(holder);
        }else {
            holder=(Demo41ViewHolder) convertView.getTag();
        }
        Demo41Student student=list.get(position);
        holder.img_hinh.setImageResource(student.getHinh());
        holder.tvTen.setText(student.getTen());
        holder.tvTuoi.setText(student.getTuoi());
        return convertView;
    }

    static class Demo41ViewHolder{
        ImageView img_hinh;
        TextView tvTen,tvTuoi;
    }
}
