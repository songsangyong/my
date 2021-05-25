package com.example.myproject.test;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.myproject.MainActivity;
import com.example.myproject.dialog.CommonDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.myproject.R;

public class DialogTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);

        //  커스텀
//        View view = LayoutInflater.from(this).inflate(R.layout.layout_custom_dialog, null);
//        CommonDialog.show(DialogTestActivity.this, view, "취소", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(DialogTestActivity.this, "취소", Toast.LENGTH_SHORT).show();
//            }
//        }, "확인", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(DialogTestActivity.this, "확인", Toast.LENGTH_SHORT).show();
//            }
//        });


        //  버튼 2개
//        CommonDialog.show(DialogTestActivity.this, "msg", "확인", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        }, "취소", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });

        //  버튼 1개
        CommonDialog.show(this, "title");

        //    버튼 1개
//        CommonDialog.show(this, "title", new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialogInterface) {
//                dialogInterface.dismiss();
//
//                Toast.makeText(DialogTestActivity.this, "Test", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}