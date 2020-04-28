package com.ryan.texturecamerapreview.camera;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.Log;
import android.view.TextureView;

import com.ryan.texturecamerapreview.api.ICamera;
import com.ryan.texturecamerapreview.utils.TextureUtils;

/**
 * @anchor: andy
 * @date: 18-11-11
 */

public class CameraV1Pick implements TextureView.SurfaceTextureListener {

    private static final String TAG = "zhf-debug";

    private TextureView mTextureView;

    private int mCameraId;

    private ICamera mCamera;

    private TextureEGLHelper mTextureEglHelper;

    public void bindTextureView(TextureView textureView) {
        this.mTextureView = textureView;
        mTextureEglHelper = new TextureEGLHelper();
        mTextureView.setSurfaceTextureListener(this);
    }

    SurfaceTexture mSurfaceTexture;
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Log.d(TAG, "onSurfaceTextureAvailable");
        //加载OES纹理ID
        final int textureId = TextureUtils.loadOESTexture(); // 初始化一个OES textureId
        //初始化操作
        mTextureEglHelper.initEgl(mTextureView, textureId);
        //自定义的SurfaceTexture
        mSurfaceTexture = mTextureEglHelper.loadOESTexture();
        //前置摄像头
        mCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;
        mCamera = new CameraV1((Activity) mTextureView.getContext());
        if (mCamera.openCamera(mCameraId)) {
            mCamera.setPreviewTexture(mSurfaceTexture);
            mCamera.enablePreview(true);
        } else {
            Log.e(TAG, "openCamera failed");
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        Log.d(TAG, "onSurfaceTextureSizeChanged width="+width+", height="+height);
        mTextureEglHelper.onSurfaceChanged(width, height);
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        Log.d(TAG, "onSurfaceTextureDestroyed ");
        if (mCamera != null) {
            mCamera.enablePreview(false);
            mCamera.closeCamera();
            mCamera = null;
        }
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
//        Log.d(TAG, "onSurfaceTextureUpdated ");
    }

    public void onDestroy() {
        if (mTextureEglHelper != null) {
            mTextureEglHelper.onDestroy();
        }
    }
}
