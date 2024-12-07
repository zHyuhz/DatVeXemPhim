package vn.edu.stu.datvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.edu.stu.datvexemphim.Models.Booking;
import vn.edu.stu.datvexemphim.R;
import vn.edu.stu.datvexemphim.ViewMatch.BookingAdapter;

public class dsVe_MainActivity extends AppCompatActivity {
    List<Booking> bookingList;
    ListView listView;
    BookingAdapter adapter;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.frm_danhsach_ve);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addControls();
        addEvents();
    }

    private void addControls() {
        listView = findViewById(R.id.lv_ve);
        bookingList = new ArrayList<>();
        imageView = findViewById(R.id.img_dsVe_troLai);


        for (int i = 1; i <= 15; i++) {
            Calendar date = Calendar.getInstance();
            date.set(2024, Calendar.DECEMBER, i, 0, 0, 0); // Ngày từ 1/12 đến 15/12/2024

            Calendar hour = Calendar.getInstance();
            hour.set(Calendar.HOUR_OF_DAY, 10 + (i % 5)); // Giờ từ 10 đến 14
            hour.set(Calendar.MINUTE, i * 3 % 60); // Phút từ 0 đến 59
            hour.set(Calendar.SECOND, 0);

            double price = 100000 + i * 5000; // Giá từ 105,000 đến 175,000

            Booking booking = new Booking(i, date, hour, price);
            bookingList.add(booking);
        }

        adapter = new BookingAdapter(this,R.layout.item_ve,bookingList);
        listView.setAdapter(adapter);


    }

    private void addEvents() {
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(this, trangChu_MainActivity.class);
            startActivity(intent);
        });
    }
}