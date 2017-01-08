# XPermission 最快最简单的方式适配Android6.0权限

使用方法：继承XPermissActivity,调用requestPermission()获取权限



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
