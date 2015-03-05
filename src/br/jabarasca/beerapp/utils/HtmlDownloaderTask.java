package br.jabarasca.beerapp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;

public class HtmlDownloaderTask extends AsyncTask<String, Void, String> {
	private final String NULL_POINTER_EXCEPTION_MSG = "DownloaderPostAction cannot be NULL."; 
	private final int READ_TIMEOUT_MILLIS = 10000, CONN_TIMEOUT_MILLIS = 15000;
	
	private DownloaderPostAction postAction;
	
	public HtmlDownloaderTask(DownloaderPostAction postAction) { 
		if(postAction == null) {
			throw new NullPointerException(NULL_POINTER_EXCEPTION_MSG);
		}
		else {
			this.postAction = postAction;
		}
	}
	
	@Override
	protected String doInBackground(String... urls) {
		InputStream inStream = null;
		try{
			HttpURLConnection httpConn = establishHttpConnection(urls[0]);
			inStream = httpConn.getInputStream();
			return getHtmlContent(inStream, httpConn.getContentLength()); 
			
		}catch(MalformedURLException e) {
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
	protected void onPostExecute(String htmlResultString) {
		postAction.htmlDownloaderPostAction(htmlResultString);
	}
	
	private String getHtmlContent(InputStream inStream, int contentLength) throws IOException {
		Reader inputReader = new InputStreamReader(inStream, "UTF-8");
		char[] buffer = new char[contentLength];
		inputReader.read(buffer);
		return new String(buffer);
	}
	
	private HttpURLConnection establishHttpConnection(String web_url) throws MalformedURLException, IOException {
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
