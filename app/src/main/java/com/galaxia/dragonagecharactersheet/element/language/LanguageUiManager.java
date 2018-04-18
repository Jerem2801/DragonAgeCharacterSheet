package com.galaxia.dragonagecharactersheet.element.language;

import android.content.Context;
import android.text.TextUtils;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.create.ResumeActivity;
import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

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

    public static String getLanguagesName(Context context,List<String> languageIds) {
        List<Language> languages = Lists.newArrayList();
        for(String languageId : languageIds){
            languages.add(LanguageManager.getLanguage(languageId));
        }
        return getLanguages(context,languages);
    }


}
