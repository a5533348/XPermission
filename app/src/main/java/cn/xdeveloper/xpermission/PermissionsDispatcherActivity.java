package cn.xdeveloper.xpermission;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Lai on 2016/8/17.
 */
public class PermissionsDispatcherActivity extends BasePermissionActivity implements View.OnClickListener{

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
        switch (v.getId()){
            case R.id.btn_call:
                requestCallPermission(new PermissionHandler() {
                    @Override
                    public void onGranted() {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        Uri data = Uri.parse("tel:10086");
                        intent.setData(data);
                        startActivity(intent);
                    }
                });

                break;
            case R.id.btn_camera:

                requestCameraPermission(new PermissionHandler() {
                    @Override
                    public void onGranted() {
                        Intent intent = new Intent(); //调用照相机
                        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(intent);
                    }
                });

                break;
        }
    }
}
