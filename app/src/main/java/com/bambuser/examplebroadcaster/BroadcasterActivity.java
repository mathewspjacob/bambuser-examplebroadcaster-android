package com.bambuser.examplebroadcaster;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;

import com.bambuser.broadcaster.BroadcastStatus;
import com.bambuser.broadcaster.Broadcaster;
import com.bambuser.broadcaster.CameraError;
import com.bambuser.broadcaster.ConnectionError;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class BroadcasterActivity extends AppCompatActivity {
    private static final String LOGTAG = "BroadcasterActivity";

    private static final String APPLICATION_ID = "CHANGE_ME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcaster);
        mPreviewSurface = findViewById(R.id.PreviewSurfaceView);
        mBroadcaster = new Broadcaster(this, APPLICATION_ID, mBroadcasterObserver);
        mBroadcaster.setRotation(getWindowManager().getDefaultDisplay().getRotation());
        mBroadcastButton = findViewById(R.id.BroadcastButton);
        mBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBroadcaster.canStartBroadcasting())
                    mBroadcaster.startBroadcast();
                else
                    mBroadcaster.stopBroadcast();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBroadcaster.onActivityDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBroadcaster.onActivityPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBroadcaster.setCameraSurface(mPreviewSurface);
        mBroadcaster.onActivityResume();
        mBroadcaster.setRotation(getWindowManager().getDefaultDisplay().getRotation());
        if (!hasPermission(Manifest.permission.CAMERA)
                && !hasPermission(Manifest.permission.RECORD_AUDIO))
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO}, 1);
        else if (!hasPermission(Manifest.permission.RECORD_AUDIO))
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECORD_AUDIO}, 1);
        else if (!hasPermission(Manifest.permission.CAMERA))
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);
    }

    private boolean hasPermission(String permission) {
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private Broadcaster.Observer mBroadcasterObserver = new Broadcaster.Observer() {
        @Override
        public void onConnectionStatusChange(BroadcastStatus broadcastStatus) {
            Log.i(LOGTAG, "Received status change: " + broadcastStatus);
            if (broadcastStatus == BroadcastStatus.STARTING)
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            if (broadcastStatus == BroadcastStatus.IDLE)
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            mBroadcastButton.setText(broadcastStatus == BroadcastStatus.IDLE ? "Broadcast" : "Disconnect");
        }
        @Override
        public void onStreamHealthUpdate(int i) {
        }
        @Override
        public void onConnectionError(ConnectionError connectionError, String s) {
            Log.w(LOGTAG, "Received connection error: " + connectionError + ", " + s);
        }
        @Override
        public void onCameraError(CameraError cameraError) {
            Log.w(LOGTAG, "Received camera error: " + cameraError);
        }
        @Override
        public void onChatMessage(String s) {
        }
        @Override
        public void onResolutionsScanned() {
        }
        @Override
        public void onCameraPreviewStateChanged() {
        }
        @Override
        public void onBroadcastInfoAvailable(String s, String s1) {
        }
        @Override
        public void onBroadcastIdAvailable(String s) {
        }
    };

    SurfaceView mPreviewSurface;
    Broadcaster mBroadcaster;
    Button mBroadcastButton;
}
