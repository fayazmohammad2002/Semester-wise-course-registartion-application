package com.example.idpbdcheck;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class details extends AppCompatActivity {
    String user,mail,num;
    DatabaseReference reference;
    EditText Mobile,Mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Button Next=findViewById(R.id.next1);
        TextView Name= (TextView)findViewById(R.id.textname);
        TextView Year = (TextView)findViewById(R.id.year);
        TextView Gender = (TextView)findViewById(R.id.gen);
        TextView Branch = (TextView)findViewById(R.id.textbranch);
        TextView section = (TextView)findViewById(R.id.sec);
        TextView Sem= (TextView)findViewById(R.id.sem);
         Mobile = findViewById(R.id.mobile);
         Mail = findViewById(R.id.mail);
        Intent intent = getIntent();
         user = (String)intent.getStringExtra("R");
        String n = (String)intent.getStringExtra("B");
        String p = (String)intent.getStringExtra("Y");
        String m = (String)intent.getStringExtra("G");
        String r = (String)intent.getStringExtra("N");
        String s = (String)intent.getStringExtra("S");
        String ac = (String)intent.getStringExtra("SE");
         mail = (String)intent.getStringExtra("M");
         num = (String)intent.getStringExtra("MN");
        Name.setText(r);
        Branch.setText(n);
        Mail.setText(mail);
        section.setText(s);
        Sem.setText(ac);
        Year.setText(p);
        Gender.setText(m);
        Mobile.setText(num);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mail= String.valueOf(Mail.getText());
                num=String.valueOf(Mobile.getText());
                reference = FirebaseDatabase.getInstance().getReference("16aKuCsEG4KcBd6z75HfRnyzWcucEwpdnO0V9moPoaL0/Sheet1");
                reference.child(user).child("mail").setValue(mail);
                reference.child(user).child("Student mobile").setValue(num);
                Intent i = new Intent(getApplicationContext(),ImageUpload.class);
                Toast.makeText(details.this, user, Toast.LENGTH_SHORT).show();
                i.putExtra("regno",user+"");
                i.putExtra("sem",ac);
                i.putExtra("branch",n);
                i.putExtra("year",p);
                i.putExtra("name",r);
                i.putExtra("mail", mail);
                i.putExtra("phone",num);
                startActivity(i);
            }
        });
    }
}