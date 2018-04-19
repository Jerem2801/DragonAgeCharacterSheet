package com.galaxia.dragonagecharactersheet.element.attribute;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;


import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.ui.TableUiManager;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AttributeUiManager {

    private AttributeUiManager(){

    }

    public static void setTableUi(Context context, Map<Integer,Integer> rollMap,TableLayout rollTable){
        List<Integer> keys = Lists.newArrayList();
        keys.addAll(rollMap.keySet());
        Collections.sort(keys);

        rollTable.setStretchAllColumns(true);
        rollTable.setShrinkAllColumns(true);

        TableUiManager.createFirstLine(context,rollTable, R.string.result,R.string.value);

        int rowNumber = 1;

        for(Integer key : keys){
            Integer value = rollMap.get(key);

            String keyString = String.valueOf(key);
            String valueString = String.valueOf(value);
            TableRow tableRow = TableUiManager.createRowValue(context,rowNumber,keyString,valueString);
            rollTable.addView(tableRow);
            rowNumber++;
        }

    }
}
