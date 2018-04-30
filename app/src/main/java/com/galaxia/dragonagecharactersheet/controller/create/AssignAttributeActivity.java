package com.galaxia.dragonagecharactersheet.controller.create;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
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
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.background.Background;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTable;
import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.element.classe.ClasseManager;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.galaxia.dragonagecharactersheet.player.PlayerManager;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;
import com.galaxia.dragonagecharactersheet.ui.UiViewUtils;
import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AssignAttributeActivity extends AppCompatActivity {

    private Player player;
    private int pointToAssign;
    private Map<String, Integer> baseAttribute;
    private Map<String, Integer> addAttribute;

    private LinearLayout attributesLayout;
    private Button nextActivity;
    private TextView numberAttributes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_attribute);

        Intent intent = getIntent();
        player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);
        baseAttribute = PlayerManager.getTotalAttribute(player);
        addAttribute = PlayerManager.initializeAttribute();

        attributesLayout = findViewById(R.id.assigne_attributes_linear);
        nextActivity = findViewById(R.id.next_to);
        numberAttributes = findViewById(R.id.assign_attribute_number);

        pointToAssign = 10;
        setNumber();

        initialize();
        initializeButton(player.getClasseId());
    }

    @SuppressLint("NewApi")
    private void initialize() {
        Context context = AssignAttributeActivity.this;
        List<String> attributeIds = new ArrayList<>(baseAttribute.keySet());
        Collections.sort(attributeIds);
        for (String attributeId : attributeIds) {
            Attribute attribute = AttributeManager.getAttribute(attributeId);

            LinearLayout linearLayout = createLinearLayout(context,false);

            TextView attributeName = createTextViewForAttributeName(context,attribute);
            linearLayout.addView(attributeName);

            Classe classe = ClasseManager.getClasse(player.getClasseId());
            List<String> primaryAttributesId = classe.getPrimaryAttributesId();
            boolean visible = primaryAttributesId.contains(attributeId);

            ImageView star = createImageViewForPrincipalAttribute(context,visible);
            linearLayout.addView(star);

            LinearLayout linearLayoutRight = createLinearLayout(context,true);

            ImageView imageDown = createImageMinusPlus(context,attribute);
            linearLayoutRight.addView(imageDown);

            TextView attributeValue = createTextViewForAttributeValue(context,attribute);
            linearLayoutRight.addView(attributeValue);

            ImageView imagePlus = createImageForPlus(context,attribute);
            linearLayoutRight.addView(imagePlus);

            linearLayout.addView(linearLayoutRight);

            attributesLayout.addView(linearLayout);

            LinearLayout line = UiViewUtils.createLine(context);
            attributesLayout.addView(line);

            TextView attributeDesc = createTextViewForAttributeDesc(context,attribute);
            attributesLayout.addView(attributeDesc);

        }
    }

    private TextView createTextViewForAttributeName(Context context, Attribute attribute) {
        TextView attributeName =  new TextView(context);
        LinearLayout.LayoutParams paramsAttributeName = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsAttributeName.setMargins(UiUtils.sizeInDp(context,8), 0, 0, 0);
        attributeName.setLayoutParams(paramsAttributeName);
        UiViewUtils.setTextViewTitle(attributeName);
        attributeName.setText(attribute.getName());
        return attributeName;
    }

    private ImageView createImageViewForPrincipalAttribute(Context context,boolean visible) {
        ImageView star = new ImageView(context);
        LinearLayout.LayoutParams paramsPrincipalAttribute = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        star.setLayoutParams(paramsPrincipalAttribute);
        star.setImageBitmap(RessourceUtils.getImage(context, RessourcePath.STAR_PATH));
        if(!visible){
            star.setVisibility(View.INVISIBLE);
        }
        return star;
    }


    private ImageView createImageMinusPlus(Context context,Attribute attribute) {
        ImageView minus = new ImageView(context);
        LinearLayout.LayoutParams paramsPlus = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        paramsPlus.setMargins(0, 0, UiUtils.sizeInDp(context,8), 0);
        minus.setLayoutParams(paramsPlus);
        minus.setImageBitmap(RessourceUtils.getImage(context, RessourcePath.MINUS_PATH));
        minus.setOnClickListener(new CustomClickMinusListener(attribute));
        if(addAttribute.get(attribute.getId()) == 0){
            minus.setVisibility(View.INVISIBLE);
        }
        return minus;
    }


    private TextView createTextViewForAttributeValue(Context context,Attribute attribute) {
        TextView attributeValue = new TextView(context);
        LinearLayout.LayoutParams paramsAttributeValue = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsAttributeValue.setMargins(0, 0, UiUtils.sizeInDp(context,8), 0);
        attributeValue.setLayoutParams(paramsAttributeValue);
        UiViewUtils.setTextViewTitle(attributeValue);
        String value = getValue(attribute);
        attributeValue.setText(value);
        attributeValue.setGravity(Gravity.RIGHT);
        return attributeValue;
    }


    private ImageView createImageForPlus(Context context,Attribute attribute) {
        ImageView plus = new ImageView(context);
        LinearLayout.LayoutParams paramsPlus = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        paramsPlus.setMargins(0, 0, UiUtils.sizeInDp(context,8), 0);
        plus.setLayoutParams(paramsPlus);
        plus.setImageBitmap(RessourceUtils.getImage(context, RessourcePath.PLUS_PATH));
        plus.setOnClickListener(new CustomClickPlusListener(attribute));
        if(addAttribute.get(attribute.getId()) >= 3 || pointToAssign == 0){
            plus.setVisibility(View.INVISIBLE);
        }
        return plus;
    }


    @SuppressLint("NewApi")
    private TextView createTextViewForAttributeDesc(Context context, Attribute attribute) {
        TextView attributeDesc = new TextView(context);
        LinearLayout.LayoutParams paramsAttributeDesc = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsAttributeDesc.setMargins(0, 0, 0, UiUtils.sizeInDp(context,16));
        attributeDesc.setLayoutParams(paramsAttributeDesc);
        attributeDesc.setText(attribute.getDescription());
        attributeDesc.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        return attributeDesc;
    }


    private LinearLayout createLinearLayout(Context context,boolean right){
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if(right){
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        }
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        if(right){
            linearLayout.setGravity(Gravity.RIGHT);
        }
        return linearLayout;
    }

    public void setNumber() {
        String text = "Nombre de points restant : " + pointToAssign;
        numberAttributes.setText(text);
    }


    class CustomClickPlusListener implements View.OnClickListener {

        private Attribute attribute;

        public CustomClickPlusListener(Attribute attribute){
            this.attribute = attribute;
        }

        @Override
        public void onClick(View v) {
            if(pointToAssign == 0){
                String text = "Vous n'avez plus de points";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(AssignAttributeActivity.this, text, duration);
                toast.show();
            }else{
                --pointToAssign;
                setNumber();
                int number = addAttribute.get(attribute.getId());
                addAttribute.put(attribute.getId(),++number);
                attributesLayout.removeAllViews();
                initialize();
            }
        }
    }

    class CustomClickMinusListener implements View.OnClickListener {

        private Attribute attribute;

        public CustomClickMinusListener(Attribute attribute){
            this.attribute = attribute;
        }

        @Override
        public void onClick(View v) {
            ++pointToAssign;
            setNumber();
            int number = addAttribute.get(attribute.getId());
            addAttribute.put(attribute.getId(),--number);
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
        if(pointToAssign != 0){
            String text = "Vous n'avez pas assigné tout vos points !";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(AssignAttributeActivity.this, text, duration);
            toast.show();
        }else{
            Intent assignAttributeActivity = new Intent(AssignAttributeActivity.this, ChooseTalentActivity.class);
            player.setAttributeIdsRoll(addAttribute);
            assignAttributeActivity.putExtra(ActivityConstant.EXTRA_PLAYER, player);
            startActivity(assignAttributeActivity);

        }

    }
}


