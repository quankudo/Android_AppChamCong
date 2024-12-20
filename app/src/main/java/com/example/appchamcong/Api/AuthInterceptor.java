package com.example.appchamcong.Api;

import android.content.Context;

import com.example.appchamcong.Utils.JwtUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private String token;
    private Context context;

    public AuthInterceptor(String token, Context context) {
        this.token = token;
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if(token==null){
            token = new JwtUtils(context).getToken();
        }
        Request.Builder builder = originalRequest.newBuilder()
                .header("Authorization", "Bearer " + token);  // Thêm token vào header
        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }
}
