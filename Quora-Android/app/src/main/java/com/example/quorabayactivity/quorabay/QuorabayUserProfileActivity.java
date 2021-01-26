package com.example.quorabayactivity.quorabay;

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
import com.example.quorabayactivity.quorabay.builders.RetrofitFollower;
import com.example.quorabayactivity.quorabay.models.FollowRequest;
import com.example.quorabayactivity.quorabay.models.UserSearch;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.quorabayactivity.R.id.quorabay_userProfile_profileImage;

public class QuorabayUserProfileActivity extends AppCompatActivity {

    ImageView profileImage;
    TextView userName, email;
    Button follow;
//    private Uri imageUri;
//    private FirebaseStorage firebaseStorage;
//    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_user_profile);

        Retrofit retrofit = RetrofitFollower.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

        String user = (String) getIntent().getSerializableExtra("UserSearch");
        Gson gson = new Gson();

        String userId = "u5";
        UserSearch userSearch = gson.fromJson(user, UserSearch.class);
        profileImage = findViewById(quorabay_userProfile_profileImage);
        Glide.with(this)
                .load(userSearch.getImageUrl())
                .placeholder(R.drawable.quorabay_profile_image)
                .into(profileImage);


        userName = findViewById(R.id.quorabay_userPorfile_userName);
        userName.setText(userSearch.getUserName());

        FollowRequest followRequest = new FollowRequest();
        followRequest.setUserId(userId);
        followRequest.setFollowerId(userSearch.getUserId());
        follow = findViewById(R.id.btn_quorabay_user_profile_follow);
        Call<Boolean> followRequestCall = iPostAPI.checkFollowing(followRequest);
        followRequestCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.body() != null){
                    if (response.body()){
                        follow.setEnabled(false);
                    }else{
                        follow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Call<FollowRequest> followRequestCall = iPostAPI.addFollower(followRequest);
                                followRequestCall.enqueue(new Callback<FollowRequest>() {
                                    @Override
                                    public void onResponse(Call<FollowRequest> call, Response<FollowRequest> response) {
                                        Toast.makeText(QuorabayUserProfileActivity.this, "Follow Request", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<FollowRequest> call, Throwable t) {
                                        Log.d("fail", "onFailure: " + t);
                                    }
                                });
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("fail", "onFailure: " + t );
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