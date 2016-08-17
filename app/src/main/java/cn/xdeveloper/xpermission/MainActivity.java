package cn.xdeveloper.xpermission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_normal).setOnClickListener(this);
        findViewById(R.id.btn_dispatcher).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_normal:
                startActivity(new Intent(this,NormalPermissionActivity.class));

                break;
            case R.id.btn_dispatcher:
                startActivity(new Intent(this,PermissionsDispatcherActivity.class));

                break;
        }
    }
}
