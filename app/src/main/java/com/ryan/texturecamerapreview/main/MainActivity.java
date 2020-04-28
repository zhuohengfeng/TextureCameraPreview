package com.ryan.texturecamerapreview.main;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.core.app.ActivityCompat;

import com.ryan.texturecamerapreview.R;
import com.ryan.texturecamerapreview.camera.CameraV1Pick;
import com.ryan.texturecamerapreview.utils.DisplayUtils;

public class MainActivity extends Activity {

    private static final int PERMISSION_CODE = 1000;

    private TextureView mTextureView;

    private CameraV1Pick mCameraPick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applyPermission();
    }

    private void applyPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_CODE);
        } else {
            setupView();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE && grantResults != null && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupView();
            }
        }
    }

    private void setupView() {
        mTextureView = findViewById(R.id.camera_textureView);

        mCameraPick = new CameraV1Pick();
        mCameraPick.bindTextureView(mTextureView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCameraPick != null) {
            mCameraPick.onDestroy();
            mCameraPick = null;
        }
    }

    private boolean isFullScreen = false;

    public void onSwitchScreen(View view) {
//        mCameraPick.onDestroy();

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mTextureView.getLayoutParams();
        if(isFullScreen) {
            //切换到窗口
            lp.removeRule(RelativeLayout.CENTER_IN_PARENT);
            lp.width = (int)DisplayUtils.dp2px(200);
            lp.height = (int) DisplayUtils.dp2px(200);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        } else {
            //切换到全屏
            lp.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            lp.width = (int)DisplayUtils.dp2px(400);
            lp.height = (int) DisplayUtils.dp2px(400);
        }
        mTextureView.setLayoutParams(lp);
        isFullScreen = !isFullScreen;
    }
}
