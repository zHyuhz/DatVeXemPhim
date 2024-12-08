package vn.edu.stu.datvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import vn.edu.stu.datvexemphim.R;

public class capNhatNguoiDung_MainActivity extends AppCompatActivity {
    ImageView img_troLai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frm_capnhatnguoidung);

        addControls();
        addEvents();
    }

    private void addControls() {
        img_troLai = findViewById(R.id.img_capNhatTaiKhoan_troLai);
    }

    private void addEvents() {
        img_troLai.setOnClickListener(v ->{
            Intent intent = new Intent(this, dsNguoiDung_MainActivity.class);
            startActivity(intent);
        });
    }
}