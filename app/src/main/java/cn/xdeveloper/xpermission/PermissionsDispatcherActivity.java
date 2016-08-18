package cn.xdeveloper.xpermission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by Lai on 2016/8/17.
 */
@RuntimePermissions
public class PermissionsDispatcherActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        setTitle("PermissionsDispatcher");

        findViewById(R.id.btn_camera).setOnClickListener(this);
        findViewById(R.id.btn_call).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call:
                PermissionsDispatcherActivityPermissionsDispatcher.startCallWithCheck(this);
                break;
            case R.id.btn_camera:
                PermissionsDispatcherActivityPermissionsDispatcher.startCameraWithCheck(this);
                break;
        }
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera() {
        Intent intent = new Intent(); //调用照相机
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void startCall(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:10086");
        intent.setData(data);
        startActivity(intent);
    }
}
