package tech.xiaosuo.com.remoteviewsimple;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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

        Button button = (Button)findViewById(R.id.frame_button);
        button.setBackgroundResource(R.drawable.frame_animation);
        AnimationDrawable aniationDrawable= (AnimationDrawable)button.getBackground();
        aniationDrawable.start();

        ListView listView = (ListView)findViewById(R.id.layout_anim_list);
        ArrayList<String> listItems = new ArrayList<String>();
        for(int i = 0;i<99;i++){
            listItems.add("Item " + i);
        }
        MyListAdapter adapter = new MyListAdapter(listItems);
        listView.setAdapter(adapter);

    }

    class MyListAdapter extends BaseAdapter{
         List<String> data;
         MyListAdapter(ArrayList<String> data){
            this.data = data;
        }
        @Override
        public int getCount() {
            return data ==null ? 0 : data.size();
        }

        @Override
        public String getItem(int position) {
            return data == null ? null : data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
             if(convertView == null){
                 convertView =  LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_list,parent,false);
                 holder = new Holder();
                 holder.textView = (TextView)convertView.findViewById(R.id.item_name);
                 convertView.setTag(holder);
             }else {
                 holder = (Holder)convertView.getTag();
             }
            holder.textView.setText(getItem(position));
            return convertView;
        }

        class Holder {
             TextView textView;
        }
    }

}
