package com.example.quorabayactivity.quorabay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.builders.RetrofitBuilder;
import com.example.quorabayactivity.quorabay.builders.RetrofitFollower;
import com.example.quorabayactivity.quorabay.dto.ResponseMessage;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.quorabayactivity.R.id.quorabay_userProfile_profileImage;

public class QuorabayUserProfileActivity extends AppCompatActivity {

    ImageView profileImage;
    TextView userName, email;
    Button follow;
    String imageUrl;
    TextView logout;
    TextView level;
//    private Uri imageUri;
//    private FirebaseStorage firebaseStorage;
//    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_user_profile);

        Retrofit retrofit = RetrofitBuilder.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

        String userId = getIntent().getStringExtra("QuorabayUserId");
        String UserName = getIntent().getStringExtra("QuorabayUserName");
        String userImage = getIntent().getStringExtra("QuorabayUserImage");

        profileImage = findViewById(quorabay_userProfile_profileImage);
        Glide.with(this)
                .load(userImage)
                .placeholder(R.drawable.quorabay_profile_image)
                .into(profileImage);


        userName = findViewById(R.id.quorabay_userPorfile_userName);
        userName.setText(UserName);

        Call<Integer> numbersofPostsApiCall = iPostAPI.getNumberOfPostsByUserId(userId);
        TextView numberofPost = findViewById(R.id.tv_quorabay_userPorfile_numbersofPosts);
        numbersofPostsApiCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() != null){
                    numberofPost.setText(String.valueOf(response.body().intValue()));
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(QuorabayUserProfileActivity.this, "Fail Number Of Posts", Toast.LENGTH_SHORT).show();
                Log.e("FailNumberOfPosts", "onFailure: " + t.getMessage() );
            }
        });

        TextView numberOfFollowers = findViewById(R.id.tv_quorabay_userPorfile_numbersofFollowers);

        Call<ResponseMessage> numbersofFollowersApiCall = iPostAPI.getNumberOfFollowersByUserId(userId);
        numbersofFollowersApiCall.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                if (response.body() != null){
                    Log.d("follower", "onResponse: " + response.body().getMessage());
                    numberOfFollowers.setText(response.body().getMessage());
                }else{
                    numberOfFollowers.setText("0");
                }
            }
            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Toast.makeText(QuorabayUserProfileActivity.this, "failFollower", Toast.LENGTH_SHORT).show();
                Log.e("FailNumberOfFollower", "onFailure: " + t.getMessage() );
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);


        logout = findViewById(R.id.tv_quorabay_user_profile_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLogout = new Intent(QuorabayUserProfileActivity.this , LoginActivity.class);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("QuorabayUserId");
                editor.remove("QuorabayUserName");
                editor.remove("QuorabayUserImage");
                editor.apply();
                editor.commit();
                gotoLogout.putExtra("channelId" , 1);
                gotoLogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gotoLogout);
            }
        });

        Retrofit retrofit1 = RetrofitFollower.getInstance();
        IPostAPI iPostAPI1 = retrofit1.create(IPostAPI.class);

        level = findViewById(R.id.tv_quorabay_userPorfile_setLevel);

//        Call<String> levelApiCall = iPostAPI1.findRanking(userId);
//        levelApiCall.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.body() != null){
//                    Log.d("level", "onResponse: " + response.body());
//                    level.setText(response.body());
//                }else{
//                    level.setText("beginner");
//                }
//            }
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.e("LevelFail", "onFailure: " + t.getMessage() );
//            }
//        });
         Call<ResponseMessage> apiCall = iPostAPI1.findRanking(userId);
         apiCall.enqueue(new Callback<ResponseMessage>() {
             @Override
             public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                 Log.d("level", "onResponse: " + response.body().getMessage());
                 level.setText(response.body().getMessage());
             }

             @Override
             public void onFailure(Call<ResponseMessage> call, Throwable t) {
                 Log.d("LevelFail", "onFailure: " + t.getMessage());
             }
         });
//        firebaseStorage = FirebaseStorage.getInstance();
//        storageReference = firebaseStorage.getReference();
//        profileImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                choosePicture();
//            }
//        });
    }

//    private void choosePicture() {
//        Intent intent = new Intent();
//        intent.setType("image/**");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, 1);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            imageUri = data.getData();
//            profileImage.setImageURI(imageUri);
//            uploadPicture();
//        }
//
//    }
//
//    private void uploadPicture() {
//
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("Uploading Image....");
//
//        final String randomKey = UUID.randomUUID().toString();
//        StorageReference riversRef = storageReference.child("images/randomKey");
//
//        riversRef.putFile(imageUri)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl();
//                        downloadUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                User userProfile = new User();
//                                userProfile.setProfileImage(uri.toString());
//                                Call<ResponseBody> updateCall = iPostAPI.updateProfilePicture(userId, userProfile);
//                                updateCall.enqueue(new Callback<ResponseBody>() {
//                                    @Override
//                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                                    }
//                                });
//                            }
//                        });
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(), "Failed to Upload", Toast.LENGTH_LONG).show();
//                    }
//                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                double progress = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                progressDialog.setMessage("Progress" + (int) progress + "%");
//            }
//        });
//    }
}