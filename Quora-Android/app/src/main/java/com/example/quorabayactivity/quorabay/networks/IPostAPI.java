package com.example.quorabayactivity.quorabay.networks;

import com.example.quorabayactivity.quorabay.dto.CommentData;
import com.example.quorabayactivity.quorabay.dto.DataanalyticsPost;
import com.example.quorabayactivity.quorabay.dto.PostAnswer;
import com.example.quorabayactivity.quorabay.dto.PostComment;
import com.example.quorabayactivity.quorabay.dto.PostReaction;
import com.example.quorabayactivity.quorabay.models.Answers;
import com.example.quorabayactivity.quorabay.models.Comments;
import com.example.quorabayactivity.quorabay.models.Questions;
import com.example.quorabayactivity.quorabay.models.Reaction;
import com.example.quorabayactivity.quorabay.models.User;
import com.example.quorabayactivity.quorabay.models.UserSearch;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IPostAPI {

    // List of All APIS

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

    @GET("search/custom/{text}")
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

//    @GET("quora/profile/{userId}")
//    Call<List<>>
//=================================================================================================


}
