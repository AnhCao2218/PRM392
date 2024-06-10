package com.caodnhe150776.myapplication.slot9;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.caodnhe150776.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Demo91MainActivity extends AppCompatActivity {
    private ListView listView;
    private Demo91Adapter adapter;
    private List<Product91> list;
    CartManager cartManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demo91_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView=findViewById(R.id.demo91_listview);
        cartManager =CartManager.getInstance();
        list= new ArrayList<>();
        adapter= new Demo91Adapter(this,list);
        listView.setAdapter(adapter);
        new FetchProduct91().execute();
    }
    private class FetchProduct91 extends AsyncTask<Void, Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder response= new StringBuilder();
            try{
                URL url= new URL("http://192.168.88.154:8080/aphp/select.php");
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line=reader.readLine())!=null){
                    response.append(line);
                }
                reader.close();
            }catch (MalformedURLException e){
                throw new RuntimeException(e);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
            return response.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            if(s!=null&& !s.isEmpty()){
                try {
                    JSONObject jsonObject =  new JSONObject(s);
                    JSONArray productArray= jsonObject.getJSONArray("products");
                    for(int i=0; i<productArray.length();i++){
                        JSONObject pObject= productArray.getJSONObject(i);
                        String styleId=pObject.getString("styleid");
                        String brand=pObject.getString("brands_filter_facet");
                        String price=pObject.getString("price");
                        String info=pObject.getString("product_additional_info");
                        String searchImage=pObject.getString("search_image");
                        Product91 product91= new Product91(styleId,brand,price,info,searchImage);
                        list.add(product91);
                    }
                    adapter.notifyDataSetChanged();
                }catch (JSONException e){
                    throw new RuntimeException(e);
                }

            }
        }
    }
}