package vn.edu.stu.datvexemphim.ApiService;

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
import vn.edu.stu.datvexemphim.DTO.Request.LoginRequest;
import vn.edu.stu.datvexemphim.DTO.Response.AccountResponse;
import vn.edu.stu.datvexemphim.DTO.Response.ApiResponse;
import vn.edu.stu.datvexemphim.DTO.Response.MovieResponse;
import vn.edu.stu.datvexemphim.DTO.Response.ScheduleDateTimeResponse;
import vn.edu.stu.datvexemphim.DTO.Response.ScheduleResponse;
import vn.edu.stu.datvexemphim.DTO.Response.SeatsResponse;

public interface ApiService {
    //    @POST("/accounts/register")
//    Call<ApiResponse<AccountResponse>> register(@Body RegisterAccountRequest registerRequest);
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

    @POST("/auth/login")
    Call<ApiResponse<Void>> login(@Body LoginRequest loginRequest);

    @GET("/accounts/{username}")
    Call<ApiResponse<AccountResponse>> findAccountByUsername(@Path("username") String username);

    @GET("/schedules/{movieID}")
    Call<ApiResponse<List<ScheduleDateTimeResponse>>> findScheduleDateTimeResponse(@Path("movieID") int movieID);

    @GET("/schedules/search")
    Call<ApiResponse<ScheduleResponse>> findSchedules(
            @Query("scheduleDate") String scheduleDate,  // Chuyển LocalDate thành String
            @Query("scheduleStart") String scheduleStart, // Chuyển LocalTime thành String
            @Query("movieId") int movieId
    );

    @GET("/seats/{movieID}")
    Call<ApiResponse<List<SeatsResponse>>> getSeatByMovie(@Path("movieID") int movieID);

    @GET("/seats/search")
    Call<ApiResponse<SeatsResponse>> getSearch(@Query("seatRow") char seatRow, @Query("seatNumber") int seatNumber, @Query("roomId") int roomId);
}

