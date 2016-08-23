package cn.xdeveloper.xpermission;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

/**
 * Demo
 * Created by Lai on 2016/8/17.
 */
public class MainActivity extends XPermissionActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        findViewById(R.id.btn_camera).setOnClickListener(this);
        findViewById(R.id.btn_call).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_camera:

                    requestPermission(new String[]{Manifest.permission.CAMERA}, new PermissionHandler() {
                        @Override
                        public void onGranted() {
                            Intent intent = new Intent(); //调用照相机
                            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivity(intent);
                        }

                        @Override
                        public void onDenied() {
                            Toast.makeText(MainActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                        }

                    });

                break;

            case R.id.btn_call:

                requestPermission(new String[]{Manifest.permission.CALL_PHONE}, new PermissionHandler() {
                    @Override
                    public void onGranted() {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        Uri data = Uri.parse("tel:10086");
                        intent.setData(data);
                        startActivity(intent);
                    }

                    @Override
                    public boolean onNeverAsk() {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("权限申请")
                                .setMessage("在设置-应用-权限中开始电话权限，以保证功能的正常使用")
                                .setPositiveButton("去开启", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                                        intent.setData(uri);
                                        startActivity(intent);

                                        dialog.dismiss();
                                    }
                                })
                                .setNegativeButton("取消", null)
                                .setCancelable(false)
                                .show();

                        return true;
                    }

                });

                break;
        }
    }


}
