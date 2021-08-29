package com.example.task.network;

import android.app.Application;
import android.content.Context;

import com.example.task.R;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThisApp extends Application {

    private static Api api;


    public static void setApi() {
        api = null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }


    public static Api getApiCommonController(Context context) {
        setApi();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        client.addInterceptor(chain -> {
            Request.Builder builder = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("X-APP_NAME", context.getResources().getString(R.string.app_name));
//            String token = Preferences.getInstance(context).getToken();
//            if (null != token && !token.equals("")) {
//                builder.addHeader("Authorization", "Bearer " + token);
//            }
            return chain.proceed(builder.build());
        });
        String url=Api.BASE_URL;

        client.addInterceptor(interceptor);

        if (api == null) {
            api = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .setLenient()
                            .create()))
                    .build()
                    .create(Api.class);
        }
        return api;
    }




}
