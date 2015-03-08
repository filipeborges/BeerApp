package br.jabarasca.beerapp.utils;

import android.graphics.Bitmap;

public interface DownloaderPostAction {
	public void htmlDownloaderPostAction(String htmlContent);
	public void imageDownloaderPostAction(Bitmap bitmap);
}
