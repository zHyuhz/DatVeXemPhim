package vn.edu.stu.datvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.stu.datvexemphim.R;

public class dsTaiKhoan_MainActivity extends AppCompatActivity {
    Button btn_troLai;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frm_danhsach_taikhoan);

        addControls();
        addEvents();
    }
    private void addControls(){
        btn_troLai = findViewById(R.id.img_dsTaiKhoan_troLai);
    }
    private void addEvents(){
        btn_troLai.setOnClickListener(v -> {
            Intent intent = new Intent(this, trangChu_MainActivity.class);
            startActivity(intent);
        });
    }
}