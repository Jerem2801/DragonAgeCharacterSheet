package com.galaxia.dragonagecharactersheet.controller.sheet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.sheet.bean.PlayerAttributeBean;
import com.galaxia.dragonagecharactersheet.controller.sheet.manager.PlayerAttributeManager;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;
import com.galaxia.dragonagecharactersheet.ui.UiViewUtils;

import java.util.Map;

public class SheetAttribute extends Fragment {

    private Context context;

    public static SheetAttribute newInstance() {
        return (new SheetAttribute());
    }


    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.sheet_attribute, container, false);
        this.context = inflate.getContext();

        Map<String, PlayerAttributeBean> playerAttribute = PlayerAttributeManager.getPlayerAttribute(context);

        LinearLayout linear = (LinearLayout) inflate;
        for(PlayerAttributeBean bean : playerAttribute.values()){
            createView(linear,bean);
        }


        return inflate;
    }

    @SuppressLint("NewApi")
    private void createView(LinearLayout mother, PlayerAttributeBean value) {
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
        keyName.setText(value.getName());
        linearLayout.addView(keyName);

        TextView valueName =  new TextView(context);
        valueName.setText(value.getValue());
        valueName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        linearLayout.addView(valueName);

        LinearLayout line = UiViewUtils.createLine(context);
        mother.addView(line);

        LinearLayout focusLinear = new LinearLayout(context);
        LinearLayout.LayoutParams focusParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        focusParam.setMargins(0,  UiUtils.sizeInDp(context,8), 0,0);
        focusLinear.setLayoutParams(focusParam);
        focusLinear.setOrientation(LinearLayout.HORIZONTAL);
        mother.addView(focusLinear);

        for(String focus : value.getFocuss()){
            TextView focusView =  new TextView(context);
            focusView.setText(focus);
            focusView.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
            focusLinear.addView(focusView);
        }

    }
}