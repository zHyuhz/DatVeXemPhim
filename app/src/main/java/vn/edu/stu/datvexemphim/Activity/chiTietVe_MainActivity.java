package vn.edu.stu.datvexemphim.Activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import vn.edu.stu.datvexemphim.R;

public class chiTietVe_MainActivity extends AppCompatActivity {
    ImageView img_btn_troLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frm_chitietve);
        
        addControls();
        addEvents();
        
    }

    private void addControls() {
        img_btn_troLai = findViewById(R.id.img_veDaDat_troLai);

    }

    private void addEvents() {

    }
}