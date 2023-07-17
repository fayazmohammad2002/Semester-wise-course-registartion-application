package com.example.idpbdcheck;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import javax.annotation.Nullable;
public class ImageUpload extends AppCompatActivity
{
    Button select,uplaodBtn,showAllBtn;
    ImageView imageview;
    DatabaseReference root;
    StorageReference reference;
    String sem,branch,year;
    String path,phone,name,mail,fee;
    EditText fees;
    Uri Imageuri;
    ProgressDialog pd;
    private static final int MAX_FILE_SIZE = 2 * 1024 * 1024;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);
      imageview=findViewById(R.id.imagevi);
        select = findViewById(R.id.Select);
        uplaodBtn = findViewById(R.id.upload_btn);
        showAllBtn = findViewById(R.id.next1);
        fees=findViewById(R.id.fees);
        Intent intent = getIntent();
        String user = (String)intent.getStringExtra("regno");
         sem=(String) intent.getStringExtra("sem");
         branch=(String) intent.getStringExtra("branch");
        year=(String) intent.getStringExtra("year");
        phone=(String) intent.getStringExtra("phone");
        name=(String) intent.getStringExtra("name");
        mail=(String) intent.getStringExtra("mail");
        path="16aKuCsEG4KcBd6z75HfRnyzWcucEwpdnO0V9moPoaL0/Sheet1/"+user;
        root = FirebaseDatabase.getInstance().getReference(path);
        reference = FirebaseStorage.getInstance().getReference();
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(ImageUpload.this,"select ",Toast.LENGTH_SHORT).show();
                ImageSelect();
            }
        });
        uplaodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Imageuri != null)
                {
                    uploadToFirebase(Imageuri);
                } else
                {
                    Toast.makeText(ImageUpload.this, "please select image", Toast.LENGTH_SHORT).show();
                }
            }
        });
        showAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               fee= String.valueOf(fees.getText());
             Intent i=new Intent(getApplicationContext(),subjects.class);
                i.putExtra("name",name);
                i.putExtra("regno",user+"");
                i.putExtra("sem",sem);
                i.putExtra("branch",branch);
                i.putExtra("year",year);
                i.putExtra("mail",mail);
                i.putExtra("phone",phone);
                i.putExtra("fees",fee);
                AlertDialog.Builder builder=new AlertDialog.Builder(ImageUpload.this);
                builder.setTitle("VERIFY ONCE ...!");
                builder.setMessage("ARE YOU SURE TO CONTINUE");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        if(Imageuri!=null){
                        startActivity(i);}
                        else {
                            Toast.makeText(ImageUpload.this, "PLEASE UPLOADE IMAGE", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        Toast.makeText(ImageUpload.this, "check the details and upload", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
    }
    private void  uploadToFirebase(Uri uri)
    {
        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        pd=new ProgressDialog(ImageUpload.this);
        pd.setTitle("uploading File...");
        pd.show();
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri)
                    {
                        Model model =new Model(uri.toString());
                        root.child("").child("Url").setValue(model);
                        if(pd.isShowing())
                             pd.dismiss();
                        Toast.makeText(ImageUpload.this, "Uploading successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(pd.isShowing())
                    pd.dismiss();
                Toast.makeText(ImageUpload.this, "Uploading failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String getFileExtension(Uri mUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
    private void ImageSelect() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, 100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==100 && data!=null && data.getData()!=null)
        {
            Imageuri=data.getData();
            imageview.setImageURI(Imageuri);
        }
    }
}