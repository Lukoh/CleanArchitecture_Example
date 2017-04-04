package com.goforer.clean_architecture.dependencyinjection.module.network;

import com.goforer.clean_architecture.presentation.model.data.AutoValueGsonFactory;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private static final long READ_TIME_OUT = 5;
    private static final long WRITE_TIME_OUT = 5;
    private static final long CONNECT_TIME_OUT = 5;

    private String mBaseUrl;

    private static String mRawResponseBody;

    public NetworkModule(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);

        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Connection", "keep-alive")
                        .method(original.method(), original.body())
                        .build();

                Response response = chain.proceed(request);

                mRawResponseBody = response.body().string();

                return response.newBuilder()
                        .body(ResponseBody.create(response.body().contentType(),
                                mRawResponseBody)).build();
            }
        });

        return okHttpClient.build();
    }


    @Provides
    @Singleton
    Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        GsonConverterFactory factory = GsonConverterFactory.create(
                new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create())
                        .create());

        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(factory)
                .build();
    }

    @SuppressWarnings("unused")
    public static String getRawResponseBody() {
        return mRawResponseBody;
    }
}

