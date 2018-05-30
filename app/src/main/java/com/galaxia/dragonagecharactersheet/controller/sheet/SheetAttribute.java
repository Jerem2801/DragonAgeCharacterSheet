package com.galaxia.dragonagecharactersheet.controller.sheet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
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
            focusView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
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
        focusView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        focusView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        focusView.setBackground(getResources().getDrawable(R.drawable.custom_border_focus));
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
        attributeNameView.setPadding(UiUtils.sizeInDp(context,4),  UiUtils.sizeInDp(context,4), UiUtils.sizeInDp(context,4),UiUtils.sizeInDp(context,4));
        attributeNameView.setLayoutParams(attributeNameParams);
        UiViewUtils.setAttributeName(attributeNameView);
        String attributeNameValue = AttributeManager.getAttribute(bean.getName()).getName();
        attributeNameView.setText(attributeNameValue);
        attributeNameView.setGravity(Gravity.CENTER);
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













    private LinearLayout createAttributeValueLayout(Context context,PlayerAttributeBean bean){
        //ATTRIBUTE AND VALUE
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,  UiUtils.sizeInDp(context,8), 0,0);
        linearLayout.setLayoutParams(params);
        linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        //TextView attributeNameView = createAttributeNameView(context, bean);
        //linearLayout.addView(attributeNameView);

        LinearLayout linearLayoutRight = createLinearLayoutForValueAndHelp(context,bean);
        linearLayout.addView(linearLayoutRight);


        return linearLayout;
    }

    @SuppressLint("NewApi")
    private FlexboxLayout createFocusLayout(Context context, PlayerAttributeBean bean) {
        /*LinearLayout focusLinear = new LinearLayout(context);
        LinearLayout.LayoutParams focusParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        focusParam.setMargins(0,  0, 0,UiUtils.sizeInDp(context,8));
        focusLinear.setLayoutParams(focusParam);
        focusLinear.setOrientation(LinearLayout.HORIZONTAL);*/

        FlexboxLayout flex = new FlexboxLayout(context);
        flex.setFlexWrap(FlexWrap.WRAP);
        LinearLayout.LayoutParams focusParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        focusParam.setMargins(0,  0, 0,UiUtils.sizeInDp(context,8));
        flex.setLayoutParams(focusParam);

        for(String focus : bean.getFocuss()){
            TextView focusView =  new TextView(context);
            LinearLayout.LayoutParams focusParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            focusParams.setMargins(UiUtils.sizeInDp(context,8),  0, 0,UiUtils.sizeInDp(context,8));
            focusView.setLayoutParams(focusParams);
            String name = FocusManager.getFocus(focus).getName();
            focusView.setText(name);
            focusView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
            focusView.setTextColor(context.getResources().getColor(R.color.colorAccent));
            focusView.setBackground(getResources().getDrawable(R.drawable.custom_border_focus));
            flex.addView(focusView);
        }

        return flex;
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
        UiViewUtils.setAttributeName(valueView);
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



    @SuppressLint("NewApi")
    private LinearLayout createMotherLayout(Context context){
        LinearLayout mother = new LinearLayout(context);
        ScrollView.LayoutParams params = new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT);
        params.setMargins(UiUtils.sizeInDp(context,16), 0, UiUtils.sizeInDp(context,16), 0);
        mother.setLayoutParams(params);
        mother.setOrientation(LinearLayout.VERTICAL);
        //mother.setBackground(getResources().getDrawable(R.drawable.custom_border));
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

    private ScrollView createScrollView(Context context) {
        ScrollView scroll = new ScrollView(context);
        scroll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        return scroll;
    }


}