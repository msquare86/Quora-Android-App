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
import com.example.quorabayactivity.quorabay.builders.RetrofitBuilderCommon;
import com.example.quorabayactivity.quorabay.builders.RetrofitFollower;
import com.example.quorabayactivity.quorabay.dto.ResponseMessage;
import com.example.quorabayactivity.quorabay.models.User;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import java.util.List;

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
    String interest;
    //List<String> areaofInterest;

//    private Uri imageUri;
//    private FirebaseStorage firebaseStorage;
//    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_user_profile);

        Retrofit retrofit = RetrofitBuilder.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

//        String userId = getIntent().getStringExtra("QuorabayUserId");
//        String UserName = getIntent().getStringExtra("QuorabayUserName");
//        String userImage = getIntent().getStringExtra("QuorabayUserImage");

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        String userId = sharedPreferences.getString("QuorabayUserId" , "quorabayUserId");
        String UserName = sharedPreferences.getString("QuorabayUserName" , "quorabayUserId");
        String userImage = sharedPreferences.getString("QuorabayUserImage" , "quorabayUserId");

        Retrofit retrofit2 = RetrofitBuilderCommon.getInstance();
        IPostAPI iPostAPI2 = retrofit2.create(IPostAPI.class);
        Call<User> userCall = iPostAPI2.getUser(2 , userId);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null){
                    interest = response.body().getAreaOfInterests();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(QuorabayUserProfileActivity.this, "Fail Get User", Toast.LENGTH_SHORT).show();
            }
        });

//        List<String> areaofInterest = Arrays.asList(interest.split(","));
//        PrintAreaofInterest(areaofInterest);

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

    private void PrintAreaofInterest(List<String> areaofInterest) {
        if (areaofInterest.size() == 0){

        }else if (areaofInterest.size() == 1){
            TextView textView = findViewById(R.id.tv_quorabay_user_profile_1);
            textView.setText(areaofInterest.get(1));
        }
        else if (areaofInterest.size() == 2){
            TextView textView = findViewById(R.id.tv_quorabay_user_profile_1);
            textView.setText(areaofInterest.get(1));

            TextView textView1 = findViewById(R.id.tv_quorabay_user_profile_1);
            textView1.setText(areaofInterest.get(2));
        }
        else if (areaofInterest.size() == 3){
            TextView textView = findViewById(R.id.tv_quorabay_user_profile_1);
            textView.setText(areaofInterest.get(1));

            TextView textView1 = findViewById(R.id.tv_quorabay_user_profile_1);
            textView1.setText(areaofInterest.get(2));

            TextView textView2 = findViewById(R.id.tv_quorabay_user_profile_1);
            textView2.setText(areaofInterest.get(3));
        }
        else if (areaofInterest.size() == 4){
            TextView textView = findViewById(R.id.tv_quorabay_user_profile_1);
            textView.setText(areaofInterest.get(1));

            TextView textView1 = findViewById(R.id.tv_quorabay_user_profile_2);
            textView1.setText(areaofInterest.get(1));

            TextView textView2 = findViewById(R.id.tv_quorabay_user_profile_3);
            textView2.setText(areaofInterest.get(2));

            TextView textView3 = findViewById(R.id.tv_quorabay_user_profile_4);
            textView3.setText(areaofInterest.get(3));


        }else if (areaofInterest.size() == 5){
            TextView textView = findViewById(R.id.tv_quorabay_user_profile_1);
            textView.setText(areaofInterest.get(1));

            TextView textView1 = findViewById(R.id.tv_quorabay_user_profile_2);
            textView1.setText(areaofInterest.get(1));

            TextView textView2 = findViewById(R.id.tv_quorabay_user_profile_3);
            textView2.setText(areaofInterest.get(2));

            TextView textView3 = findViewById(R.id.tv_quorabay_user_profile_4);
            textView3.setText(areaofInterest.get(3));

            TextView textView4 = findViewById(R.id.tv_quorabay_user_profile_5);
            textView4.setText(areaofInterest.get(4));


        }
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