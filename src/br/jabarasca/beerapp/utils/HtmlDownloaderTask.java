package br.jabarasca.beerapp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

public class HtmlDownloaderTask extends AsyncTask<String, Void, String> {
	private final String NULL_POINTER_ERROR_MSG = "Activity is NULL.";
	private final int READ_TIMEOUT_MILLIS = 10000, CONN_TIMEOUT_MILLIS = 15000;
	
	private DownloaderPostAction postAction;
	public boolean networkIsAvailable;
	
	public HtmlDownloaderTask(ActionBarActivity activity) {
		if(activity == null) {
			throw new NullPointerException(NULL_POINTER_ERROR_MSG);
		}
		else {
			verifyNetworkConnection(activity.getApplicationContext());
			postAction = (DownloaderPostAction)activity;
		}
	}
	
	@Override
	protected String doInBackground(String... urls) {
		InputStream inStream = null;
		try{
			HttpURLConnection httpConn = establishHttpConnection(urls[0]);
			inStream = httpConn.getInputStream();
			String htmlContent = getHtmlContent(inStream, httpConn.getContentLength());
			httpConn.disconnect();
			return htmlContent;
			
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}catch(SocketTimeoutException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(NegativeArraySizeException e) {
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
	protected void onPostExecute(String htmlResultString) {
		postAction.htmlDownloaderPostAction(htmlResultString);
	}
	
	private void verifyNetworkConnection(Context context) {
		ConnectivityManager connManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connManager.getActiveNetworkInfo();
		if(!(netInfo != null && netInfo.isConnected())) {
			networkIsAvailable = false;
		}
		else {
			networkIsAvailable = true;
		}
	}
	
	private String getHtmlContent(InputStream inStream, int contentLength) throws IOException, NegativeArraySizeException, SocketTimeoutException {
		Reader inputReader = new InputStreamReader(inStream, "UTF-8");
		char[] buffer = new char[contentLength];
		inputReader.read(buffer);
		return new String(buffer);
	}
	
	private HttpURLConnection establishHttpConnection(String web_url) throws MalformedURLException, IOException, SocketTimeoutException {
		URL url = new URL(web_url);
		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
		httpConn.setReadTimeout(READ_TIMEOUT_MILLIS);
		httpConn.setConnectTimeout(CONN_TIMEOUT_MILLIS);
		httpConn.setRequestMethod("GET");
		httpConn.setDoInput(true);
		httpConn.connect();
		return httpConn;
	}
}
