package vn.edu.stu.datvexemphim.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import vn.edu.stu.datvexemphim.CustomEvent.IDatePicker;
import vn.edu.stu.datvexemphim.Models.Schedule;
import vn.edu.stu.datvexemphim.Models.TimeSlot;
import vn.edu.stu.datvexemphim.R;
import vn.edu.stu.datvexemphim.ViewMatch.DatePickerAdapter;
import vn.edu.stu.datvexemphim.ViewMatch.DatePickerAdapter2;
import vn.edu.stu.datvexemphim.ViewMatch.TimePickerAdapter;

public class lichChieu_MainActivity extends AppCompatActivity implements IDatePicker {
    TextView tvNgay,tvThang;
    RecyclerView recyclerDate,recyclerTime;
    Button btn_tiepTuc, btn_troLai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frm_lichchieu);

        addControls();
        addEvents();
    }

    private void addControls() {
        tvNgay = findViewById(R.id.tvDay);
        tvThang=findViewById(R.id.tvMonth);
        btn_tiepTuc = findViewById(R.id.frmLichChieu_btn_tiepTuc);
        btn_troLai = findViewById(R.id.img_LichChieu_troLai);
    }

    private void addEvents() {
//        hienThiNgay();
//        hienThiGio();

        btn_tiepTuc.setOnClickListener(v ->{
            Intent intent = new Intent(this, chonChoNgoi_MainActivity.class);
            startActivity(intent);
        });
        hienthi();
        btn_troLai.setOnClickListener(v ->{
            Intent intent = new Intent(this, chiTietPhim_MainActivity.class);
            startActivity(intent);
        });
    }


//    private void hienThiGio(){
//        recyclerTime = findViewById(R.id.RCV_hour);
//        recyclerTime.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
//        List<TimeSlot> timeSlots = getSampleData();
//
//        TimePickerAdapter adapter = new TimePickerAdapter(timeSlots, (view, positon) ->{
//            TimeSlot selectedTime = timeSlots.get(positon);
//            Toast.makeText(lichChieu_MainActivity.this,
//                    "Giờ được chọn: " + selectedTime.toString(),
//                    Toast.LENGTH_SHORT).show();
//        });
//        recyclerTime.setAdapter(adapter);
//    }
    public void hienthi() {
        List<Schedule> scheduleList = new ArrayList<>();
        // Tạo lịch chiếu 1
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2023, Calendar.DECEMBER, 5);  // 5 tháng 12 năm 2023
        Schedule schedule1 = new Schedule(1, 101, 1, calendar1, "10:00");
        scheduleList.add(schedule1);

        // Tạo lịch chiếu 2
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2023, Calendar.DECEMBER, 5);  // 5 tháng 12 năm 2023
        Schedule schedule2 = new Schedule(2, 101, 2, calendar2, "12:30");
        scheduleList.add(schedule2);

        // Tạo lịch chiếu 3
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2023, Calendar.DECEMBER, 6);  // 6 tháng 12 năm 2023
        Schedule schedule3 = new Schedule(3, 101, 1, calendar3, "03:00");
        scheduleList.add(schedule3);


        // Tạo lịch chiếu 4
        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(2023, Calendar.DECEMBER, 7);  // 7 tháng 12 năm 2023
        Schedule schedule4 = new Schedule(4, 104, 3, calendar4, "06:00");
        scheduleList.add(schedule4);
        List<String> strings = new ArrayList<>();
        List<Schedule> list = new ArrayList<>();
        for (Schedule schedule : scheduleList){
            if (schedule.getMovie_id() == 101){
                list.add(schedule);
                strings.add(schedule.getStartTime());
            }
        }


        recyclerDate = findViewById(R.id.RCV_date);
        recyclerTime = findViewById(R.id.RCV_hour);
        recyclerTime.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        recyclerDate.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

        TimePickerAdapter adapter2 = new TimePickerAdapter(strings, time ->{
            Toast.makeText(lichChieu_MainActivity.this,
                    "Giờ được chọn: " + time,
                    Toast.LENGTH_SHORT).show();
        });
        DatePickerAdapter2 adapter = new DatePickerAdapter2(list, date -> {
            Toast.makeText(lichChieu_MainActivity.this,
                    "Ngày được chọn: " + date,
                    Toast.LENGTH_SHORT).show();
        });
        recyclerTime.setAdapter(adapter2);
        recyclerDate.setAdapter(adapter);
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

    private void hienThiNgay(){
        recyclerDate = findViewById(R.id.RCV_date);
        recyclerDate.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
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