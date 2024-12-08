package vn.edu.stu.datvexemphim.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import vn.edu.stu.datvexemphim.R;

public class dangNhap_MainActivity extends AppCompatActivity {
    EditText edt_matKhau, edt_ngaySinh, edt_gioiTinh, edt_email, edt_thanhPho;
    TextView tv_dangKy;
    CardView dangKyCardView, dangNhapCardView;
    Button DK_btn_quayLai;

    private boolean isPasswordVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frm_dangnhap_activity);

        addControls();
        addEvents();
    }


    private void addControls() {
        edt_matKhau = findViewById(R.id.edt_matKhau);
        edt_ngaySinh= findViewById(R.id.edt_ngaySinh);
        edt_gioiTinh = findViewById(R.id.edt_gioiTinh);
        edt_email = findViewById(R.id.edt_email);
        edt_thanhPho = findViewById(R.id.edt_thanhPho);

        tv_dangKy = findViewById(R.id.tv_dangKy);

        dangKyCardView = findViewById(R.id.cardView_dangKy);
        dangNhapCardView = findViewById(R.id.cardView_dangNhap);

        DK_btn_quayLai = findViewById(R.id.DK_btn_quayLai);
    }
    private void addEvents() {
        xulyAnHienMatKhau();
        xulyChuyenSangGDDangKy();
        xulyQuayLayDangNhap();
        xulyHienThiDialogNgaySinh();
        xulyHienThiDialogGioiTinh();
        xulyNhapEmail();

    }

    private void xulyHienThiDialogGioiTinh() {
        // Biến lưu giá trị giới tính (0 = Nam, 1 = Nữ)
        edt_gioiTinh.setOnClickListener(v -> {
            String[] gioiTinh = {"Nam", "Nữ"};

            // Dùng AlertDialog để hiển thị danh sách giới tính
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Chọn giới tính")
                    .setItems(gioiTinh, (dialog, luaChon) -> {
                        // Lưu và hiển thị giới tính đã chọn
                        switch (luaChon) {
                            case 0: // Nam
                                edt_gioiTinh.setText("Nam");
                                edt_gioiTinh.setTag(0); // Lưu giá trị 0 (Nam)
                                break;
                            case 1: // Nữ
                                edt_gioiTinh.setText("Nữ");
                                edt_gioiTinh.setTag(1); // Lưu giá trị 1 (Nữ)
                                break;

                        }
                    });
            builder.show();
        });
    }

    private void xulyNhapEmail() {
        edt_email.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) { // Khi EditText mất focus
                String email = edt_email.getText().toString().trim();
                if (!isValidGmail(email)) {
                    edt_email.setError("Email sai định dạng example@gmail.com");
                }
            }
        });
    }

    // Hàm kiểm tra định dạng email
    private boolean isValidGmail(String email) {
        // Biểu thức chính quy kiểm tra email kết thúc bằng @gmail.com
        String emailPattern = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        return email.matches(emailPattern);
    }
    private void xulyHienThiDialogNgaySinh() {
        edt_ngaySinh.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year1, month1, dayOfMonth) -> {
                        String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                        edt_ngaySinh.setText(selectedDate);
                    }, year, month, day);

            datePickerDialog.show();
        });
    }
    private void xulyQuayLayDangNhap() {
        DK_btn_quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangKyCardView.setVisibility(View.GONE);
                dangNhapCardView.setVisibility(View.VISIBLE);
            }
        });
    }


    private void xulyChuyenSangGDDangKy() {
        tv_dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangNhapCardView.setVisibility(View.GONE);
                dangKyCardView.setVisibility(View.VISIBLE);
            }
        });
    }
    private void xulyAnHienMatKhau() {
        edt_matKhau.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Lấy vị trí drawableEnd (phía bên phải)
                    int drawableRightWidth = edt_matKhau.getCompoundDrawables()[2].getBounds().width();

                    // Kiểm tra nếu vị trí chạm nằm trong drawableEnd
                    if (event.getRawX() >= (edt_matKhau.getRight() - drawableRightWidth)) {
                        // Toggle trạng thái hiển thị mật khẩu
                        togglePasswordVisibility();
                        return true; // Đã xử lý sự kiện, không cần xử lý tiếp
                    }
                }
                return false;
            }
        });
    }
    private void togglePasswordVisibility() {
        if (edt_matKhau.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            // Hiển thị mật khẩu
            edt_matKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            edt_matKhau.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icons8_open_eye_32_, 0);
        } else {
            // Ẩn mật khẩu
            edt_matKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            edt_matKhau.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icons8_eye_32, 0);
        }
        // Đặt con trỏ về cuối văn bản
        edt_matKhau.setSelection(edt_matKhau.getText().length());
    }
}