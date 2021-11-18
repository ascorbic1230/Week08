package com.example.week08;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Fragment1 extends Fragment implements FragmentCallbacks {
    Context main;
    String message = "";
    private ArrayList<String> ids;
    private ListView listView;
    private View oldPosition = null;
    private TextView txtMaSo1;
    private static Cursor cursor;

    public static Fragment1 newInstance(String strArg, Cursor c) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        cursor = c;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            main = getActivity();

            ids = new ArrayList<String>();
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                ids.add(cursor.getString(0));
            }
        } catch (IllegalStateException e) {
            throw new IllegalStateException("MainActivity must implement callbacks");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout_fragment1 = (LinearLayout) inflater.inflate(R.layout.layout_fragment1, null);
        System.out.println("Hello World");
        txtMaSo1 = (TextView) layout_fragment1.findViewById(R.id.txtMaSo1);

        CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(), R.layout.list_item_layout, ids);
        listView = (ListView) layout_fragment1.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setSelection(0);
        listView.smoothScrollToPosition(0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (oldPosition != null) {
                    oldPosition.setBackgroundResource(0);
                }
                txtMaSo1.setText("Mã số: " + ids.get(position));
                v.setBackgroundResource(R.drawable.background);
                oldPosition = v;
                ((MainActivity) main).onMsgFromFragToMain("Fragment 1", position);
            }
        });
        return layout_fragment1;
    }


    @Override
    public void onMsgFromMainToFragment(int value) {
        txtMaSo1.setText("Mã số: " + ids.get(value));
        if (oldPosition != null) {
            oldPosition.setBackgroundResource(0);
        }
        listView.getChildAt(value).setBackgroundResource(R.drawable.background);
        oldPosition = listView.getChildAt(value);
    }
}