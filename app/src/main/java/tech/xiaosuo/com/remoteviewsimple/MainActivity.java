package tech.xiaosuo.com.remoteviewsimple;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setTicker("ticker title");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(true);
        Intent intent = new Intent(this,TestActivity1.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentTitle("Contenttitle");
        builder.setContentText("contentText");
        builder.setContentIntent(pendingIntent);
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.remote_notification);
        remoteViews.setTextViewText(R.id.remote_text_view,"remoteText");
        remoteViews.setImageViewResource(R.id.remote_img_view,R.drawable.ic_launcher_foreground);
        remoteViews.setOnClickPendingIntent(R.id.remote_button_view,pendingIntent);
        builder.setContent(remoteViews);
        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,notification);

        TextView textView = (TextView)findViewById(R.id.transition_textview);
        TransitionDrawable drawable  = (TransitionDrawable)textView.getBackground();
        drawable.startTransition(5000);
    }



}
