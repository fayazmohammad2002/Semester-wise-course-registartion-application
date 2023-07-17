package com.example.admin;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
public class deparment extends AppCompatActivity
{
    ArrayList<String> branches=new ArrayList<>();
    int select;
    DatabaseReference reference,refer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deparment);
        reference = FirebaseDatabase.getInstance().getReference("ADMIN");
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                for (DataSnapshot ds : task.getResult().getChildren()) {
                    String c = ds.getKey();
                    branches.add(c);
                }
                display();
            }
        });
    }
    private void display() {
        ListAdapter1 listAdapter=new ListAdapter1(deparment.this,branches);
        ListView ls=findViewById(R.id.listviewdeparment);
        ls.setAdapter(listAdapter);
        ls.setClickable(true);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int di, long l) {
                String[] str= {"year-1","year-2","year-3","year-4"};
                AlertDialog.Builder aa = new AlertDialog.Builder(deparment.this);
                aa.setTitle("select year");
                aa.setSingleChoiceItems(str,0, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        select=which;
                    }
                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(deparment.this,MainActivity2.class);
                        intent.putExtra("year","ADMIN/"+branches.get(di)+"/"+(select+1));
                        startActivity(intent);
                    }
                });
                aa.show();
            }
        });
    }
}
