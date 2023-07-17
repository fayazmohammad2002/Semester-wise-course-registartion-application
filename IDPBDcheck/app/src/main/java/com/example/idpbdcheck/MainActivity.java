package com.example.idpbdcheck;
import static android.app.ProgressDialog.show;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.idpbdcheck.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
    public class MainActivity extends AppCompatActivity {
        ActivityMainBinding binding;
        DatabaseReference reference;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            binding.registerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = binding.input.getText().toString().toUpperCase();
                    if (!username.isEmpty()) {
                        readData(username);
                    } else {
                        Toast.makeText(MainActivity.this, "PLease Enter Username", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        private void readData(String username) {
            reference = FirebaseDatabase.getInstance().getReference("16aKuCsEG4KcBd6z75HfRnyzWcucEwpdnO0V9moPoaL0/Sheet1");
            reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                            Toast.makeText(MainActivity.this, "Successfully Read", Toast.LENGTH_SHORT).show();
                            DataSnapshot dataSnapshot = task.getResult();
                            String Branch = String.valueOf(dataSnapshot.child("Branch").getValue());
                            String Currentyear = String.valueOf(dataSnapshot.child("Current year").getValue());
                            String Gender = String.valueOf(dataSnapshot.child("Gender").getValue());
                            String Name = String.valueOf(dataSnapshot.child("Name").getValue());
                            String Section = String.valueOf(dataSnapshot.child("Section").getValue());
                            String Semester = String.valueOf(dataSnapshot.child("semester").getValue());
                            String Mail = String.valueOf(dataSnapshot.child("mail").getValue());
                            String Number = String.valueOf(dataSnapshot.child("Student mobile").getValue());
                            Intent intent = new Intent(getApplicationContext(), details.class);
                            intent.putExtra("R", username);
                            intent.putExtra("B", Branch);
                            intent.putExtra("Y", Currentyear);
                            intent.putExtra("G", Gender);
                            intent.putExtra("N", Name);
                            intent.putExtra("S", Section);
                            intent.putExtra("SE", Semester);
                            intent.putExtra("M", Mail);
                            intent.putExtra("MN", Number);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "User Doesn't Exist", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "User Doesn't Exist", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        public void sho(View view) {
            show(view);
        }
        public void show(View view) {
            PopupMenu pop = new PopupMenu(MainActivity.this, view);
            pop.getMenuInflater().inflate(R.menu.menu, pop.getMenu());
            pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.item4) {
                        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.vignan.ac.in/tutionfee.php"));
                        startActivity(intent);
                    }
                    if (menuItem.getItemId() == R.id.item2) {
                        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.vignan.ac.in/results.php"));
                        startActivity(intent);
                    }
                    if (menuItem.getItemId() == R.id.item3) {
                        Intent si = new Intent(Intent.ACTION_SEND);
                        si.setType("message/rfc822");
                        si.putExtra(Intent.EXTRA_EMAIL, new String[]{"201FA04066@gmail.com"});
                        si.putExtra(Intent.EXTRA_SUBJECT, "REGISTRAION DESK");
                        si.putExtra(Intent.EXTRA_TEXT, "Vignan University");
                        startActivity(Intent.createChooser(si,"Choose Mail App"));
                    }
                    return false;
                }
            });
            pop.show();
        }
    }