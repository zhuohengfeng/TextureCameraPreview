package com.ryan.texturecamerapreview.api;

import android.graphics.SurfaceTexture;


/**
 * @anchor: andy
 * @date: 2018-11-12
 * @description:
 */
public interface ICamera {

    boolean openCamera(int cameraId);

    void enablePreview(boolean enable);

    void setPreviewTexture(SurfaceTexture surfaceTexture);

    void closeCamera();
}
