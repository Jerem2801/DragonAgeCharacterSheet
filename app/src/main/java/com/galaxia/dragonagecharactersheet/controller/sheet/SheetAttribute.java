package com.galaxia.dragonagecharactersheet.controller.sheet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.sheet.bean.PlayerAttributeBean;
import com.galaxia.dragonagecharactersheet.controller.sheet.manager.PlayerAttributeManager;
import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.focus.FocusManager;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;
import com.galaxia.dragonagecharactersheet.ui.UiViewUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
        List<PlayerAttributeBean> beans = new ArrayList(playerAttribute.values());
        PlayerAttributeManager.sort(beans);

        LinearLayout linear = (LinearLayout) inflate;

        LinearLayout motherLayout = createMotherLayout(context);
        linear.addView(motherLayout);

        for(PlayerAttributeBean bean : beans){
            createView(motherLayout,bean);
        }


        return inflate;
    }

    @SuppressLint("NewApi")
    private void createView(LinearLayout mother, PlayerAttributeBean bean) {
        //ATTRIBUTE AND VALUE AND HELP
        LinearLayout attributeValueHelpLayout = createAttributeValueHelpLayout(context, bean);
        mother.addView(attributeValueHelpLayout);

        //LINE
        LinearLayout line = UiViewUtils.createLine(context);
        mother.addView(line);

        //FOCUS
        LinearLayout focusLayout = createFocusLayout(context,bean);
        mother.addView(focusLayout);
    }

    @SuppressLint("NewApi")
    private LinearLayout createFocusLayout(Context context, PlayerAttributeBean bean) {
        LinearLayout focusLinear = new LinearLayout(context);
        LinearLayout.LayoutParams focusParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        focusParam.setMargins(0,  0, 0,UiUtils.sizeInDp(context,8));
        focusLinear.setLayoutParams(focusParam);
        focusLinear.setOrientation(LinearLayout.HORIZONTAL);

        for(String focus : bean.getFocuss()){
            TextView focusView =  new TextView(context);
            String name = FocusManager.getFocus(focus).getName();
            focusView.setText(name);
            focusView.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
            focusLinear.addView(focusView);
        }

        return focusLinear;
    }

    private LinearLayout createAttributeValueHelpLayout(Context context,PlayerAttributeBean bean){
        //ATTRIBUTE AND VALUE
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,  UiUtils.sizeInDp(context,8), 0,0);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView attributeNameView = createAttributeNameView(context, bean);
        linearLayout.addView(attributeNameView);

        LinearLayout linearLayoutRight = createLinearLayoutForValueAndHelp(context,bean);
        linearLayout.addView(linearLayoutRight);


        return linearLayout;
    }

    private LinearLayout createLinearLayoutForValueAndHelp(Context context, PlayerAttributeBean bean) {
        LinearLayout linearLayoutRight = createLinearLayoutRight(context);

        TextView valueView = createValueView(context, bean);
        linearLayoutRight.addView(valueView);

        ImageView helpView = createHelpView(context, bean);
        linearLayoutRight.addView(helpView);


        return linearLayoutRight;
    }

    private ImageView createHelpView(Context context,PlayerAttributeBean bean) {
        ImageView helpView = new ImageView(context);
        LinearLayout.LayoutParams helpViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        helpViewParams.setMargins(0, 0, UiUtils.sizeInDp(context,8), 0);
        helpView.setLayoutParams(helpViewParams);
        helpView.setImageBitmap(RessourceUtils.getImage(context, RessourcePath.HELP_PATH));
        Attribute attribute = AttributeManager.getAttribute(bean.getName());
        helpView.setOnClickListener(new SheetAttribute.CustomClickHelpListener(attribute));
        return helpView;
    }

    private TextView createValueView(Context context, PlayerAttributeBean bean){
        TextView valueView =  new TextView(context);
        LinearLayout.LayoutParams paramsAttributeValue = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsAttributeValue.setMargins(0, 0, UiUtils.sizeInDp(context,8), 0);
        valueView.setLayoutParams(paramsAttributeValue);
        UiViewUtils.setTextViewTitle(valueView);
        valueView.setText(bean.getValue());
        valueView.setGravity(Gravity.RIGHT);
        return valueView;
    }

    private LinearLayout createLinearLayoutRight(Context context){
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.RIGHT);
        return linearLayout;
    }

    private TextView createAttributeNameView(Context context,PlayerAttributeBean bean){
        TextView attributeNameView =  new TextView(context);
        LinearLayout.LayoutParams attributeNameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        attributeNameParams.setMargins(UiUtils.sizeInDp(context,8), 0, 0, 0);
        attributeNameView.setLayoutParams(attributeNameParams);
        UiViewUtils.setTextViewTitle(attributeNameView);
        String attributeNameValue = AttributeManager.getAttribute(bean.getName()).getName();
        attributeNameView.setText(attributeNameValue);
        return attributeNameView;
    }

    @SuppressLint("NewApi")
    private LinearLayout createMotherLayout(Context context){
        LinearLayout mother = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(UiUtils.sizeInDp(context,16), 0, UiUtils.sizeInDp(context,16), 0);
        mother.setLayoutParams(params);
        mother.setOrientation(LinearLayout.VERTICAL);
        mother.setBackground(getResources().getDrawable(R.drawable.custom_border));
        return mother;
    }

    class CustomClickHelpListener implements View.OnClickListener {

        private Attribute attribute;

        public CustomClickHelpListener(Attribute attribute){
            this.attribute = attribute;
        }

        @Override
        public void onClick(View v) {

        }
    }


}