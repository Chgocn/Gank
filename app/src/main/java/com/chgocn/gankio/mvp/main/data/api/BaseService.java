package com.chgocn.gankio.mvp.main.data.api;

import android.util.Log;

import com.chgocn.gankio.mvp.main.domain.ResponseJson;
import com.chgocn.gankio.mvp.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.functions.Func1;

/**
 * Created by chgocn.
 */
public class BaseService {

    private static final String TAG = "BaseService";

    public Retrofit getAdapter() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .build();

        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)//排除属性final,transient,static
                .serializeNulls()//当需要序列化的值为空时，采用null映射，否则会把该字段省略
                .create();

        return new Retrofit.Builder()
                .baseUrl(Constants.ENDPOINT)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.addConverterFactory(KsConverterFactory.create())
                .build();
    }

    public static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            String requestLog = String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers());
            //YLog.d(String.format("Sending request %s on %s%n%s",
            //        request.url(), chain.connection(), request.headers()));
            if (request.method().compareToIgnoreCase("post") == 0) {
                requestLog = requestLog + "  " + bodyToString(request);
            }
            Log.d("Retrofit", "Retrofit request" + "\n" + requestLog);

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();

            String responseLog = String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers());

            String bodyString = response.body().string();

            Log.d("Retrofit", "Retrofit response" + "\n" + responseLog + bodyString);

            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), bodyString))
                    .build();
            //return response;
        }
    }


    public static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    public class ResponseFunc<T> implements Func1<ResponseJson<T>,T> {

        @Override
        public T call(ResponseJson<T> data) {
            Log.e(TAG,data.toString());

            if (data.isError()) {
                //throw new KsApiException(data.getCode(),data.getMsg());
            }
            return data.getResults();
        }
    }
}