package com.collekarry.globlockhackthon;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class ShareJobService extends JobService {


    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        Intent intent = new Intent(this , SocialMedia.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this , 0 , intent,0);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(getApplicationContext() , "share_channel")
                .setSmallIcon(R.drawable.logout_icon)
                .setContentText("Upload Pending")
                .setContentIntent(pendingIntent)
                .build();

        manager.notify(100 , notification);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
