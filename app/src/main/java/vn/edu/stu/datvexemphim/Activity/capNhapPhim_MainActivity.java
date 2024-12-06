package vn.edu.stu.datvexemphim.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import vn.edu.stu.datvexemphim.R;

public class capNhapPhim_MainActivity extends AppCompatActivity {
    ImageButton imageButton;
    TextView tv_valueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frm_update_movies);

        addControls();
        addEvents();
    }

    private void addControls() {
        imageButton = findViewById(R.id.frmUpdate_btn_calendar);
        tv_valueDate = findViewById(R.id.frmUpdate_tv_ngay);

    }

    private void addEvents() {
        xulyImageButton();
    }

    private void xulyImageButton() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy ngày hiện tại làm ngày mặc định
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Hiển thị DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        capNhapPhim_MainActivity.this, // Activity hiện tại
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                                // Xử lý ngày được chọn
                                String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                                tv_valueDate.setText(selectedDate);
                                Toast.makeText(capNhapPhim_MainActivity.this, "Ngày đã chọn: " + selectedDate, Toast.LENGTH_SHORT).show();
                            }
                        },
                        year, // Năm mặc định
                        month, // Tháng mặc định
                        day // Ngày mặc định
                );
                datePickerDialog.show();
            }
        });
    }
}