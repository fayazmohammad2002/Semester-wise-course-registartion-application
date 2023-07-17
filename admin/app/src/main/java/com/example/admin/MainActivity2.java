package com.example.admin;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.admin.databinding.ActivityMain2Binding;
import com.example.admin.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    DatabaseReference reference,refer;
    ArrayList<String> useregno;
    ArrayList<String> phones;
    ArrayList<String> names;
    ArrayList<String> feespaids;
    ArrayList<String> urls;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        useregno = new ArrayList<>();
        phones = new ArrayList<>();
        names = new ArrayList<>();
        feespaids = new ArrayList<>();
        urls = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i=getIntent();
        path=i.getStringExtra("year");
        getdetails();
    }
         private void getdetails()
         {
             reference = FirebaseDatabase.getInstance().getReference(path);
             reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<DataSnapshot> task) {
                     for (DataSnapshot ds : task.getResult().getChildren()) {
                         String c = ds.getKey();
                         useregno.add(c);
                     }
                     Log.d("checkb",useregno.size()+"");
                     for (String i : useregno)
                     {
                         refer = FirebaseDatabase.getInstance().getReference(path+"/" + i);
                         refer.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                             @Override
                             public void onComplete(@NonNull Task<DataSnapshot> task) {
                                 DataSnapshot ds = task.getResult();
                                 String name = String.valueOf(ds.child("Name").getValue());
                                 names.add(name);
                                 String pn = String.valueOf(ds.child("Phone").getValue());
                                 phones.add(pn);
                                 String fee = String.valueOf(ds.child("Fees paid:").getValue());
                                 feespaids.add(fee);
                                 String url = String.valueOf(ds.child("Imageurl").getValue());
                                 urls.add(url);
                                if(useregno.size()==names.size())
                                {
                                    detials(useregno,names,phones,urls,feespaids);
                                }
                             }
                         });
                     }
                 }
             });
         }
    private void detials(ArrayList<String> useregno, ArrayList<String> names, ArrayList<String> phones, ArrayList<String> urls,ArrayList<String> feespaids)
    {
      ArrayList<Users> usersArrayList=new ArrayList<>();
        for(int i=0;i<useregno.size();i++)
        {
            Users user=new Users(useregno.get(i),feespaids.get(i),names.get(i));
            usersArrayList.add(user);
        }
        if(useregno.size()==0)
        {
            Users user=new Users("NO user Found","","");
            usersArrayList.add(user);
        }
            ListAdapter listAdapter = new ListAdapter(MainActivity2.this, usersArrayList);
            ListView ls = findViewById(R.id.listview);
            ls.setAdapter(listAdapter);
            ls.setClickable(true);
            ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                    intent.putExtra("Name", names.get(i));
                    intent.putExtra("Regno", useregno.get(i));
                    intent.putExtra("Urls", urls.get(i));
                    intent.putExtra("Phone", phones.get(i));
                    intent.putExtra("fees", feespaids.get(i));
                    startActivity(intent);
                }
            });
    }
}