package ca.mcgill.ecse321.library;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.entity.StringEntity;

public class HttpUtils {
    public static final String DEFAULT_BASE_URL = "https://library-backend-534a.herokuapp.com/";

    private static String baseUrl;
    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        baseUrl = DEFAULT_BASE_URL;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        HttpUtils.baseUrl = baseUrl;
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setURLEncodingEnabled(false);
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }
    public static void postJson(Context context, String url, StringEntity json , AsyncHttpResponseHandler responseHandler) {
        client.post(context, getAbsoluteUrl(url), json, "application/json", responseHandler);
    }
        public static void put (String url, RequestParams params, AsyncHttpResponseHandler
        responseHandler){
            client.put(getAbsoluteUrl(url), params, responseHandler);

        }

        public static void getByUrl (String url, RequestParams params, AsyncHttpResponseHandler
        responseHandler){
            client.get(url, params, responseHandler);
        }

        public static void postByUrl (String url, RequestParams params, AsyncHttpResponseHandler
        responseHandler){
            client.post(url, params, responseHandler);
        }

        public static void delete (String url, RequestParams params, AsyncHttpResponseHandler
        responseHandler){
            client.delete(url, params, responseHandler);
        }
        private static String getAbsoluteUrl(String relativeUrl){
            return baseUrl + relativeUrl;
        }
}
