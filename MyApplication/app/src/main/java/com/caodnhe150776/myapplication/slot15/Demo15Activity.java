package com.caodnhe150776.myapplication.slot15;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.caodnhe150776.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Demo15Activity extends AppCompatActivity {
    FirebaseFirestore database;
    TextView tvResult;
    Button btnInsert,btnUpdate,btnDelete;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demo15);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        database=FirebaseFirestore.getInstance();
        tvResult=findViewById(R.id.demo15tv1);
        btnInsert=findViewById(R.id.demo15btnInsert);
        btnUpdate=findViewById(R.id.demo15btnUpdate);
        btnDelete=findViewById(R.id.demo15btnDelete);
        btnInsert.setOnClickListener(v->{
            insertFirebase(tvResult);
        });
        btnUpdate.setOnClickListener(v->{
            updateFirebase(tvResult);
        });
        btnDelete.setOnClickListener(v->{
            deleteFirebase(tvResult);
        });
    }

    private void deleteFirebase(TextView tvResult) {
        id="";
        database.collection("TODO").document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        tvResult.setText("XOa thanh cong");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tvResult.setText(e.getMessage());
                    }
                });
        SelectDataFromFireBase(tvResult);
    }

    String id="";
    ToDo toDo=null;

    public  void  insertFirebase(TextView tvResult){
        id= UUID.randomUUID().toString();
        toDo= new ToDo(id,"title 1","content 1");
        HashMap<String, Object>mapTodo=toDo.convertHasMap();
        database.collection("TODO").document(id)
                .set(mapTodo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                    tvResult.setText("Them thanh cong");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tvResult.setText(e.getMessage());
                    }
                });



    }
    public void updateFirebase(TextView tvResult){
        id="";
        toDo= new ToDo(id,"sua title 1","sua content 1");
        database.collection("TODO").document(toDo.getId())
                .update(toDo.convertHasMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                    tvResult.setText("Sua thanh cong");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tvResult.setText(e.getMessage());
                    }
                });
    }
    String strResult="";
    public ArrayList<ToDo> SelectDataFromFireBase(TextView tvResult){
        ArrayList<ToDo> list= new ArrayList<>();
        database.collection("TODO")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            strResult="";
                            for(QueryDocumentSnapshot document: task.getResult()){
                                ToDo toDo1=document.toObject(ToDo.class);
                                strResult+="Id: "+toDo1.getId()+"\n";

                                list.add(toDo1);

                            }
                            tvResult.setText(strResult);
                        }else {
                            tvResult.setText("Doc du lieu that bai");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tvResult.setText(e.getMessage());
                    }
                });
        return list;
    }
}