package com.galaxia.dragonagecharactersheet.controller.create.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.talent.Talent;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;
import com.galaxia.dragonagecharactersheet.ui.UiViewUtils;

import org.w3c.dom.Text;

import java.util.List;

public class ChooseTalentManager {

    private ChooseTalentManager(){

    }

    public static void initialize(Context context, List<Talent> talents, LinearLayout mother){
        for(Talent talent : talents){
            LinearLayout layout = createLayout(context);

            TextView title = createTitle(context,talent);
            layout.addView(title);

            LinearLayout line = UiViewUtils.createLine(context);
            layout.addView(line);

            TextView desc = createDesc(context,talent);
            layout.addView(desc);

            mother.addView(layout);
        }
    }



    @SuppressLint("NewApi")
    private static LinearLayout createLayout(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, UiUtils.sizeInDp(context,16));
        linearLayout.setPadding(0,0,0,UiUtils.sizeInDp(context,16));
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackground(context.getResources().getDrawable(R.drawable.custom_border));

        return linearLayout;
    }

    private static TextView createTitle(Context context, Talent talent) {
        TextView title = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(UiUtils.sizeInDp(context,8), 0, 0, 0);
        title.setLayoutParams(params);
        UiViewUtils.setTextViewTitle(title);
        String talentName = talent.getName();
        title.setText(talentName);
        return title;

    }

    @SuppressLint("NewApi")
    private static TextView createDesc(Context context, Talent talent) {
        TextView desc = new TextView(context);
        LinearLayout.LayoutParams paramsAttributeDesc = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        desc.setLayoutParams(paramsAttributeDesc);
        desc.setText(talent.getDescription());
        desc.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        return desc;
    }
}
