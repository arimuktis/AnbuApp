package com.arimukti.anbuapp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.arimukti.anbuapp.R;

public class CustomButton extends AppCompatButton {
    private static final int REGULAR = 0;
    private static final int BOLD = 1;
    private static final int LIGHT = 2;

    public CustomButton(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init(context, null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init(context, attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomButton);
            int fontStyle = a.getInt(R.styleable.CustomButton_fontStyleCustomButton, REGULAR);

            Typeface typeface = TextViewUtil.getTypeface(getFontName(fontStyle), context);
            setTypeface(typeface);

            a.recycle();
        }
    }

    public String getFontName(int name) {
        switch (name) {
            case REGULAR:
            default:
                return "netflixsanslight.ttf";

            case BOLD:
                return "netflixsansmedium.ttf";

            case LIGHT:
                return "netflixsansthin.ttf";

        }
    }
}