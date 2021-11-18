package com.example.week08;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends FragmentActivity implements com.example.week08.MainCallbacks {
    FragmentTransaction ft;
    com.example.week08.Fragment1 fragment1; Fragment2 fragment2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = this.openOrCreateDatabase("myfriendsDB", MODE_PRIVATE, null);

        db.beginTransaction();
        try {
            db.execSQL(" DROP TABLE IF EXISTS Lop; ");
            db.execSQL(" DROP TABLE IF EXISTS HocSinh; ");
            String sql1 = "create table Lop ("
                            + "MaLop text PRIMARY KEY,"
                            + "TenLop text );";
            String sql2 = "create table HocSinh ("
                            + "MaHocSinh text PRIMARY KEY,"
                            + "MaLop text,"
                            + "TenHocSinh text,"
                            + "Diem float);";
            db.execSQL(sql1);
            db.execSQL(sql2);
            db.execSQL("insert into Lop(MaLop, TenLop) values('19_3', 'Mobile')");
            db.execSQL("insert into HocSinh(MaHocSinh, MaLop, TenHocSinh, Diem) values('19120070', '19_3', 'Trần Nhật Hào', 10)");
            db.execSQL("insert into HocSinh(MaHocSinh, MaLop, TenHocSinh, Diem) values('19120129', '19_3', 'Huỳnh Minh Thắng', 10)");
            db.execSQL("insert into HocSinh(MaHocSinh, MaLop, TenHocSinh, Diem) values('19120141', '19_3', 'Nguyễn Quốc Toàn', 10)");
            db.execSQL("insert into HocSinh(MaHocSinh, MaLop, TenHocSinh, Diem) values('19120168', '19_3', 'Lê Viết Bách', 10)");
            db.execSQL("insert into HocSinh(MaHocSinh, MaLop, TenHocSinh, Diem) values('19120722', '19_3', 'Văn Thế Vinh', 10)");


            db.setTransactionSuccessful(); //commit your changes
        }
        catch (SQLiteException e) {
        //report problem
        }
        finally { db.endTransaction(); }

        String sql = "Select * from HocSinh";
        Cursor c = db.rawQuery(sql, null);

        ft = getSupportFragmentManager().beginTransaction();
        fragment1 = com.example.week08.Fragment1.newInstance("first-blue", c);
        ft.replace(R.id.main_holder_1, fragment1);
        ft.commit();

        ft = getSupportFragmentManager().beginTransaction();
        fragment2 = Fragment2.newInstance("first-red", c);
        ft.replace(R.id.main_holder_2, fragment2);
        ft.commit();

    }

    @Override
    public void onMsgFromFragToMain(String sender, int position) {

        if (sender.equals("Fragment 1")){
            fragment2.onMsgFromMainToFragment(position);
        }
        if (sender.equals("Fragment 2")) {
            fragment1.onMsgFromMainToFragment(position);
        }
    }

}