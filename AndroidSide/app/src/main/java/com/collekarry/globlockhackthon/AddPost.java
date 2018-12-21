package com.collekarry.globlockhackthon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import  com.collekarry.globlockhackthon.Model.shareData;

public class AddPost extends AppCompatActivity {

    String title;
    String description;
    int image;

    EditText titleEditText;
    EditText descriptionEditText;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        titleEditText = findViewById(R.id.tileEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        imageButton = findViewById(R.id.image_button);
        Bundle bundle = getIntent().getExtras();
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(bundle != null){
            bundle  = bundle.getBundle("bundle");
            title = bundle.getString(shareData.SHARE_TITLE);
            description = bundle.getString(shareData.SHARE_DESCRIPTION);
            image = bundle.getInt(shareData.SHARE_IMAGE);
            titleEditText.setText(title);
            descriptionEditText.setText(description);
            imageButton.setImageResource(image);

            getSupportActionBar().setTitle("Edit");
        }else{
            getSupportActionBar().setTitle("Add");
        }
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddPost.this, "Share Item Saved " , Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent , 24);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null)
        {

            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap)bundle.get("data");
            imageButton.setImageBitmap(bitmap);
        }}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
