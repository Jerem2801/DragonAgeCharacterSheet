package com.galaxia.dragonagecharactersheet.controller.sheet;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.sheet.bean.PlayerResumeBean;
import com.galaxia.dragonagecharactersheet.controller.sheet.manager.PlayerResumeManager;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.galaxia.dragonagecharactersheet.stream.ReaderUtils;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;
import com.galaxia.dragonagecharactersheet.ui.UiViewUtils;

import java.util.List;


public class SheetResume extends Fragment {

    private Context context;
    private TextView textView;

    public static SheetResume newInstance() {
        return (new SheetResume());
    }


    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.sheet_resume, container, false);
        this.context = inflate.getContext();

        PlayerResumeBean playerResume = PlayerResumeManager.getPlayerResume(context);

        LinearLayout linear = (LinearLayout) inflate;
        createView(linear,"Nom : ",playerResume.getName());
        createView(linear,"Race : ",playerResume.getRace());
        createView(linear,"Classe : ",playerResume.getClasse());
        createView(linear,"Background : ",playerResume.getBackground());
        createView(linear,"Level : ",playerResume.getLevel());

        return inflate;
    }

    private void createView(LinearLayout mother, String key, String value) {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        mother.addView(linearLayout);

        TextView keyName =  new TextView(context);
        UiViewUtils.setTextViewTitle(keyName);
        keyName.setText(key);
        linearLayout.addView(keyName);

        TextView valueName =  new TextView(context);
        valueName.setText(value);
        linearLayout.addView(valueName);

    }


}
