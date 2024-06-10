package com.caodnhe150776.myapplication.slot9;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.caodnhe150776.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Demo91Adapter extends BaseAdapter {
    private Context context;
    private List<Product91> mList;

    public Demo91Adapter() {
    }

    public Demo91Adapter(Context context, List<Product91> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler91 holer;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.demo91_itemview,parent,false);
            holer=new ViewHoler91();
            holer.imageView=convertView.findViewById(R.id.demo91_itemview_searchImage);
            holer.styleIdTV=convertView.findViewById(R.id.demo91_itemview_TvStyleid);
            holer.brandTV=convertView.findViewById(R.id.demo91_itemview_TvBrand);
            holer.priceTV=convertView.findViewById(R.id.demo91_itemview_TvPrice);
            holer.infoTV=convertView.findViewById(R.id.demo91_itemview_TvInfo);
            convertView.setTag(holer);
        }
        else {
            holer=(ViewHoler91) convertView.getTag();
        }
        Product91 product= mList.get(position);
        if(product!=null){

            Picasso.get().load(product.getSearchImage()).into(holer.imageView);
            holer.styleIdTV.setText(product.getStyleId());
            holer.brandTV.setText(product.getBrand());
            holer.priceTV.setText(product.getPrice());
            holer.infoTV.setText(product.getInfo());

        }
        //event
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product91 product=mList.get(position);
                Intent intent= new Intent(context,Demo92MainActivity.class);
                intent.putExtra("PRODUCT",product);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHoler91{
        ImageView imageView;
        TextView styleIdTV,brandTV,priceTV,infoTV;
    }
}
