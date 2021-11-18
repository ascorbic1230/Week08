package com.example.week08;


import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends FragmentActivity implements com.example.week08.MainCallbacks {
    FragmentTransaction ft;
    com.example.week08.Fragment1 fragment1; Fragment2 fragment2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft = getSupportFragmentManager().beginTransaction();
        fragment1 = com.example.week08.Fragment1.newInstance("first-blue");
        ft.replace(R.id.main_holder_1, fragment1);
        ft.commit();

        ft = getSupportFragmentManager().beginTransaction();
        fragment2 = Fragment2.newInstance("first-red");
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