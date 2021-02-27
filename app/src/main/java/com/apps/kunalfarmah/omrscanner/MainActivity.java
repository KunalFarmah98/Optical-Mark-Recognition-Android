package com.apps.kunalfarmah.omrscanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.apps.kunalfarmah.omrscanner.Util.sout;

public class MainActivity extends AppCompatActivity {

    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int REQUEST_GALLERY = 2;
    private static final int MY_STORAGE_PERMISSION_CODE = 101;
    public static File file;
    String currentPhotoPath;
    String imageFileName;
    Mat source;
    ImageView img;
    File photoFile;
    Uri photoURI;
    Button results;
    ImageButton retry;
    boolean isCamera = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.loadLibrary("opencv_java3");
        isCamera = getIntent().getBooleanExtra("isCamera", false);

        final File path =
                Environment.getExternalStoragePublicDirectory
                        (
                                Environment.DIRECTORY_DOCUMENTS + "/OMR/"
                        );

        if (!path.exists()) {
            path.mkdirs();
        }

        file = new File(path, "config.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        img = findViewById(R.id.img);
        results = findViewById(R.id.res);
        retry = findViewById(R.id.retry);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        } else if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_STORAGE_PERMISSION_CODE);
        } else {
            dispatchTakePictureIntent();
        }

        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Results.class));
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (null != grantResults && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
        if (requestCode == MY_STORAGE_PERMISSION_CODE) {
            if (null != grantResults && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "storage permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_CANCELED)
            finish();

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            try {
                CropImage.activity(photoURI)
                        .start(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(requestCode == REQUEST_GALLERY && resultCode == RESULT_OK){
            currentPhotoPath = data.getData().toString();
            try {
                CropImage.activity(data.getData())
                        .start(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                photoURI = result.getUri();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), photoURI);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                img.setImageBitmap(bitmap);
                retry.setVisibility(View.VISIBLE);
                results.setVisibility(View.VISIBLE);
                //photoURI = CropImage.getCaptureImageOutputUri(this);
                //img.setImageUriAsync(photoURI);
                Log.d("IMAGE", currentPhotoPath.toString());
                //source = Imgcodecs.imread("/storage/self/primary/Pictures/sheet_1.jpg");
                source = Imgcodecs.imread(photoURI.getPath());

                sout("...started");
                Scanner scanner = new Scanner(source, 20);
                scanner.setLogging(false);
                try {
                    PrintWriter writer = new PrintWriter(file);
                    writer.print("");
                    writer.close();
                    scanner.scan();
                } catch (Exception e) {
                    results.setAlpha(0.2f);
                    results.setClickable(false);
                    Toast.makeText(this, "Error Scanning OMR, Please Try Again", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

                sout("...finished");
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        imageFileName = "OMR_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        results.setAlpha(1f);
        results.setClickable(true);
        Intent takePictureIntent;
        if (isCamera) {
            takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {

                }
                if (photoFile != null) {
                    photoURI = FileProvider.getUriForFile(this,
                            "com.example.android.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                }
            }
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

        } else {
            takePictureIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(takePictureIntent, REQUEST_GALLERY);
        }

    }


    @Override
    public void onBackPressed() {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.onBackPressed();
    }


}
