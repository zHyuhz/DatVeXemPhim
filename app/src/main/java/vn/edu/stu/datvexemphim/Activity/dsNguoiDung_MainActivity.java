package vn.edu.stu.datvexemphim.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import vn.edu.stu.datvexemphim.Models.User;
import vn.edu.stu.datvexemphim.R;
import vn.edu.stu.datvexemphim.ViewMatch.UserAdapter;

public class dsNguoiDung_MainActivity extends AppCompatActivity {
    ImageView img_btn_troLai;
    FloatingActionButton flBtn_themMoi;
    ListView listView;
    List<User> userList;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.frm_danhsach_nguoidung);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addControls();
        addEvents();
    }

    private void addControls() {
        img_btn_troLai = findViewById(R.id.img_dsNguoiDung_troLai);
        flBtn_themMoi = findViewById(R.id.frmDanhSachNguoiDung_btn_themMoi);

        listView = findViewById(R.id.lv_NguoiDung);
        userList = new ArrayList<>();
        userList.add(new User("Nguyen Van A", "0987654321"));
        userList.add(new User("Tran Thi B", "0912345678"));
        userList.add(new User("Le Van C", "0908765432"));
        userList.add(new User("Hoang Minh D", "0931234567"));
        userList.add(new User("Pham Thi E", "0976543210"));
        userList.add(new User("Do Van F", "0956781234"));
        userList.add(new User("Bui Minh G", "0923456789"));
        userList.add(new User("Dang Thi H", "0945678901"));
        userList.add(new User("Ngo Van I", "0989012345"));
        userList.add(new User("Pham Thi K", "0910987654"));
        userList.add(new User("Tran Van L", "0901234567"));
        userList.add(new User("Nguyen Thi M", "0934567890"));
        userList.add(new User("Le Minh N", "0978901234"));
        userList.add(new User("Bui Thi O", "0954321987"));
        userList.add(new User("Dang Van P", "0921098765"));
        adapter = new UserAdapter(this, R.layout.item_nguoidung, userList);
        listView.setAdapter(adapter);


    }

    private void addEvents() {
        img_btn_troLai.setOnClickListener(v -> {
            Intent intent = new Intent(this, trangChu_MainActivity.class);
            startActivity(intent);
        });
        flBtn_themMoi.setOnClickListener(v -> {
            Intent intent = new Intent(this, capNhatNguoiDung_MainActivity.class);
            startActivity(intent);
        });
    }

    private void xoaTaiKhoan() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(dsNguoiDung_MainActivity.this);
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