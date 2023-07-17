package com.example.admin;
import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.admin.databinding.ActivityMain3Binding;
import java.io.InputStream;
import java.net.URL;
public class MainActivity3 extends AppCompatActivity {
    TextView name, regno, phone, fees;
    ActivityMain3Binding binding;
    ImageView img;
    Handler mainHandler = new Handler();
    ProgressDialog pd;
    Button approve,disapprove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        approve=findViewById(R.id.approve);
        disapprove=findViewById(R.id.disapprove);
        Intent intent = this.getIntent();
        String namestr = intent.getStringExtra("Name");
        String phonestr = intent.getStringExtra("Phone");
        String regnostr = intent.getStringExtra("Regno");
        String feesstr = intent.getStringExtra("fees");
        String url = intent.getStringExtra("Urls");
        new fetch(url).start();
        name = findViewById(R.id.name);
        fees = findViewById(R.id.fees);
        regno = findViewById(R.id.regno);
        phone = findViewById(R.id.phone);
        img=findViewById(R.id.image);
        name.setText(namestr);
        fees.setText(feesstr);
        phone.setText(phonestr);
        regno.setText(regnostr);
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendmessage(phonestr,1);
            }
        });
        disapprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendmessage(phonestr,0);
            }
        });
    }
    private void sendmessage(String phonestr,int i) {
        if(!phonestr.isEmpty())
        {
            SmsManager sm=SmsManager.getDefault();
            if(i==1) {
                sm.sendTextMessage(phonestr, null, "Your Registation succesfull", null, null);
            }
            if(i==0) {
                sm.sendTextMessage(phonestr, null, "Your Registation failed try apply again with good clarity and more then half amount of fees", null, null);
            }
            Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show();
        }
    }
    class fetch extends Thread {
        String url;
        Bitmap bitmap;
        fetch(String url) {
            this.url = url;
        }
        @Override
        public void run() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    pd=new ProgressDialog(MainActivity3.this);
                    pd.setMessage("Getting your pic....");
                    pd.show();
                }
            });
            InputStream is=null;
            try{
               is=new URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(is);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
           mainHandler.post(new Runnable() {
               @Override
               public void run() {
                   if(pd.isShowing())
                   {
                       pd.dismiss();
                   }
                   img.setImageBitmap(bitmap);
               }
           });
        }
    }
}