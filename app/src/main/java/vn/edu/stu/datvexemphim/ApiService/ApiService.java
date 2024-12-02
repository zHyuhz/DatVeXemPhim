package vn.edu.stu.datvexemphim.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import vn.edu.stu.datvexemphim.DTO.Response.ApiResponse;
import vn.edu.stu.datvexemphim.DTO.Response.MovieResponse;

public interface ApiService {
//    @POST("/accounts/register")
//    Call<ApiResponse<AccountResponse>> register(@Body RegisterAccountRequest registerRequest);
    @GET("/movies/{id}") // Thay {id} bằng ID phim
    Call<ApiResponse<MovieResponse>> getMovie(@Path("id") int movieId);
    @GET("/movies") // Thay {id} bằng ID phim
    Call<ApiResponse<List<MovieResponse>>> getAllMovie();
}
