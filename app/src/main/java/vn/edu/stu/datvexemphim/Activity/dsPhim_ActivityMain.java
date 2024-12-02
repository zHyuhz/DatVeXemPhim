package vn.edu.stu.datvexemphim.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.stu.datvexemphim.ApiService.ApiService;
import vn.edu.stu.datvexemphim.DTO.Response.ApiResponse;
import vn.edu.stu.datvexemphim.DTO.Response.MovieResponse;
import vn.edu.stu.datvexemphim.R;
import vn.edu.stu.datvexemphim.Retrofit.RetrofitSer;
import vn.edu.stu.datvexemphim.ViewMatch.FilmAdapter;
import vn.edu.stu.datvexemphim.ViewMatch.MoviesAdapter;
import vn.edu.stu.datvexemphim.ViewMatch.ScaleCenterLinearLayoutManager;

public class dsPhim_ActivityMain extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btn_add;
    List<MovieResponse> movieResponseList = new ArrayList<>();
    ScaleCenterLinearLayoutManager layoutManagerPhimDangChieu, layoutManagerPhimSapChieu;
    FilmAdapter filmAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frm_danhsach_phim);

        addControls();
        addEvents();
    }

    private void addControls() {
        recyclerView = findViewById(R.id.frmDanhSach_RCV_dsPhim);
        btn_add =findViewById(R.id.frmDanhSach_btn_add);
        khoiTaoPhim();


    }

    private void addEvents() {
    }
    private void layTatCaPhim() {
        List<MovieResponse> movieResponses = new ArrayList<>();
        ApiService apiService = RetrofitSer.getRetrofitInstance().create(ApiService.class);
        Call<ApiResponse<List<MovieResponse>>> call = apiService.getAllMovie();
        call.enqueue(new Callback<ApiResponse<List<MovieResponse>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<MovieResponse>>> call, Response<ApiResponse<List<MovieResponse>>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ApiResponse<List<MovieResponse>> apiResponse = response.body();
                        if (apiResponse.getCode() == 0) {
                            filmAdapter.movieList.clear();
                            movieResponseList.addAll(apiResponse.getResult());
                            filmAdapter.movieList.addAll(apiResponse.getResult());
                            filmAdapter.notifyDataSetChanged();
                            Toast.makeText(dsPhim_ActivityMain.this, "Thanh cong 1", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(dsPhim_ActivityMain.this, "That bai 1", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(dsPhim_ActivityMain.this, "That bai 2: Do body null", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        // Đọc nội dung lỗi từ response.errorBody()
                        String errorBody = response.errorBody() != null ? response.errorBody().string() : "Unknown error";
                        Gson gson = new Gson();
                        JsonObject errorJson = gson.fromJson(errorBody, JsonObject.class);

                        // Lấy giá trị của "message"
                        String errorMessage = errorJson.has("message") ? errorJson.get("message").getAsString() : "Unknown error";
                        // Bạn có thể xử lý chuỗi lỗi ở đây và hiển thị thông báo lỗi
                        Toast.makeText(dsPhim_ActivityMain.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(dsPhim_ActivityMain.this, "Error parsing error body", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<MovieResponse>>> call, Throwable t) {
                Log.e("API Error", "Lỗi: " + t.getMessage());
            }
        });
    }
    private void khoiTaoPhim() {
        filmAdapter = new FilmAdapter(this, R.layout.item_movie, new ArrayList<>());

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(filmAdapter);
        layTatCaPhim();
    }
}