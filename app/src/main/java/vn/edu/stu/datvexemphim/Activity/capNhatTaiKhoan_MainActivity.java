package vn.edu.stu.datvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import vn.edu.stu.datvexemphim.R;

public class capNhatTaiKhoan_MainActivity extends AppCompatActivity {
    ImageView img_btn_troLai;
    EditText edt_emails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.frm_capnhattaikhoan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addControls();
        addEvents();
    }

    private void addControls() {
        img_btn_troLai = findViewById(R.id.img_chiTietTaiKhoan_troLai);
        edt_emails = findViewById(R.id.frmChiTietTK_edt_emails);
    }

    private void addEvents() {
        img_btn_troLai.setOnClickListener(v -> {
            Intent intent = new Intent(this, dsTaiKhoan_MainActivity.class);
            startActivity(intent);
        });
        xulyNhapEmail();
    }

    private void xulyNhapEmail() {
        edt_emails.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String email = edt_emails.getText().toString().trim();
                if (!isValidGmail(email)) {
                    edt_emails.setError("Email sai định dạng example@gmail.com");
                }
            }
        });
    }

    private boolean isValidGmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        return email.matches(emailPattern);
    }
}