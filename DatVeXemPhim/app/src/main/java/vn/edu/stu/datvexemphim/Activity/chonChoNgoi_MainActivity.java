package vn.edu.stu.datvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.edu.stu.datvexemphim.CustomEvent.IDatePicker;
import vn.edu.stu.datvexemphim.R;
import vn.edu.stu.datvexemphim.ViewMatch.DatePickerAdapter;

public class chonChoNgoi_MainActivity extends AppCompatActivity implements View.OnClickListener{


    private final int[] gheNgoi = {R.id.btn_A1, R.id.btn_A2, R.id.btn_A3, R.id.btn_A4, R.id.btn_A5, R.id.btn_B1,
            R.id.btn_B2, R.id.btn_B3, R.id.btn_B4, R.id.btn_B5, R.id.btn_C1, R.id.btn_C2, R.id.btn_C3
            , R.id.btn_C4, R.id.btn_C5, R.id.btn_D1, R.id.btn_D2, R.id.btn_D3, R.id.btn_D4, R.id.btn_D5
            , R.id.btn_E1, R.id.btn_E2, R.id.btn_E3, R.id.btn_E4, R.id.btn_E5};
    boolean isChecked = false;
    Button btn_comfirm;
    boolean[] selected = new boolean[gheNgoi.length];
    Button btn_troLai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frm_chon_cho_ngoi_activity);

        addControls();
        addEvents();


    }

    private void addControls() {
        for (int id : gheNgoi) {
            findViewById(id).setOnClickListener(this);
        }
        btn_comfirm = findViewById(R.id.btn_comfirm);
        btn_troLai = findViewById(R.id.img_ChoNgoi_troLai);


    }

    private void addEvents() {
        btn_comfirm.setOnClickListener(v -> {
            List<Integer> selectedSeats = getSelectedSeats();
            if (selectedSeats.isEmpty()) {
                Toast.makeText(chonChoNgoi_MainActivity.this, "Bạn chưa chọn ghế nào!", Toast.LENGTH_SHORT).show();
            } else {
                confirmSelected();
            }
        });
        btn_troLai.setOnClickListener(v -> {
            Intent intent = new Intent(this, lichChieu_MainActivity.class);
            startActivity(intent);
        });

    }


    public void confirmSelected() {
        List<Integer> getSeats = getSelectedSeats();
        if (getSeats.isEmpty()) {
            Toast.makeText(this, "Chưa chọn ghế!", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder builder = new StringBuilder("Ghế đã chọn: ");
            for (int seat : getSeats) {
                builder.append("E").append(seat + 1).append(", ");
            }
            Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();
        }


    }

    public List<Integer> getSelectedSeats() {
        List<Integer> selectedSeats = new ArrayList<>();
        for (int i = 0; i < gheNgoi.length; i++) {
            if (selected[i]) {
                selectedSeats.add(i + 1);
            }
        }
        return selectedSeats;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        for (int i = 0; i < gheNgoi.length; i++) {
            if (id == gheNgoi[i]) {
                selected[i] = !selected[i];
                Button button = findViewById(id);
                if (selected[i]) {
                    button.setBackgroundColor(ContextCompat.getColor(this, R.color.backGround_selected));
                    button.setTextColor(ContextCompat.getColor(this, R.color.textView_selected));
                } else {
                    button.setBackgroundColor(ContextCompat.getColor(this, R.color.backGround_unSelected));
                    button.setTextColor(ContextCompat.getColor(this, R.color.textView_unSelected));
                }
                break;
            }
        }
    }

}
