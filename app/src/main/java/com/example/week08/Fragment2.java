package com.example.week08;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Fragment2 extends Fragment implements FragmentCallbacks {
    Context context;
    private int position;
    private TextView txtMSSV;
    private TextView txtInfo;
    private Button btnFirst;
    private Button btnPrevios;
    private Button btnNext;
    private Button btnLast;

    private com.example.week08.Member members[] = {
            new com.example.week08.Member("19120070", "Trần Nhật Hào", "19_3", 10),
            new com.example.week08.Member("19120168", "Lê Viết Bách", "19_3", 10),
            new com.example.week08.Member("19120141", "Nguyễn Quốc Toàn", "19_3", 10),
            new com.example.week08.Member("19120722", "Văn Thế Vinh", "19_3", 10),
            new com.example.week08.Member("19120129", "Huỳnh Minh Thắng", "19_3", 10)
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    public static Fragment2 newInstance(String strArg1) {
        Fragment2 fragment = new Fragment2();
        Bundle bundle = new Bundle();
        bundle.putString("arg1", strArg1);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout view_fragment2 = (LinearLayout) inflater.inflate(R.layout.layout_fragment2, null);
        txtMSSV = (TextView) view_fragment2.findViewById(R.id.txtMaSo2);
        txtInfo = (TextView) view_fragment2.findViewById(R.id.txtInfo);

        try {
            Bundle arguments = getArguments();
        }
        catch (Exception e) {
            Log.e("RED BUNDLE ERROR – ", "" + e.getMessage());
        }

        txtMSSV.setText(" ");
        txtInfo.setText("Họ tên: " + "\n" +
                "Lớp: "  + "\n" +
                "Điểm trung bình: ");

        btnFirst = (Button) view_fragment2.findViewById(R.id.btnFirst);
        btnPrevios = (Button) view_fragment2.findViewById(R.id.btnPrevious);
        btnNext = (Button) view_fragment2.findViewById(R.id.btnNext);
        btnLast = (Button) view_fragment2.findViewById(R.id.btnLast);

        setButtonEnable(false, false, false, false);

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = 0;
                setButtonEnable(false, false, true, true);
                setData(position);
            }
        });
        btnPrevios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                if (position == 0) {
                    setButtonEnable(false, false, true, true);
                }
                else {
                    setButtonEnable(true, true, true, true);
                }
                setData(position);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position++;
                if (position == members.length - 1) {
                    setButtonEnable(true,true, false, false);
                }
                else {
                    setButtonEnable(true, true, true, true);
                }
                setData(position);
            }
        });
        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = members.length - 1;
                setButtonEnable(true,true, false, false);
                setData(position);
            }
        });
        return view_fragment2;
    }

    @Override
    public void onMsgFromMainToFragment(int value) {
        if (value == 0) {
            setButtonEnable(false, false, true, true);
        } else if (value == members.length - 1) {
            setButtonEnable(true,true, false, false);
        }
        else {
            setButtonEnable(true, true, true, true);
        }

        position = value;
        setData(position);
    }

    public void setButtonEnable(boolean first, boolean prev, boolean next, boolean last) {
        btnPrevios.setEnabled(prev);
        btnFirst.setEnabled(first);
        btnLast.setEnabled(last);
        btnNext.setEnabled(next);
        if (!first)
            btnFirst.setAlpha(0.2f);
        else
            btnFirst.setAlpha(1);
        if (!prev)
            btnPrevios.setAlpha(0.2f);
        else
            btnPrevios.setAlpha(1);
        if (!next)
            btnNext.setAlpha(0.2f);
        else
            btnNext.setAlpha(1);
        if (!last)
            btnLast.setAlpha(0.2f);
        else
            btnLast.setAlpha(1);
    }

    public void setData(int position) {
        txtMSSV.setText(members[position].getId());
        txtInfo.setText("Họ tên: " + members[position].getName() + "\n" +
                "Lớp: " + members[position].getClassName() + "\n" +
                "Điểm trung bình: " + members[position].getScore());
        ((com.example.week08.MainActivity) context).onMsgFromFragToMain("Fragment 2", position);
    }
}
