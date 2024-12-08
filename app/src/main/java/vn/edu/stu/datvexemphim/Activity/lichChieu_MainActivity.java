package vn.edu.stu.datvexemphim.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.stu.datvexemphim.ApiService.ApiService;
import vn.edu.stu.datvexemphim.CustomEvent.IDatePicker;
import vn.edu.stu.datvexemphim.DTO.Response.ApiResponse;
import vn.edu.stu.datvexemphim.DTO.Response.ScheduleDateTimeResponse;
import vn.edu.stu.datvexemphim.DTO.Response.ScheduleResponse;
import vn.edu.stu.datvexemphim.Models.Schedule;
import vn.edu.stu.datvexemphim.Models.TimeSlot;
import vn.edu.stu.datvexemphim.R;
import vn.edu.stu.datvexemphim.Retrofit.RetrofitSer;
import vn.edu.stu.datvexemphim.ViewMatch.DatePickerAdapter;
import vn.edu.stu.datvexemphim.ViewMatch.DatePickerAdapter2;
import vn.edu.stu.datvexemphim.ViewMatch.TimePickerAdapter;

public class lichChieu_MainActivity extends AppCompatActivity implements IDatePicker {
    TextView tvNgay, tvThang;
    RecyclerView recyclerDate, recyclerTime;
    Button btn_tiepTuc;
    ImageView btn_troLai;
    List<ScheduleDateTimeResponse> scheduleDateTimeResponses = new ArrayList<>();
    List<Schedule> scheduleList = new ArrayList<>();
    DatePickerAdapter2 adapter;
    TimePickerAdapter adapter2;
    ScheduleResponse scheduleSelected = new ScheduleResponse();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frm_lichchieu);

        addControls();
        addEvents();

    }

    private void addControls() {
        tvNgay = findViewById(R.id.tvDay);
        tvThang = findViewById(R.id.tvMonth);
        btn_tiepTuc = findViewById(R.id.frmLichChieu_btn_tiepTuc);
        btn_troLai = findViewById(R.id.img_LichChieu_troLai);
        getScheduleMovie();
        hienthi();
    }

    private void addEvents() {
//        hienThiNgay();
//        hienThiGio();

        btn_tiepTuc.setOnClickListener(v -> {
//            Intent intent = new Intent(lichChieu_MainActivity.this, chonChoNgoi_MainActivity.class);
//            startActivity(intent);
        });

        btn_troLai.setOnClickListener(v -> {
            finish();
        });
    }

    private void getScheduleMovie() {
        Intent intent = getIntent();
        int movieId = intent.getIntExtra("MovieID", -1);
        if (movieId != -1) {

            ApiService apiService = RetrofitSer.getRetrofitInstance().create(ApiService.class);
            Call<ApiResponse<List<ScheduleDateTimeResponse>>> call = apiService.findScheduleDateTimeResponse(movieId);
            call.enqueue(new Callback<ApiResponse<List<ScheduleDateTimeResponse>>>() {
                @Override
                public void onResponse(Call<ApiResponse<List<ScheduleDateTimeResponse>>> call, Response<ApiResponse<List<ScheduleDateTimeResponse>>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        scheduleDateTimeResponses.clear(); // Xóa dữ liệu cũ
                        scheduleDateTimeResponses.addAll(response.body().getResult()); // Thêm dữ liệu mới
                        hienthi(); // Nếu adapter chưa được khởi tạo, gọi hienthi()
                        Toast.makeText(lichChieu_MainActivity.this, String.valueOf(scheduleDateTimeResponses.size()), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<List<ScheduleDateTimeResponse>>> call, Throwable t) {

                }
            });
        }
    }

    public void hienthi() {

        recyclerDate = findViewById(R.id.RCV_date);
        recyclerTime = findViewById(R.id.RCV_hour);
        recyclerTime.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerDate.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));


        Set<String> uniqueDates = new HashSet<>();
        List<ScheduleDateTimeResponse> filteredList = new ArrayList<>();

        for (ScheduleDateTimeResponse schedule : scheduleDateTimeResponses) {
            if (uniqueDates.add(schedule.getScheduleDate())) {
                filteredList.add(schedule);
            }
        }


        adapter = new DatePickerAdapter2(filteredList, date -> {
            setTimeOfDay(date);
        });

        recyclerDate.setAdapter(adapter);
    }

    private void setTimeOfDay(ScheduleDateTimeResponse response) {
        List<String> listTime = new ArrayList<>();
        for (ScheduleDateTimeResponse timeResponse : scheduleDateTimeResponses) {
            if ((timeResponse.getScheduleDate().equals(response.getScheduleDate()))) {
                listTime.add(timeResponse.getScheduleStart());
            }
        }
        adapter2 = new TimePickerAdapter(listTime, time -> {
            getDateTime(response.getScheduleDate(), time, response.getMovies().getMovieId());
        });

        recyclerTime.setAdapter(adapter2);
    }

    private void getDateTime(String date, String time, int movieId) {

        ApiService apiService = RetrofitSer.getRetrofitInstance().create(ApiService.class);
        Call<ApiResponse<ScheduleResponse>> call = apiService.findSchedules(date, time, movieId);
        call.enqueue(new Callback<ApiResponse<ScheduleResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<ScheduleResponse>> call, Response<ApiResponse<ScheduleResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == 0) {
                        scheduleSelected = response.body().getResult();
                    }
                }

            }

            @Override
            public void onFailure(Call<ApiResponse<ScheduleResponse>> call, Throwable t) {
                Log.e("API",t.getMessage());
            }
        });

    }


    public List<TimeSlot> getSampleData() {
        List<TimeSlot> timeList = new ArrayList<>();
        timeList.add(new TimeSlot(1, "09", "30"));
        timeList.add(new TimeSlot(2, "10", "00"));
        timeList.add(new TimeSlot(3, "12", "45"));
        timeList.add(new TimeSlot(4, "14", "15"));
        timeList.add(new TimeSlot(5, "16", "00"));
        return timeList;
    }

    private void hienThiNgay() {
        recyclerDate = findViewById(R.id.RCV_date);
        recyclerDate.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        List<Calendar> dateList = getDates();
        DatePickerAdapter adapter = new DatePickerAdapter(dateList, date -> {
            Toast.makeText(lichChieu_MainActivity.this,
                    "Ngày được chọn: " + date.getTime(),
                    Toast.LENGTH_SHORT).show();
        });

        recyclerDate.setAdapter(adapter);
    }


    private List<Calendar> getDates() {
        List<Calendar> dateList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 30; i++) {
            Calendar date = (Calendar) calendar.clone();
            date.add(Calendar.DAY_OF_YEAR, i);
            dateList.add(date);
        }

        return dateList;
    }

    @Override
    public void onDateClick(Calendar date) {

    }
}