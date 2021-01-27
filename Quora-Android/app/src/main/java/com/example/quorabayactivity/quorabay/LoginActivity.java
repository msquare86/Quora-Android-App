package com.example.quorabayactivity.quorabay;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.builders.RetrofitBuilderCommon;
import com.example.quorabayactivity.quorabay.models.DecodedJWTEntity;
import com.example.quorabayactivity.quorabay.models.LoginSendCommonDTO;
import com.example.quorabayactivity.quorabay.models.UserRegisterResponse;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {


    EditText et_login_email;
    EditText et_login_password;
    Button bt_login_user;
    Button bt_login_register_user;
    String notificationToken;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_login_email = findViewById(R.id.et_login_email);
        et_login_password = findViewById(R.id.et_login_password);
        bt_login_user = findViewById(R.id.bt_login_user);
        bt_login_register_user = findViewById(R.id.bt_login_register_user);

        int channelId = getIntent().getIntExtra("channelId", -1);
        if(channelId == -1) {
            throw new NullPointerException();
        }

        bt_login_register_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                intent.putExtra("channelId", channelId);
                startActivity(intent);
            }
        });

        bt_login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_login_email.getText().toString();
                String password = et_login_password.getText().toString();

                if(email.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please provide all your credentials!", Toast.LENGTH_SHORT).show();
                } else {

                    LoginSendCommonDTO loginSendCommonDTO = new LoginSendCommonDTO();
                    loginSendCommonDTO.setChannelId(channelId);
                    loginSendCommonDTO.setEmail(email);
                    loginSendCommonDTO.setPassword(password);
                    loginSendCommonDTO.setOauth(-1);

                    FirebaseMessaging.getInstance().getToken()
                            .addOnCompleteListener(new OnCompleteListener<String>() {
                                @Override
                                public void onComplete(@NonNull Task<String> task) {
                                    if (!task.isSuccessful()) {
                                        Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                                        return;
                                    }

                                    // Get new FCM registration token
                                    notificationToken = task.getResult();

                                    // Log and toast
                                    String msg = getString(R.string.msg_token_fmt, notificationToken);
                                    Log.d("TAG", msg);
                                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            });

                    Retrofit retrofit = RetrofitBuilderCommon.getInstance();
                    IPostAPI iApiInterface = retrofit.create(IPostAPI.class);
                    Call<UserRegisterResponse> apiCallRegisterUser = iApiInterface.loginUser(loginSendCommonDTO);
                    apiCallRegisterUser.enqueue(new Callback<UserRegisterResponse>() {
                        @Override
                        public void onResponse(Call<UserRegisterResponse> call, Response<UserRegisterResponse> response) {
                            Log.d("TAG", "onResponse: " + response.body().toString());
                            DecodedJWTEntity decodedJWTEntity = new DecodedJWTEntity();
                            try {
                                String[] split = response.body().getJwt().split("\\.");
                                Log.d("TAG", "onResponse: json object " + getJson(split[1]));
                                ObjectMapper objectMapper = new ObjectMapper();
                                decodedJWTEntity = objectMapper.readValue(getJson(split[1]).getBytes(), DecodedJWTEntity.class);
                                Log.d("TAG", "onResponse: " + decodedJWTEntity.getUserId());

                                // TODO: 25/1/21 add this user into your respective platform's microservice



                                if(decodedJWTEntity.getUserId() != null) {
                                    Call<ResponseBody> apiCallAppendNotification = iApiInterface.appendNotification("userId", notificationToken);
                                    apiCallAppendNotification.enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            // TODO: 25/1/21 START LANDING PAGE ACTIVITY
                                            Intent gotoHomePage = new Intent(LoginActivity.this, QuorabayHomeActivity.class);
                                            startActivity(gotoHomePage);
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                                            Log.d("TAG", "onFailure: " + t.getMessage());
                                        }
                                    });
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Log.d("TAG", "onResponse: " + decodedJWTEntity.getUserId());
                        }

                        @Override
                        public void onFailure(Call<UserRegisterResponse> call, Throwable t) {
                            Log.d("TAG", "onFailure: " + t.getMessage());
                        }
                    });

                }

            }
        });
    }

    public static void decoded(String JWTEncoded) throws Exception {
        try {
            String[] split = JWTEncoded.split("\\.");
            Log.d("JWT_DECODED", "Header: " + getJson(split[0]));
            Log.d("JWT_DECODED", "Body: " + getJson(split[1]));
            Log.d("JWT_DECODED", "IDK" + getJson(split[2]));
        } catch (UnsupportedEncodingException e) {
            e.getStackTrace();
        }
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException{
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
