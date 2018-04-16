package com.galaxia.dragonagecharactersheet.element.language;

import android.content.Context;
import android.text.TextUtils;

import com.galaxia.dragonagecharactersheet.R;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class LanguageUiManager {

    private LanguageUiManager(){

    }

    public static String getLanguages(Context context, List<Language> languages){
        StringBuilder builder = new StringBuilder();
        for (Language language : languages) {
            if (TextUtils.isEmpty(builder.toString())) {
                builder.append(language.getName());
            } else {
                builder.append(", " + language.getName());
            }
        }

        String languageList = builder.toString().trim();
        if(StringUtils.isBlank(languageList)){
            languageList = context.getString(R.string.empty);
        }

        return languageList;
    }
}
