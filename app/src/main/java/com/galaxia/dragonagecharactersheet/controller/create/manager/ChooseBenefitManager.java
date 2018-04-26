package com.galaxia.dragonagecharactersheet.controller.create.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TableLayout;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTable;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTableUiManager;

import java.util.List;

public class ChooseBenefitManager {

    @SuppressLint("NewApi")
    public static BackgroundTableUiManager initializeBenefit(Context context, List<BackgroundTable> backgroundTables, TableLayout tableLayout,TextView numberUpgrade) {
        tableLayout.removeAllViews();

        BackgroundTableUiManager uiManager = new BackgroundTableUiManager(context,tableLayout,backgroundTables,numberUpgrade);
        uiManager.setTableUi();
        return uiManager;
    }

    public static boolean checkNotBenefitSelected(BackgroundTableUiManager uiManager) {
        List<BackgroundTable> selectedBackgroudBonus = uiManager.getSelectedBackgroudBonus();
        int i = 0;
        for(BackgroundTable selected : selectedBackgroudBonus){
            i += uiManager.getCost(selected);
        }
        return i != 3;
    }

}
