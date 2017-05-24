package com.example.kryguu.laboratoria8;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class InfoFragment extends Fragment {
    private TextView mHeader;
    private TextView mInfo;

    public InfoFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_info, container, false);
        mHeader = (TextView) root.findViewById(R.id.headerTextView);
        mInfo = (TextView) root.findViewById(R.id.infoTextView);
        return root;
    }


    public void SetHeader(String header)
    {
        mHeader.setText(header);
    }

    public void SetInfo(String info)
    {
        mInfo.setText(info);
    }

}
