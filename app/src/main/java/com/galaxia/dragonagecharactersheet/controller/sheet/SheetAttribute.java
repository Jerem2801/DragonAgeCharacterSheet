package com.galaxia.dragonagecharactersheet.controller.sheet;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.sheet.bean.PlayerAttributeBean;
import com.galaxia.dragonagecharactersheet.controller.sheet.manager.PlayerAttributeManager;
import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.focus.FocusManager;
import com.galaxia.dragonagecharactersheet.ui.DialogManager;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;
import com.galaxia.dragonagecharactersheet.ui.UiViewUtils;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SheetAttribute extends Fragment {

    private Context context;
    private LayoutInflater inflater;

    public static SheetAttribute newInstance() {
        return (new SheetAttribute());
    }


    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        View inflate = inflater.inflate(R.layout.sheet_attribute, container, false);
        this.context = inflate.getContext();

        Map<String, PlayerAttributeBean> playerAttribute = PlayerAttributeManager.getPlayerAttribute(context);
        List<PlayerAttributeBean> beans = new ArrayList(playerAttribute.values());
        PlayerAttributeManager.sort(beans);

        LinearLayout linear = (LinearLayout) inflate;
        ScrollView scroll = new ScrollView(context);
        linear.addView(scroll);
        LinearLayout motherLayout = createMotherLayout(context);
        scroll.addView(motherLayout);
        //linear.addView(motherLayout);

        for(PlayerAttributeBean bean : beans){
            createView(motherLayout,bean);
        }

        return inflate;
    }



    @SuppressLint("NewApi")
    private void createView(LinearLayout mother, PlayerAttributeBean bean) {
        LinearLayout attributeAndValueAndFocusLinearLayout = createAttributeAndValueAndFocusLinearLayout();
        mother.addView(attributeAndValueAndFocusLinearLayout);

        //ATTRIBUTE AND FOCUS
        LinearLayout attributeAndFocus = createAttributeAndFocus(bean);
        attributeAndValueAndFocusLinearLayout.addView(attributeAndFocus);

        //VALUE
        LinearLayout value = createValueView(bean);
        attributeAndValueAndFocusLinearLayout.addView(value);
    }

    private LinearLayout createValueView(PlayerAttributeBean bean) {
        LinearLayout valueLinearLayout = createValueLinearLayout();

        TextView valueView = createValueTextView(bean);
        valueLinearLayout.addView(valueView);

        return valueLinearLayout;
    }

    private TextView createValueTextView(PlayerAttributeBean bean){
        TextView valueView =  new TextView(context);
        //LinearLayout.LayoutParams paramsAttributeValue = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //paramsAttributeValue.setMargins(0, 0, UiUtils.sizeInDp(context,8), 0);
        LinearLayout.LayoutParams paramsAttributeValue = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        valueView.setLayoutParams(paramsAttributeValue);
        UiViewUtils.setValue(valueView);
        valueView.setText(bean.getValue());
        valueView.setGravity(Gravity.CENTER);
        return valueView;
    }

    @SuppressLint("NewApi")
    private LinearLayout createValueLinearLayout() {
        LinearLayout attributeFocusLinearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams attributeFocusLinearLayoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        attributeFocusLinearLayoutParams.weight = 0.5f;
        attributeFocusLinearLayoutParams.setMargins(0,  0, 0,0);
        attributeFocusLinearLayout.setLayoutParams(attributeFocusLinearLayoutParams);
        attributeFocusLinearLayout.setOrientation(LinearLayout.VERTICAL);
        attributeFocusLinearLayout.setBackground(context.getResources().getDrawable(R.drawable.custom_border_black));
        return attributeFocusLinearLayout;
    }

    private LinearLayout createAttributeAndFocus(PlayerAttributeBean bean) {
        LinearLayout attributeAndFocusLinearLayout = createAttributeAndFocusLinearLayout();

        TextView attributeNameView = createAttributeNameView(bean);
        attributeAndFocusLinearLayout.addView(attributeNameView);

        FlexboxLayout focusView = createFocusView(bean);
        attributeAndFocusLinearLayout.addView(focusView);

        return attributeAndFocusLinearLayout;
    }


    @SuppressLint("NewApi")
    private FlexboxLayout createFocusView(PlayerAttributeBean bean) {
        FlexboxLayout flex = createFlexLayout();

        for(String focus : bean.getFocuss()){
            TextView focusView =  createFocusList(focus);
            flex.addView(focusView);
        }

        if(flex.getChildAt(0) == null){
            TextView focusView = new TextView(context);
            FlexboxLayout.LayoutParams focusParams = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT);
            focusParams.setMargins(UiUtils.sizeInDp(context,4),  UiUtils.sizeInDp(context,4), UiUtils.sizeInDp(context,4),UiUtils.sizeInDp(context,4));
            focusView.setLayoutParams(focusParams);
            focusView.setText("NONE");
            //focusView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
            focusView.setBackground(getResources().getDrawable(R.drawable.custom_border_focus));
            focusView.setVisibility(View.INVISIBLE);
            flex.addView(focusView);
        }

        return flex;
    }

    @SuppressLint("NewApi")
    private TextView createFocusList(String focus) {
        TextView focusView = new TextView(context);
        FlexboxLayout.LayoutParams focusParams = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT);
        focusParams.setMargins(UiUtils.sizeInDp(context,4),  UiUtils.sizeInDp(context,4), UiUtils.sizeInDp(context,4),UiUtils.sizeInDp(context,4));
        focusView.setLayoutParams(focusParams);
        String name = FocusManager.getFocus(focus).getName();
        focusView.setText(name);
        //focusView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        focusView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        focusView.setBackground(getResources().getDrawable(R.drawable.custom_border_focus));
        focusView.setOnClickListener(new CustomClickFocusHelpListener(FocusManager.getFocus(focus)));
        return focusView;
    }

    private FlexboxLayout createFlexLayout() {
        FlexboxLayout flexboxLayout = new FlexboxLayout(context);
        flexboxLayout.setFlexWrap(FlexWrap.WRAP);
        LinearLayout.LayoutParams focusParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        focusParam.setMargins(0,  0, 0,0);
        flexboxLayout.setLayoutParams(focusParam);
        return flexboxLayout;
    }


    private TextView createAttributeNameView(PlayerAttributeBean bean){
        TextView attributeNameView =  new TextView(context);
        LinearLayout.LayoutParams attributeNameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        attributeNameView.setPadding(UiUtils.sizeInDp(context,2),  UiUtils.sizeInDp(context,2), UiUtils.sizeInDp(context,2),UiUtils.sizeInDp(context,2));
        attributeNameView.setLayoutParams(attributeNameParams);
        UiViewUtils.setAttributeName(attributeNameView);
        String attributeNameValue = AttributeManager.getAttribute(bean.getName()).getName();
        attributeNameView.setText(attributeNameValue);
        attributeNameView.setGravity(Gravity.CENTER);
        attributeNameView.setOnClickListener(new CustomClickAttributeHelpListener(AttributeManager.getAttribute(bean.getName())));
        return attributeNameView;
    }

    private LinearLayout createAttributeAndFocusLinearLayout() {
        LinearLayout attributeFocusLinearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams attributeFocusLinearLayoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        attributeFocusLinearLayoutParams.weight = 1.5f;
        attributeFocusLinearLayout.setLayoutParams(attributeFocusLinearLayoutParams);
        attributeFocusLinearLayout.setOrientation(LinearLayout.VERTICAL);
        return attributeFocusLinearLayout;
    }

    @SuppressLint("NewApi")
    private LinearLayout createAttributeAndValueAndFocusLinearLayout() {
        LinearLayout attributeValueFocusLinearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams attributeValueFocusLinearLayoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        attributeValueFocusLinearLayoutparams.setMargins(0,  UiUtils.sizeInDp(context,8), 0,0);
        attributeValueFocusLinearLayout.setLayoutParams(attributeValueFocusLinearLayoutparams);
        attributeValueFocusLinearLayout.setBackground(context.getResources().getDrawable(R.drawable.custom_border_black));
        attributeValueFocusLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        return attributeValueFocusLinearLayout;
    }


    @SuppressLint("NewApi")
    private LinearLayout createMotherLayout(Context context){
        LinearLayout mother = new LinearLayout(context);
        ScrollView.LayoutParams params = new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT);
        params.setMargins(UiUtils.sizeInDp(context,16), 0, UiUtils.sizeInDp(context,16), 0);
        mother.setLayoutParams(params);
        mother.setOrientation(LinearLayout.VERTICAL);
        return mother;
    }

    class CustomClickAttributeHelpListener implements View.OnClickListener {

        private Attribute attribute;

        public CustomClickAttributeHelpListener(Attribute attribute){
            this.attribute = attribute;
        }

        @Override
        public void onClick(View v) {
            Dialog dialog = DialogManager.createCustomDialog(context,inflater, attribute.getName(), attribute.getDescription());
            dialog.show();
        }
    }

    class CustomClickFocusHelpListener implements View.OnClickListener {

        private Focus focus;

        public CustomClickFocusHelpListener(Focus focus){
            this.focus = focus;
        }

        @Override
        public void onClick(View v) {
            Dialog dialog = DialogManager.createCustomDialog(context,inflater, focus.getName(), focus.getDescription());
            dialog.show();
        }
    }




}