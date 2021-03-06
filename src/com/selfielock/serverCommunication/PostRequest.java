package com.selfielock.serverCommunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import android.os.AsyncTask;
import android.util.Log;

public class PostRequest extends AsyncTask <String, Void, String> {

	private String url;
	private String jsonString;
	private String response;
	
	public PostRequest(String url, String jsonString) {
		this.url = url;
		this.jsonString = jsonString;
		this.response = "";
	}
	
	public String getResponse()
	{
	    return response;
	}
	
	@Override
	protected String doInBackground(String... params) {
        String answer = "";
    	HttpClient httpClient = new DefaultHttpClient();
    	HttpPost post = new HttpPost(url);
    	HttpResponse httpResponse = null;
    	try {
			post.setEntity(new StringEntity(jsonString, "UTF8"));
			post.setHeader("Content-type", "application/json");
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
    	try {
			httpResponse = httpClient.execute(post);
			HttpEntity httpEntity = httpResponse.getEntity();
			if(httpEntity != null)
			{
				answer = convertInputStreamToString(httpEntity.getContent());
			}
		} catch (ClientProtocolException cpe) {
			cpe.printStackTrace();
			Log.d("POST", cpe.getLocalizedMessage());
		} catch (IOException ioe) {
			ioe.printStackTrace();
			Log.d("POST", ioe.getLocalizedMessage());
		}
    	return answer;
	}
	
    @Override
    protected void onPostExecute(String result) {
        //a la fin de l'execution  
    	Log.d("POST Responses", result);
    	response = result;
    }
    
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }
}
