package com.retroarch.browser;

import java.io.IOException;
import com.retroarch.browser.preferences.util.UserPreferences;
import android.annotation.SuppressLint;
import android.app.NativeActivity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;

public final class RetroActivity extends NativeActivity
{
	Camera mCamera;
	
	public void onCameraStart()
	{
		mCamera.startPreview();
	}
	
	public void onCameraStop()
	{
		mCamera.stopPreview();
	}
	
	public void onCameraInit()
	{
		mCamera = Camera.open();
	}
	
	public void onCameraFree()
	{
		mCamera.release();
		mCamera = null;
	}
	
	@SuppressLint("NewApi")
	public void onCameraSetTexture(int gl_texid) throws IOException
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			mCamera.setPreviewTexture(new SurfaceTexture(gl_texid));
		else
			mCamera.setPreviewDisplay(null);
	}
	
	@Override
	public void onDestroy()
	{
		UserPreferences.readbackConfigFile(this);
	}

	@Override
	public void onLowMemory()
	{
	}

	@Override
	public void onTrimMemory(int level)
	{
	}
}
