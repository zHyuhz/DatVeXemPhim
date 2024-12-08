package vn.edu.stu.datvexemphim.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import vn.edu.stu.datvexemphim.Models.Accounts;
import vn.edu.stu.datvexemphim.R;
import vn.edu.stu.datvexemphim.ViewMatch.AccountAdapter;

public class dsTaiKhoan_MainActivity extends AppCompatActivity {
    ImageView img_troLai;
    AccountAdapter adapter;
    List<Accounts> accountsList;
    ListView listView;
    FloatingActionButton flBtn_themMoi;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frm_danhsach_taikhoan);

        addControls();
        addEvents();
    }

    private void addControls() {
        img_troLai = findViewById(R.id.img_dsTaiKhoan_troLai);
        listView = findViewById(R.id.lv_taiKhoan);

        flBtn_themMoi = findViewById(R.id.frmDanhSachTK_btn_themMoi);

        accountsList = new ArrayList<>();
        accountsList.add(new Accounts(1, "johnDoe", "pass123", "user"));
        accountsList.add(new Accounts(2, "janeSmith", "securePass", "admin"));
        accountsList.add(new Accounts(3, "aliceLee", "alice2024", "user"));
        accountsList.add(new Accounts(4, "bobMartin", "martinB123", "moderator"));
        accountsList.add(new Accounts(5, "charlieBrown", "choco123", "editor"));
        accountsList.add(new Accounts(6, "emilyClark", "password6", "user"));
        accountsList.add(new Accounts(7, "frankWhite", "frankW99", "manager"));
        accountsList.add(new Accounts(8, "graceHopper", "codeQueen", "developer"));
        accountsList.add(new Accounts(9, "henryFord", "carMaker", "admin"));
        accountsList.add(new Accounts(10, "isabelWong", "creative123", "designer"));
        accountsList.add(new Accounts(11, "jackWilson", "wilsonJack", "tester"));
        accountsList.add(new Accounts(12, "karenJones", "karen2024", "analyst"));
        accountsList.add(new Accounts(13, "leoKing", "kingLeo", "admin"));
        accountsList.add(new Accounts(14, "miaScott", "scottMia", "support"));
        accountsList.add(new Accounts(15, "noahTaylor", "taylorN", "user"));
        accountsList.add(new Accounts(16, "oliviaBrown", "olivia99", "moderator"));

        adapter = new AccountAdapter(this, R.layout.item_taikhoan, accountsList);
        listView.setAdapter(adapter);
    }

    private void addEvents() {
        img_troLai.setOnClickListener(v -> {
            Intent intent = new Intent(this, trangChu_MainActivity.class);
            startActivity(intent);
        });
        flBtn_themMoi.setOnClickListener(v -> {
            Intent intent = new Intent(this,capNhatTaiKhoan_MainActivity.class);
            startActivity(intent);
        });
        xoaTaiKhoan();
    }

    private void xoaTaiKhoan() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(dsTaiKhoan_MainActivity.this);
                builder.setTitle("Xoá tài khoản");
                builder.setMessage("Bạn có thực sự muốn xóa tài khoản này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false); // bắt buộc phải chọn mới tắt dialog >< true: nhấn ra ngoài dialog sẽ tắt
                alertDialog.show();

                return true;
            }
        });
    }
}