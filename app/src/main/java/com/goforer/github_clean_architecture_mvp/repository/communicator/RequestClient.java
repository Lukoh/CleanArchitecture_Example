package com.goforer.github_clean_architecture_mvp.repository.communicator;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public enum RequestClient {
    INSTANCE;

    private static final long READ_TIME_OUT = 5;
    private static final long WRITE_TIME_OUT = 5;
    private static final long CONNECT_TIME_OUT = 5;

    public static final String SUCCESS = "SUCCESS";

    private RequestMethod mRequestor;

    private String mRawResponseBody;

    public RequestMethod getRequestMethod() {
        if (mRequestor == null) {
            final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                    .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);

            httpClient.addInterceptor(new Interceptor() {
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

            OkHttpClient client = httpClient.build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mRequestor = retrofit.create(RequestMethod.class);
        }

        return mRequestor;
    }

    @SuppressWarnings("unused")
    public String getBody() {
        return mRawResponseBody;
    }
}
