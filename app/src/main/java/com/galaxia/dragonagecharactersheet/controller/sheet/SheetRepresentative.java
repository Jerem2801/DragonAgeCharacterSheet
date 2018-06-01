package com.galaxia.dragonagecharactersheet.controller.sheet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.galaxia.dragonagecharactersheet.R;

public class SheetRepresentative extends Fragment {

    private Context context;
    private LayoutInflater inflater;

    public static SheetRepresentative newInstance() {
        return (new SheetRepresentative());
    }


    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        View inflate = inflater.inflate(R.layout.sheet_attribute, container, false);
        this.context = inflate.getContext();
        return inflate;
    }

}
