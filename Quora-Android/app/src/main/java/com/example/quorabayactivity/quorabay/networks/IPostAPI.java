package com.example.quorabayactivity.quorabay.networks;

import com.example.quorabayactivity.quorabay.dto.CommentData;
import com.example.quorabayactivity.quorabay.dto.DataanalyticsPost;
import com.example.quorabayactivity.quorabay.dto.PostAnswer;
import com.example.quorabayactivity.quorabay.dto.PostComment;
import com.example.quorabayactivity.quorabay.dto.PostReaction;
import com.example.quorabayactivity.quorabay.models.Answers;
import com.example.quorabayactivity.quorabay.models.Comments;
import com.example.quorabayactivity.quorabay.models.CorporateDeatails;
import com.example.quorabayactivity.quorabay.models.FollowRequest;
import com.example.quorabayactivity.quorabay.models.LoginSendCommonDTO;
import com.example.quorabayactivity.quorabay.models.ModeratorDetails;
import com.example.quorabayactivity.quorabay.models.QuestionRequest;
import com.example.quorabayactivity.quorabay.models.Questions;
import com.example.quorabayactivity.quorabay.models.Reaction;
import com.example.quorabayactivity.quorabay.models.User;
import com.example.quorabayactivity.quorabay.models.UserDetails;
import com.example.quorabayactivity.quorabay.models.UserProfileData;
import com.example.quorabayactivity.quorabay.models.UserRegisterEntity;
import com.example.quorabayactivity.quorabay.models.UserRegisterResponse;
import com.example.quorabayactivity.quorabay.models.UserSearch;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IPostAPI {

    // List of All APIS


// For Login / Register ====================================================================================

    @POST("/user/authenticate")
    Call<UserRegisterResponse> loginUser(@Body LoginSendCommonDTO loginSendCommonDTO);

    @POST("/user/save")
    Call<UserRegisterResponse> register(@Body UserRegisterEntity userRegisterEntity);

    @POST("/user/append/notification/{userId}")
    Call<ResponseBody> appendNotification(@Path("userId") String userId, @Body String notificationToken);

// For Question ====================================================================================

        @GET("quora/question/findall")
        Call<List<Questions>> getQuestions();

        @POST("quora/question/")
        Call<ResponseBody> createQuestion(@Body Questions questions);

        @GET("quora/question/{questionId}")
        Call<Questions> getQuestionById(@Path("questionId") String questionId);

        @GET("quora/question/user/{userId}")
        Call<List<Questions>> getQuestionByUserId (@Path("userId") String userId);

        @GET("quora/question/page/{corporateId}")
        Call<List<Questions>> getQuestionByCorporateId (@Path("corporateId") String corporateId);

        @GET("quora/question/count/{userId}")
        Call<Integer> getQuestionCountByUserId (@Path("userId") String userId);

        @GET("quora/question/category/{categoryId}")
        Call<List<Questions>> getQuestionByCategoryId(@Path("categoryId") String categoryId);

        @GET("quora/question/categoryname/{categoryId}")
        Call<String> getCategoryNameByCategoryId(@Path("categoryId") String categoryId);
 //=================================================================================================

// For Answer ====================================================================================

    @POST ("quora/answer/")
    Call<ResponseBody> createAnswer(@Body PostAnswer postAnswer);

    @GET ("quora/answer/{answerId}")
    Call<Answers> getAnswerById (@Path("answerId") String answerId);

    @POST ("quora/answer/react")
    Call<ResponseBody> postLike (@Body PostReaction postReaction);

    @GET ("quora/answer/reactions/{answerId}")
    Call<List<Reaction>> getReactionsOnAnswers (@Path("answerId") String answerId);

    @PUT ("quora/answer/accept/{answerId}")
    Call<ResponseBody> acceptAnswers (@Path("answerId") String answerId);

    @GET("quora/answer/countlike/{answerId}")
    Call<Integer> getLikeCountOnAnswers (@Path("answerId") String answerId);

    @GET("quora/answer/countdisLike/{answerId}")
    Call<Integer> getdisLikeCountOnAnswers (@Path("answerId") String answerId);

    @GET("quora/answer/commentcount/{answerId}")
    Call<Integer>  getDCommentCountOnAnswers(@Path("answerId") String answerId);

    @GET("quora/answer/question/{questionId}")
    Call<List<Answers>> getAnswersByQuestionId(@Path("questionId") String questionId);
//=================================================================================================

// For Comment ====================================================================================

    @POST ("quora/comment/")
    Call<ResponseBody> createComment (@Body PostComment postComment);

    @GET ("quora/comment/comments/{answerId}")
    Call<List<CommentData>> getCommentByAnswerId (@Path("answerId") String answerId);

//    @GET ("comment/answer/{answerId}")
//    Call<>

    @GET ("quora/comment/user/{userId}")
     Call<User> getUsersByCommentId(@Path("userId") String userId);

    @GET ("quora/comment/comments/{answerId}")
    Call<Comments> getCommentsOnAnswer (@Path("answerId") String answerId);

    
//=================================================================================================

// For Search ==========================================================================================

    @GET("quora/search/custom/{text}")
    Call<List<UserSearch>> getAll(@Path("text") String text);

//=================================================================================================

//For Ranking =================================================================================================



//=================================================================================================

// For Like / Dislike=================================================================================================
    @POST("quora/answer/react")
    Call<ResponseBody> reactOnLikeDislike (@Body PostReaction postReaction);

    @GET("quora/answer/countlike/{answerId}")
    Call<Integer> countLikeByAnswerId(@Path("answerId") String answerId);

    @GET("quora/answer/countdislike/{answerId}")
    Call<Integer> countDislikeByAnswerId(@Path("answerId") String answerId);
//=================================================================================================
// For DataAnalytics=================================================================================================

    @POST ("analytics")
    Call<ResponseBody> postToAnalytics(@Body DataanalyticsPost dataanalyticsPost);
//=================================================================================================

// For Followers =================================================================================================

      @GET("quora/follower/{userId}")
      Call<List<UserDetails>> getFollowersByUserId (@Path("userId") String userId);

      @GET("quora/profile/pending/{userId}")
      Call<List<UserProfileData>> getFollowRequestByUserId(@Path("userId") String userId);

      @POST("quora/profile/approve")
      Call<FollowRequest> approveFollowRequest(@Body FollowRequest followRequest);

      @POST("quora/follower/addfollower")
      Call<FollowRequest> addFollower(@Body FollowRequest followRequest);

      @POST("quora/follower/checkfollowing")
      Call<Boolean> checkFollowing(@Body FollowRequest followRequest);
//=================================================================================================

// For DataAnalytics=================================================================================================


//=================================================================================================

// For Moderator=================================================================================================

    @GET("quora/moderator/questionrequest/{corporateId}")
    Call<List<QuestionRequest>> getApprovePostRequestById(@Path("corporateId") String corporateId);

    @POST("quora/moderator/questionrequest/accept/{requestId}")
    Call<Questions> getApprovePostAccept(@Path("requestId") String requestId);

    @POST("quora/moderator/questionrequest/reject/{requestId}")
    Call<Void> getApprovePostdecline(@Path("requestId") String requestId);

    // Page

    @GET("quora/moderator/getcorporatedetails/{moderatorId}")
    Call<List<CorporateDeatails>> getPagesOfModerator(@Path("moderatorId") String moderatorId);

    @POST("quora/moderator/add")
    Call<ModeratorDetails> addmoderator (@Body ModeratorDetails moderatorDetails);

//=================================================================================================

// For Owner=================================================================================================

    @GET("quora/moderator/getuserdetails/{ownerId}")
    Call<List<UserDetails>> findModeratorListByownerId(@Path("ownerId") String ownerId);

    @DELETE("quora/moderator/remove")
    Call<ResponseBody> delelteModerator(ModeratorDetails moderatorDetails);
//=================================================================================================

}
