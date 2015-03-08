package br.jabarasca.beerapp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import android.os.AsyncTask;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/*OBS: Cabe ao DownloaderPostAction da classe HtmlDownloaderTask implementar uma forma de carregar a imagem usando
  		esta classe, já que é na HtmlDownloaderTask que está o código HTML com o endereço da imagem.
 */

public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
	private final int READ_TIMEOUT_MILLIS = 10000, CONN_TIMEOUT_MILLIS = 15000;
	private final String NULL_POINTER_EXCEPTION_MSG = "DownloaderPostAction cannot be NULL.";
	
	private DownloaderPostAction postAction;
	
	public ImageDownloaderTask(DownloaderPostAction postAction) {
		if(postAction == null) {
			throw new NullPointerException(NULL_POINTER_EXCEPTION_MSG);
		}
		else {
			this.postAction = postAction;
		}
	}
	
	@Override
	protected Bitmap doInBackground(String... urls) {
		InputStream inStream = null;
		try {
			HttpURLConnection httpConn = this.establishHttpConnection(urls[0]);
			inStream = httpConn.getInputStream();
			Bitmap bitmap = getImageFromHttpConnection(inStream);
			httpConn.disconnect();
			return bitmap;
			
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}catch(SocketTimeoutException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(inStream != null) {
				try {
					inStream.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
			
		return null;
	}
	
	@Override
	protected void onPostExecute(Bitmap bitmap) {
		this.postAction.imageDownloaderPostAction(bitmap);
	}

	private Bitmap getImageFromHttpConnection(InputStream input) throws SocketTimeoutException {
		return BitmapFactory.decodeStream(input);
	}
	
	private HttpURLConnection establishHttpConnection(String web_url) throws MalformedURLException, IOException, SocketTimeoutException {
		URL url = new URL(web_url);
		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
		httpConn.setConnectTimeout(CONN_TIMEOUT_MILLIS);
		httpConn.setReadTimeout(READ_TIMEOUT_MILLIS);
		httpConn.setDoInput(true);
		httpConn.setRequestMethod("GET");
		httpConn.connect();
		return httpConn;
	}
	
}
