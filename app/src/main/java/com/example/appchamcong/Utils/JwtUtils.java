package com.example.appchamcong.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class JwtUtils {
    private static final String PREF_NAME = "AppPrefs";
    private static final String KEY_JWT_TOKEN = "JWT_TOKEN";
    private SharedPreferences sharedPreferences;

    // Constructor
    public JwtUtils(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    // Lưu token
    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_JWT_TOKEN, token);
        editor.apply(); // Sử dụng apply() thay vì commit() để tránh block thread chính
    }

    // Lấy token
    public String getToken() {
        return sharedPreferences.getString(KEY_JWT_TOKEN, null); // trả về null nếu không có token
    }

    // Xóa token (nếu cần)
    public void clearToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_JWT_TOKEN);
        editor.apply();
    }
}