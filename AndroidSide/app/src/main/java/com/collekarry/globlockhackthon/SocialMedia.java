package com.collekarry.globlockhackthon;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.collekarry.globlockhackthon.Adapter.ShareRecyclerAdapter;
import com.collekarry.globlockhackthon.Model.shareData;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

public class SocialMedia extends AppCompatActivity implements ShareRecyclerAdapter.socialMediaShare{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(new ShareRecyclerAdapter(this , this));
        recyclerView.setHasFixedSize(true);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        prepareFab();

        prepareItemTouchHelper().attachToRecyclerView(recyclerView);

       scheduleJob();

    }

    private void prepareFab(){
        FloatingActionButton actionButton = findViewById(R.id.floating_action_bar);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SocialMedia.this , AddPost.class));
            }
        });
    }

    private ItemTouchHelper prepareItemTouchHelper() {
        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            }
        });

        return touchHelper;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(SocialMedia.this, Home.class);
        startActivity(i);
        finish();
    }

    @Override
    public void facebookShare(Bundle bundle) {
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources() , bundle.getInt(shareData.SHARE_IMAGE));
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(bitmap)
                .setCaption(bundle.getString(shareData.SHARE_TITLE))
                .build();

        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        ShareDialog dialog = new ShareDialog(this);
        if (dialog.canShow(SharePhotoContent.class)){
            dialog.show(content);
        }
        else{
            Log.d("Activity", "you cannot share photos :(");
        }
    }

    @Override
    public void instagramShare(Bundle bundle) {

    }

    @Override
    public void whatsAppShare(Bundle bundle) {
        Uri imageUri = Uri.parse("android:resource://com.example.gaurav.helfy.Adapter" + bundle.getInt(shareData.SHARE_IMAGE));
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setPackage("com.whatsapp");
        shareIntent.putExtra(Intent.EXTRA_TEXT, bundle.getString(shareData.SHARE_DESCRIPTION));
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("text/plain");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            this.startActivity(shareIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "whats app is not installed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void twitterShare(Bundle bundle) {

    }
    public void scheduleJob(){
        ComponentName serviceComponent = new ComponentName(this, ShareJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        builder.setPersisted(true);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setRequiresCharging(true);
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }
}
