package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button notificationButton;
    public boolean isCancel = true;
    public boolean activeRequest = false; //use boolean active request for notification

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationButton = findViewById(R.id.notificationButton); //references notificationButton in xml by id

        //special devices compatibility if statement
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Notification1", "Itemnotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //notification code
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "Notification1"); //Notification 1 can be universal
                builder.setContentTitle("Warrior Dining");
                builder.setContentText("Your food is available today!");
                builder.setSmallIcon(R.drawable.ic_launcher_background); //pass new notification icon if you fancy
                builder.setAutoCancel(true); //something for pending intent or whatever
                //boolean toggle

                isCancel = !isCancel;
                if (isCancel = false){
                    activeRequest = true;
                }
                else {
                    activeRequest = false;
                }




                //following notifies user
                //use following code when api sends out matching menu item
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);

                //getting perms? don't know what this wants
                /*
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                */
                managerCompat.notify(1, builder.build()); //ID can be passed replace (1)

                                                  }
                                              }
        );}



}