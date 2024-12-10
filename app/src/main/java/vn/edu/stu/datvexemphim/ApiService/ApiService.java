package vn.edu.stu.datvexemphim.ApiService;

import java.util.Date;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.edu.stu.datvexemphim.DTO.Response.ApiResponse;
import vn.edu.stu.datvexemphim.DTO.Response.MovieResponse;
import vn.edu.stu.datvexemphim.DTO.Response.UserResponse;
import vn.edu.stu.datvexemphim.Models.User;

public interface ApiService {
    @GET("/movies/{id}")
    // Thay {id} bằng ID phim
    Call<ApiResponse<MovieResponse>> getMovie(@Path("id") int movieId);

    @GET("/movies")
        // Thay {id} bằng ID phim
    Call<ApiResponse<List<MovieResponse>>> getAllMovie();

    @Multipart
    @POST("/movies")
    Call<ApiResponse<MovieResponse>> addMovies(
            @Query("movieName") String movieName,
            @Query("movieDes") String movieDes,
            @Query("movieGenres") String movieGenres,
            @Query("movieRelease") String movieRelease,
            @Query("movieLength") int movieLength,
            @Part MultipartBody.Part moviePoster
    );

    @DELETE("/movies/{id}")
    Call<ApiResponse<String>> deleteMovie(@Path("id") int movieId);

    @Multipart
    @PUT("/movies/{id}")
    Call<ApiResponse<MovieResponse>> updateMovie(
            @Path("id") int id,
            @Query("movieName") String movieName,
            @Query("movieDes") String movieDes,
            @Query("movieGenres") String movieGenres,
            @Query("movieRelease") String movieRelease,
            @Query("movieLength") int movieLength,
            @Part MultipartBody.Part moviePoster
    );

    @GET("/users")
    Call<ApiResponse<List<UserResponse>>> getAllUsers();

    @POST("/users")
    Call<ApiResponse<List<UserResponse>>> addUsers();

    @PUT("/users/{id}")
    Call<ApiResponse<UserResponse>> updateUsers(
            @Path("id") int id,
            @Query("fullName") String fullName,
            @Query("birthday") String birthday,
            @Query("gender") int gender,
            @Query("city") String city,
            @Query("phoneNumber") String phoneNumber
    );
    @DELETE("/users/{id}")
    Call<ApiResponse<String>> deleteUser(@Path("id") int user_id);
}
