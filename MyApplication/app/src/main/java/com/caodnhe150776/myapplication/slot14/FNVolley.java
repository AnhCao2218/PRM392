package com.caodnhe150776.myapplication.slot14;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FNVolley {
    //getStringByVolley
    public  void getStringByVolley(Context context, TextView textView){
        RequestQueue queue= Volley.newRequestQueue(context);
        //2.URl
        String url="https://www.google.com/";
        //3. Truyen Tham so
        //Stringrequest(phuong thuc,url,thanh cong,thatbai)
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
            //lay ve 1000 ku tu dau tien
                textView.setText("KQ: "+s.substring(0,1000));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                textView.setText(volleyError.getMessage());
            }
        });
        queue.add(stringRequest);
    }
    //getArrayOfObject
    String strJSON="";
    public  void getArrayOfObject(Context context,TextView textView){
        //1. Tao request
        RequestQueue queue=Volley.newRequestQueue(context);
        //2.url
        String url="https://hungnttg.github.io/array_json_new.json";
        //3. JsonArrayRequest
        //JsonArrayRequest
        JsonArrayRequest request= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i=0; i<jsonArray.length();i++){
                    try{
                        JSONObject person= jsonArray.getJSONObject(i);
                        //doc tung du lieu
                        String id=person.getString("id");
                        String name=person.getString("name");
                        String email=person.getString("email");
                        JSONObject phone=person.getJSONObject("phone");
                        String mobile=phone.getString("mobile");
                        String home=phone.getString("home");
                        //chuyen sang chuoi
                        strJSON +="ID: "+id+"\n";
                        strJSON +="Name: "+name+"\n";
                        strJSON +="Email: "+email+"\n";
                        strJSON +="Mobile: "+mobile+"\n";
                        strJSON +="Home: "+home+"\n";
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                //dua du lieu len textView
                textView.setText(strJSON);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                textView.setText(volleyError.getMessage());
            }
        });
        //4.thuc thi request
        queue.add(request);
    }
    //insert
    public void insertVolley(Context context, TextView tvResult,TextView tvName, TextView tvPrice, TextView tvDes){
        RequestQueue queue=Volley.newRequestQueue(context);
        String url="http://192.168.88.155:8080/202406/create_product.php";
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
            tvResult.setText(s.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tvResult.setText(volleyError.getMessage());
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> myData= new HashMap<>();
                myData.put("name",tvName.getText().toString());
                myData.put("price",tvPrice.getText().toString());
                myData.put("description",tvDes.getText().toString());

                return myData;
            }
        };
        queue.add(stringRequest);
    }
    public void updateVolley(Context context, TextView tvResult,EditText tvPid, EditText tvName, EditText tvPrice, EditText tvDes){
        RequestQueue queue=Volley.newRequestQueue(context);
        String url="http://192.168.88.155:8080/202406/update_product.php";
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                tvResult.setText(s.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tvResult.setText(volleyError.getMessage());
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> myData= new HashMap<>();
                myData.put("pid",tvPid.getText().toString());
                myData.put("name",tvName.getText().toString());
                myData.put("price",tvPrice.getText().toString());
                myData.put("description",tvDes.getText().toString());

                return myData;
            }
        };
        queue.add(stringRequest);
    }
    public void deleteVolley(Context context, TextView tvResult,EditText tvPid){
        RequestQueue queue=Volley.newRequestQueue(context);
        String url="http://192.168.88.155:8080/202406/delete_product.php";
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                tvResult.setText(s.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tvResult.setText(volleyError.getMessage());
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> myData= new HashMap<>();
                myData.put("pid",tvPid.getText().toString());


                return myData;
            }
        };
        queue.add(stringRequest);
    }
}
