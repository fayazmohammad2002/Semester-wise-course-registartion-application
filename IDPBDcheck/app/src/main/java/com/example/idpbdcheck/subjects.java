package com.example.idpbdcheck;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
public class subjects extends AppCompatActivity  {
    String sem,branch,year,user,phone,name,mail,url,fee;
    DatabaseReference reference,r2,r3;
    CheckBox tt1,tt2,tt3,tt4,tt5,tt6,tt7,tt8,tt9;
    ArrayList<String> ans=new ArrayList<>();
    ArrayList<String> db=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel chnnelID=new NotificationChannel("Notification","MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(chnnelID);
        }
        for(int i=0;i<10;i++)
            ans.add("null");
        tt1= findViewById(R.id.t1);
        tt2= findViewById(R.id.t2);
        tt3= findViewById(R.id.t3);
        tt4= findViewById(R.id.t4);
        tt5= findViewById(R.id.t5);
        tt6=findViewById(R.id.t6);
        tt7= findViewById(R.id.t7);
        tt8=findViewById(R.id.t8);
        tt9= findViewById(R.id.t9);
         CheckBox tt10=(CheckBox) findViewById(R.id.t10);
        Intent intent = getIntent();
        user = (String)intent.getStringExtra("regno");
        sem=(String) intent.getStringExtra("sem");
        branch=(String) intent.getStringExtra("branch").toUpperCase();
        year=(String) intent.getStringExtra("year");
        phone=(String) intent.getStringExtra("phone");
        name=(String) intent.getStringExtra("name");
        mail=(String) intent.getStringExtra("mail");
        fee=intent.getStringExtra("fees");
        Button btn=(Button)findViewById(R.id.button);
        r3=FirebaseDatabase.getInstance().getReference("16aKuCsEG4KcBd6z75HfRnyzWcucEwpdnO0V9moPoaL0/Sheet1/"+user);
        r3.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot ds= task.getResult();
                url= String.valueOf(ds.child("Url").child("imageUrl").getValue());
            }
        });
        String path="subjects/"+branch+"/"+year+"/"+sem;
        Toast.makeText(subjects.this, path, Toast.LENGTH_SHORT).show();
        reference = FirebaseDatabase.getInstance().getReference(path);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                ans.add("None");
                int k = 0;
                for (DataSnapshot ds : task.getResult().getChildren())
                {
                    String c = ds.getKey();
                    String sub = String.valueOf(ds.getValue());
                    ans.add(k, c + ":" + sub);
                    k++;
                }
                if (ans.get(0) != "null"){
                    tt1.setVisibility(View.VISIBLE);
                    tt1.setText(ans.get(0));
                }
                if (ans.get(1) != "null")
                {
                    tt2.setVisibility(View.VISIBLE);
                    tt2.setText(ans.get(1));
                }
                if (ans.get(2) != "null")
                {
                    tt3.setVisibility(View.VISIBLE);
                    tt3.setText(ans.get(2));
                }
                if (ans.get(3) != "null")
                {
                    tt4.setVisibility(View.VISIBLE);
                    tt4.setText(ans.get(3));
                }
                if (ans.get(4) != "null")
                {
                    tt5.setVisibility(View.VISIBLE);
                    tt5.setText(ans.get(4));
                }
                if (ans.get(5) != "null")
                {
                    tt6.setVisibility(View.VISIBLE);
                    tt6.setText(ans.get(5));
                 }
                if(ans.get(6)!="null") {
                    tt7.setVisibility(View.VISIBLE);
                    tt7.setText(ans.get(6));
                }
                if(ans.get(7)!="null")
                {
                    tt8.setVisibility(View.VISIBLE);
                    tt8.setText(ans.get(7));
                }
                if(ans.get(8)!="null")
                {
                    tt9.setVisibility(View.VISIBLE);
                    tt9.setText(ans.get(8));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(subjects.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String r="";
                if (tt1.isChecked()) {
                   r=(String)tt1.getText();
                   db.add(r);
                }
                if (tt2.isChecked()) {
                    r=(String)tt2.getText();
                    db.add(r);
                }
                if (tt3.isChecked()) {
                    r=(String)tt3.getText();
                    db.add(r);
                }
                if (tt4.isChecked()) {
                    r=(String)tt4.getText();
                    db.add(r);
                }
                if (tt5.isChecked()) {
                    r=(String)tt5.getText();
                    db.add(r);
                }
                if (tt6.isChecked()) {
                    r=(String)tt6.getText();
                    db.add(r);
                }
                if (tt7.isChecked()) {
                    r=(String)tt7.getText();
                    db.add(r);
                }
                if (tt8.isChecked()) {
                    r=(String)tt8.getText();
                    db.add(r);
                }
//                if(tt10.isChecked()&tt9.isChecked()&tt8.isChecked()&tt7.isChecked()&tt6.isChecked()&tt5.isChecked()&tt4.isChecked()&tt3.isChecked()&tt2.isChecked()&tt1.isChecked()){
                r2 = FirebaseDatabase.getInstance().getReference("ADMIN");
                r2.child(branch+"/"+year+"/"+user).child("Fees paid:").setValue(fee);
                r2.child(branch+"/"+year+"/"+user).child("Registered Subjects").setValue(db);
                r2.child(branch+"/"+year+"/"+user).child("Name").setValue(name);
                r2.child(branch+"/"+year+"/"+user).child("Phone").setValue(phone);
                r2.child(branch+"/"+year+"/"+user).child("Mail").setValue(mail);
                r2.child(branch+"/"+year+"/"+user).child("Imageurl").setValue(url);
               Toast.makeText(subjects.this, "Succesfully registered", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder=new AlertDialog.Builder(subjects.this);
                    builder.setTitle("REGISTRATION");
                    builder.setMessage("You have been successfully Registred");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(subjects.this, "Tq for Registering", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(subjects.this,MainActivity.class);
                            startActivity(intent);
                            NotificationCompat.Builder builder=new
                                    NotificationCompat.Builder(subjects.this,"Notification")
                                    .setSmallIcon(R.drawable.logo2)
                                    .setContentTitle("VIGNAN UNIVERSITY")
                                    .setContentText("REGISTERED FOR THE SUBJECTS")
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                    .setAutoCancel(true);
                            Intent mainIntent = new Intent(subjects.this, subjects.class);
                            PendingIntent mainPIntent = PendingIntent.getActivity(subjects.this, 0,mainIntent, PendingIntent.FLAG_ONE_SHOT);
                            builder.setContentIntent(mainPIntent);
                            NotificationManager manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            manager.notify(1, builder.build());
                        }
                    });
                    AlertDialog dialog=builder.create();
                    dialog.show();
            }
        });
    }
}