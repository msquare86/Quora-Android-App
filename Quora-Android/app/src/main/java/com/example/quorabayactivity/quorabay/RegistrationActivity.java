package com.example.quorabayactivity.quorabay;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.builders.RetrofitBuilderCommon;
import com.example.quorabayactivity.quorabay.builders.RetrofitFollower;
import com.example.quorabayactivity.quorabay.models.DecodedJWTEntity;
import com.example.quorabayactivity.quorabay.models.UserDetails;
import com.example.quorabayactivity.quorabay.models.UserRegisterEntity;
import com.example.quorabayactivity.quorabay.models.UserRegisterResponse;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistrationActivity extends AppCompatActivity {

    EditText et_register_name;
    EditText et_register_username;
    EditText et_register_email;
    EditText et_register_password;
    EditText et_register_mobile_number;
    EditText et_register_date_of_birth;
    EditText et_register_bio;
    ImageView iv_register_profile_add;
    Button bt_register_add_image;
    Button bt_register_add_user;
    Button bt_register_login;
    AutoCompleteTextView autoCompleteTextView;
    private static final int RESULT_LOAD_IMAGE = 101;
    private StorageReference mStorageRef;
    Uri imageFileUri;
    String imageUrl = "https://uploads-ssl.webflow.com/5f72ebbe008321b20e82ee2a/5f86b992cee6a4510b2feadb_QB%20intro%20video%201-poster-00001.jpg";
    long mobileNumberLong = 0;
    int typeInt = 1;
    String notificationToken;
    List<String> areaOfInterests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        et_register_name = findViewById(R.id.et_register_name);
        et_register_username = findViewById(R.id.et_register_username);
        et_register_email = findViewById(R.id.et_register_email);
        et_register_password = findViewById(R.id.et_register_password);
        et_register_mobile_number = findViewById(R.id.et_register_mobile_num);
        et_register_date_of_birth = findViewById(R.id.et_register_date_of_birth);
        et_register_bio = findViewById(R.id.et_register_bio);
        iv_register_profile_add = findViewById(R.id.iv_register_profile_pic);
        bt_register_add_image = findViewById(R.id.bt_register_add_image);
        bt_register_add_user = findViewById(R.id.bt_register_add_user);
        bt_register_login = findViewById(R.id.bt_register_login);
        autoCompleteTextView = findViewById(R.id.actv_register_dropdown_profile_type);

        areaOfInterests = new ArrayList<>();

        ArrayList<String> genderList = new ArrayList<>();
        genderList.add("Public");
        genderList.add("Private");
        genderList.add("Corporate");
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item_profile, genderList);
        autoCompleteTextView.setAdapter(genderAdapter);

        int channelId = getIntent().getIntExtra("channelId", -1);
        if(channelId == -1) {
            throw new NullPointerException();
        }

        bt_register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                intent.putExtra("channelId", channelId);
                startActivity(intent);
            }
        });

        et_register_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    if(!isValidEmail(et_register_email.getText())) {
                        et_register_email.setError("Provide Valid Email");
                    }
                }
            }
        });

        et_register_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    if(!isValidPassword(et_register_password.getText().toString())) {
                        et_register_password.setError("The password should be : \n" +
                                "of at least length 8 and at most 20 \n" +
                                "having a digit \n" +
                                "having a upper case letter \n" +
                                "having a lower case letter \n" +
                                "having a special character \n" +
                                "and not having whitespaces");
                    }
                }
            }
        });

        bt_register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });

        bt_register_add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, RESULT_LOAD_IMAGE);
            }
        });

        bt_register_add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_register_username.getText().toString();
                String email = et_register_email.getText().toString();
                String password = et_register_password.getText().toString();
                String mobileNumString = et_register_mobile_number.getText().toString();
                if(!(mobileNumString.equals("") || TextUtils.isEmpty(mobileNumString))) {
                    mobileNumberLong = Long.parseLong(mobileNumString);
                }
                String type = autoCompleteTextView.getText().toString();
                switch (type) {
                    case "public" : typeInt = 1;
                        break;
                    case "private" : typeInt = 2;
                        break;
                    case "corporate" : typeInt = 3;
                        break;
                }

                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(RegistrationActivity.this, "Make sure required fields are not empty!", Toast.LENGTH_SHORT).show();
                } else if(!isValidEmail(email) || !isValidPassword(password)) {
                    Toast.makeText(RegistrationActivity.this, "Provide valid email and password!", Toast.LENGTH_SHORT).show();
                }
                else {

                    mStorageRef = FirebaseStorage.getInstance().getReference();
                    StorageReference storageReference = mStorageRef.child("images/"+email);
                    storageReference.putFile(imageFileUri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();
                                    downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            imageUrl = uri.toString();
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                // Create channel to show notifications.
                                                String channelId  = getString(R.string.default_notification_channel_id);
                                                String channelName = getString(R.string.default_notification_channel_name);
                                                NotificationManager notificationManager =
                                                        getSystemService(NotificationManager.class);
                                                notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                                                        channelName, NotificationManager.IMPORTANCE_LOW));
                                            }

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
                                                            Toast.makeText(RegistrationActivity.this, msg, Toast.LENGTH_SHORT).show();
                                                            notificationToken = new String(msg);

                                                            UserRegisterEntity userRegisterEntity = new UserRegisterEntity();
                                                            StringBuilder areaOfInterestString = new StringBuilder();
                                                            for (String areaInterest :
                                                                    areaOfInterests) {
                                                                areaOfInterestString.append(areaInterest + ",");
                                                            }
                                                            userRegisterEntity.setAreaOfInterests(areaOfInterestString.toString());
                                                            userRegisterEntity.setBio(et_register_bio.getText().toString());
                                                            userRegisterEntity.setChannelId(channelId);
                                                            userRegisterEntity.setDateOfBirth(et_register_date_of_birth.getText().toString());
                                                            userRegisterEntity.setEmail(et_register_email.getText().toString());
                                                            userRegisterEntity.setMaster(false);
                                                            userRegisterEntity.setMobileNumber(mobileNumberLong);
                                                            userRegisterEntity.setName(et_register_name.getText().toString());
                                                            userRegisterEntity.setNotificationToken(notificationToken);
                                                            userRegisterEntity.setPassword(et_register_password.getText().toString());
                                                            userRegisterEntity.setType(typeInt);
                                                            userRegisterEntity.setUsername(et_register_username.getText().toString());
                                                            userRegisterEntity.setProfileImage(imageUrl);

                                                            Retrofit retrofit = RetrofitBuilderCommon.getInstance();
                                                            IPostAPI iApiInterface = retrofit.create(IPostAPI.class);
                                                            Call<UserRegisterResponse> apiCallRegisterUser = iApiInterface.register(userRegisterEntity);
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
                                                                        // TODO: 27/01/21 check error code documentation and handle error accordingly

                                                                        if(decodedJWTEntity.getCode() == 100) {
                                                                            Toast.makeText(RegistrationActivity.this, "Email exist", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                        else if(decodedJWTEntity.getCode() == 101){
                                                                            Toast.makeText(RegistrationActivity.this, "userName exist", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                        else if(decodedJWTEntity.getCode() == 102){
                                                                            Toast.makeText(RegistrationActivity.this, "Password is null", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                        else if (decodedJWTEntity.getCode() == 103){
                                                                            Toast.makeText(RegistrationActivity.this, "Email is not in proper format", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                        else if (response.body().getCode() == 0){
                                                                            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
                                                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                                                            editor.putString("QuorabayuserId", decodedJWTEntity.getUserId());
                                                                            editor.putString("QuorabayuserName" , decodedJWTEntity.getUsername());
                                                                            editor.apply();
                                                                            editor.commit();

                                                                            // UserDetails;

                                                                            UserDetails userDetails = new UserDetails();
                                                                            userDetails.setUserId(decodedJWTEntity.getUserId());
                                                                            userDetails.setProfileType(decodedJWTEntity.getType());
                                                                            userDetails.setImageUrl(imageUrl);
                                                                            userDetails.setRanking("Beginner");
                                                                            userDetails.setUserName(decodedJWTEntity.getUsername());

                                                                            Retrofit retrofit = RetrofitFollower.getInstance();
                                                                            IPostAPI iPostAPI = retrofit.create(IPostAPI.class);
                                                                            Call<String> userCall = iPostAPI.saveUser(userDetails);

                                                                            userCall.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Call<String> call, Response<String> response) {
                                                                                    if (response.body() != null){
                                                                                        Toast.makeText(RegistrationActivity.this, "User Save", Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Call<String> call, Throwable t) {
                                                                                    Log.d("Fail User Save", "onFailure: " + t);
                                                                                }
                                                                            });
                                                                            Intent gotoHomePage = new Intent(RegistrationActivity.this , QuorabayHomeActivity.class);
                                                                            finish();
                                                                            gotoHomePage.putExtra("QuorabayUserId" , decodedJWTEntity.getUserId());
                                                                            gotoHomePage.putExtra("QuorabayUserName" , decodedJWTEntity.getUsername());
                                                                            gotoHomePage.putExtra("QuorabayUserEmail" , decodedJWTEntity.getEmail());
                                                                            gotoHomePage.putExtra("QuorabayUserImage" , imageUrl);
                                                                            startActivity(gotoHomePage);
                                                                            // TODO: 25/1/21 add this user into your respective platform's microservice
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
                                                    });

                                            FirebaseMessaging.getInstance().subscribeToTopic("all")
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            String msg = getString(R.string.msg_subscribed);
                                                            if (!task.isSuccessful()) {
                                                                msg = getString(R.string.msg_subscribe_failed);
                                                            }
                                                            Log.d("TAG", msg);
                                                            Toast.makeText(RegistrationActivity.this, msg, Toast.LENGTH_SHORT).show();

                                                        }
                                                    });
                                        }
                                    });
                                }
                            });
                }
            }
        });
    }
    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private boolean isValidPassword(String password) {

        //        It contains at least 8 characters and at most 20 characters.
        //        It contains at least one digit.
        //        It contains at least one upper case alphabet.
        //        It contains at least one lower case alphabet.
        //        It contains at least one special character which includes !@#$%&*()-+=^.
        //        It does not contain any white space.

        if(password == null || password.isEmpty())
            return false;

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMAGE){
            iv_register_profile_add.setImageURI(data.getData());
            bt_register_add_image.setText("Change Profile Image");
            imageFileUri = data.getData();
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_register_automobile:
                if (checked) {
                    areaOfInterests.add("automobile");
                    for (String area :
                            areaOfInterests) {
                        Log.d("TAG", "onCheckboxClicked: " + area);
                    }
                }
                else
                    areaOfInterests.remove("automobile");
                break;
            case R.id.checkbox_register_education:
                if (checked)
                    areaOfInterests.add("education");
                else
                    areaOfInterests.remove("education");
                break;
            case R.id.checkbox_register_fashion:
                if (checked)
                    areaOfInterests.add("fashion");
                else
                    areaOfInterests.remove("fashion");
                break;
            case R.id.checkbox_register_food:
                if (checked)
                    areaOfInterests.add("food");
                else
                    areaOfInterests.remove("food");
                break;
            case R.id.checkbox_register_general:
                if (checked)
                    areaOfInterests.add("general");
                else
                    areaOfInterests.remove("general");
                break;
            case R.id.checkbox_register_sports:
                if (checked)
                    areaOfInterests.add("sports");
                else
                    areaOfInterests.remove("sports");
                break;
        }
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