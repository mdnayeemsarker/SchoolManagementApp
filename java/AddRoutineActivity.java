package asha.md_nayeem.schoolmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddRoutineActivity extends AppCompatActivity
{
    private EditText routineTitle, routineDescriptions;
    private ImageView routineImage;
    private Button Submit;

    private String saveCurrentDate,saveCurrentTime;
    private String productRandomKey, downloadImageUrl;
    private StorageReference routineImagesRef;
    private DatabaseReference RoutineRef;
    private ProgressDialog loadingBar;

    private String r_Description, r_title;

    private static final int GalleryPic = 1;
    private Uri ImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        routineImagesRef = FirebaseStorage.getInstance().getReference().child("Routines Images");
        RoutineRef = FirebaseDatabase.getInstance().getReference().child("Routines");

        routineTitle = findViewById(R.id.add_routite_title_id);
        routineDescriptions = findViewById(R.id.add_routine_description_id);
        routineImage = findViewById(R.id.add_routine_image_id);
        Submit = findViewById(R.id.add_routine_button_id);

        loadingBar = new ProgressDialog(this);

        routineImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                OpenGallery();
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ValidateProductData();
            }
        });
    }

    private void ValidateProductData()
    {
        r_Description = routineDescriptions.getText().toString();
        r_title = routineTitle.getText().toString();

        if (ImageUri == null)
        {
            Toast.makeText(this, "Routines image is mandatory...!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(r_Description))
        {
            Toast.makeText(this, "Please write Routines description...!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(r_title))
        {
            Toast.makeText(this, "Please write Routines description...!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            storeProductInformation();
        }
    }

    private void storeProductInformation()
    {
        loadingBar.setTitle("Adding New Routine Information");
        loadingBar.setMessage("Dear Admin Please wait, while we are adding the new Routines");
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;

        final StorageReference filePath = routineImagesRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(AddRoutineActivity.this, "Error" + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(AddRoutineActivity.this, "Routines Image Uploaded Successfully", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(AddRoutineActivity.this, "got the Routines image Url Successfully...!", Toast.LENGTH_SHORT).show();

                            SaveProductInfoToDatabase();
                        }
                    }
                });
            }
        });
    }

    private void SaveProductInfoToDatabase()
    {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("id", productRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("description", r_Description);
        productMap.put("image", downloadImageUrl);
        productMap.put("title", r_title);

        RoutineRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(AddRoutineActivity.this, AdminActivity.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(AddRoutineActivity.this, "Routines added Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AddRoutineActivity.this, "Error: "+ message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void OpenGallery()
    {
        Intent galleryintent = new Intent();
        galleryintent.setAction(Intent.ACTION_GET_CONTENT);
        galleryintent.setType("image/*");
        startActivityForResult(galleryintent, GalleryPic);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPic && resultCode == RESULT_OK && data != null)
        {
            ImageUri = data.getData();
            routineImage.setImageURI(ImageUri);
        }
    }
}
