package com.galaxia.dragonagecharactersheet.controller.sheet;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.sheet.bean.PlayerResumeBean;
import com.galaxia.dragonagecharactersheet.controller.sheet.manager.PlayerResumeManager;
import com.galaxia.dragonagecharactersheet.element.background.BackgroundManager;
import com.galaxia.dragonagecharactersheet.element.classe.ClasseManager;
import com.galaxia.dragonagecharactersheet.element.race.RaceManager;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;
import com.galaxia.dragonagecharactersheet.ui.UiViewUtils;


public class SheetResume extends Fragment {

    private Context context;

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
        LinearLayout mother = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(UiUtils.sizeInDp(context,16), 0, UiUtils.sizeInDp(context,16), 0);
        mother.setLayoutParams(params);
        mother.setOrientation(LinearLayout.VERTICAL);
        mother.setBackground(getResources().getDrawable(R.drawable.custom_border));
        linear.addView(mother);

        createView(mother,"Niveau : ",playerResume.getLevel());
        createView(mother,"Nom : ",playerResume.getName());
        String race = RaceManager.getRace(playerResume.getRace()).getName();
        createView(mother,"Race : ",race);
        String classe = ClasseManager.getClasse(playerResume.getClasse()).getName();
        createView(mother,"Classe : ",classe);
        String background = BackgroundManager.getBackground(playerResume.getBackground()).getName();
        createView(mother,"Background : ",background);

        return inflate;
    }

    private void createView(LinearLayout mother, String key, String value) {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,  UiUtils.sizeInDp(context,8), 0,0);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        mother.addView(linearLayout);

        TextView keyName =  new TextView(context);
        LinearLayout.LayoutParams paramsKeyName = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsKeyName.setMargins(UiUtils.sizeInDp(context,8), 0, 0, 0);
        keyName.setLayoutParams(paramsKeyName);
        UiViewUtils.setTextViewTitle(keyName);
        keyName.setText(key);
        linearLayout.addView(keyName);

        TextView valueName =  new TextView(context);
        valueName.setText(value);
        valueName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        linearLayout.addView(valueName);

        LinearLayout line = UiViewUtils.createLine(context);
        mother.addView(line);
    }



}
