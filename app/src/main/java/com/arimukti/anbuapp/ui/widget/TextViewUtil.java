package com.arimukti.anbuapp.ui.widget;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

public class TextViewUtil {
    private static final String FONT_DIR = "font/";

    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    public static Typeface getTypeface(String fontname, Context context) {
        Typeface typeface = fontCache.get(fontname);

        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), FONT_DIR + fontname);
            } catch (Exception e) {
                return null;
            }

            fontCache.put(fontname, typeface);
        }

        return typeface;
    }
}
