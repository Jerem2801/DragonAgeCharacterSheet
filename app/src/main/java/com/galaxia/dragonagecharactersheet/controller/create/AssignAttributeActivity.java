package com.galaxia.dragonagecharactersheet.controller.create;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.element.classe.ClasseManager;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.galaxia.dragonagecharactersheet.player.PlayerManager;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.galaxia.dragonagecharactersheet.ui.UiViewUtils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AssignAttributeActivity extends AppCompatActivity {

    private Player player;
    private CharSequence[] numbers;
    private Map<String, Integer> baseAttribute;
    private Map<String, Integer> addAttribute;

    private LinearLayout attributesLayout;
    private Button nextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_attribute);

        Intent intent = getIntent();
        player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);
        baseAttribute = PlayerManager.getTotalAttribute(player);
        addAttribute = PlayerManager.initializeAttribute();


        String numbersInOneString = intent.getStringExtra(RollAttributesActivity.ROLLS);
        numbers = numbersInOneString.split(",");


        attributesLayout = findViewById(R.id.assigne_attributes_linear);
        nextActivity = findViewById(R.id.next_to);

        initialize();
        initializeButton(player.getClasseId());
    }

    @SuppressLint("NewApi")
    private void initialize() {
        Context context = AssignAttributeActivity.this;
        for (String attributeId : baseAttribute.keySet()) {
            Attribute attribute = AttributeManager.getAttribute(attributeId);

            LinearLayout linearLayout = createLinearLayout(context,attribute);

            TextView attributeName = createTextViewForAttributeName(context,attribute);
            linearLayout.addView(attributeName);

            Classe classe = ClasseManager.getClasse(player.getClasseId());
            List<String> primaryAttributesId = classe.getPrimaryAttributesId();
            boolean visible =primaryAttributesId.contains(attributeId);
            ImageView star = createImageViewForPrincipalAttribute(context,visible);
            linearLayout.addView(star);


            TextView attributeValue = createTextViewForAttributeValue(context,attribute);
            linearLayout.addView(attributeValue);

            ImageView imageCancel = createImageForCancel(context,attribute);
            linearLayout.addView(imageCancel);

            attributesLayout.addView(linearLayout);

            LinearLayout line = UiViewUtils.createLine(context);
            attributesLayout.addView(line);

            TextView attributeDesc = createTextViewForAttributeDesc(context,attribute);
            attributesLayout.addView(attributeDesc);

        }
    }

    private TextView createTextViewForAttributeName(Context context, Attribute attribute) {
        TextView attributeName =  new TextView(context);
        LinearLayout.LayoutParams paramsAttributeName = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsAttributeName.weight = 1;
        paramsAttributeName.setMargins(sizeInDp(8), 0, 0, 0);
        attributeName.setLayoutParams(paramsAttributeName);
        UiViewUtils.setTextViewTitle(attributeName);
        attributeName.setText(attribute.getName());
        return attributeName;
    }

    private ImageView createImageViewForPrincipalAttribute(Context context,boolean visible) {
        ImageView star = new ImageView(context);
        LinearLayout.LayoutParams paramsAttributeDesc = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        paramsAttributeDesc.weight = 0.25f;
        star.setLayoutParams(paramsAttributeDesc);
        star.setImageBitmap(RessourceUtils.getImage(context, RessourcePath.STAR_PATH));
        if(!visible){
            star.setVisibility(View.INVISIBLE);
        }
        return star;
    }

    private TextView createTextViewForAttributeValue(Context context,Attribute attribute) {
        TextView attributeValue = new TextView(context);
        //LinearLayout.LayoutParams paramsAttributeValue = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams paramsAttributeValue = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsAttributeValue.weight = 0.50f;
        paramsAttributeValue.setMargins(0, 0, sizeInDp(8), 0);
        attributeValue.setLayoutParams(paramsAttributeValue);
        UiViewUtils.setTextViewTitle(attributeValue);
        String value = getValue(attribute);
        attributeValue.setText(value);
        attributeValue.setGravity(Gravity.RIGHT);
        return attributeValue;
    }


    private ImageView createImageForCancel(Context context, Attribute attribute) {
        ImageView cancel = new ImageView(context);
        LinearLayout.LayoutParams paramsCancel = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        paramsCancel.weight = 0.25f;
        cancel.setLayoutParams(paramsCancel);
        cancel.setImageBitmap(RessourceUtils.getImage(context, RessourcePath.CLEAR_PATH));

        if(addAttribute.get(attribute.getId())  == 0){
            cancel.setVisibility(View.INVISIBLE);
        }else{
            cancel.setOnClickListener(new CustomClickCancelListener(attribute));
        }

        return cancel;
    }


    @SuppressLint("NewApi")
    private TextView createTextViewForAttributeDesc(Context context, Attribute attribute) {
        TextView attributeDesc = new TextView(context);
        LinearLayout.LayoutParams paramsAttributeDesc = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsAttributeDesc.setMargins(0, 0, 0, sizeInDp(16));
        attributeDesc.setLayoutParams(paramsAttributeDesc);
        attributeDesc.setText(attribute.getDescription());
        attributeDesc.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        return attributeDesc;
    }


    private LinearLayout createLinearLayout(Context context,Attribute attribute){
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        if(addAttribute.get(attribute.getId()) == 0){
            linearLayout.setOnClickListener(new CustomClickListener(attribute));
        }
        return linearLayout;
    }

    class CustomClickListener implements View.OnClickListener {

        private Attribute attribute;

        public CustomClickListener(Attribute attribute){
            this.attribute = attribute;
        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(AssignAttributeActivity.this);

            TextView textView = new TextView(AssignAttributeActivity.this);
            LinearLayout.LayoutParams paramsAttributeName = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsAttributeName.setMargins(sizeInDp(8), 0, 0, 0);
            textView.setLayoutParams(paramsAttributeName);
            textView.setText(attribute.getName());
            UiViewUtils.setTextViewTitle(textView);

            builder.setCustomTitle(textView).setItems(numbers, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    String numberString = (String) numbers[which];
                    numberString = StringUtils.deleteWhitespace(numberString);
                    Integer number = Integer.parseInt(numberString);
                    addAttribute.put(attribute.getId(),number);
                    attributesLayout.removeAllViews();
                    initialize();
                    numbers = ArrayUtils.remove(numbers, which);
                }
            });


            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    class CustomClickCancelListener implements View.OnClickListener {

        private Attribute attribute;

        public CustomClickCancelListener(Attribute attribute){
            this.attribute = attribute;
        }

        @Override
        public void onClick(View v) {
            int number = addAttribute.get(attribute.getId());
            String numberString = String.valueOf(number);
            CharSequence numberChar = (CharSequence) numberString;
            numbers = ArrayUtils.addAll(numbers, numberChar);
            addAttribute.put(attribute.getId(),0);
            attributesLayout.removeAllViews();
            initialize();
        }
    }


    private String getValue(Attribute attribute) {
        Integer valueBase = baseAttribute.get(attribute.getId());
        Integer valueAdd = addAttribute.get(attribute.getId());
        Integer valueTotal = valueAdd + valueBase;
        return String.valueOf(valueTotal);
    }

    private Integer sizeInDp( int sizeInDp){
        float v = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInDp, AssignAttributeActivity.this.getResources().getDisplayMetrics());
        return (Integer) Math.round(v);
    }

    private void initializeButton(String classeId) {
        String name = AssignAttributeActivity.this.getString(R.string.next_to_talent);
        if(StringUtils.equals(classeId,"mage")){
            name = AssignAttributeActivity.this.getString(R.string.next_to_spell);
        }else if(StringUtils.equals(classeId,"warrior")){
            name = AssignAttributeActivity.this.getString(R.string.next_to_group_weapon);
        }
        nextActivity.setText(name);
    }

    public void nextAssign(View view){

    }
}


