package com.example.user.wearemachungers.CustomFonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by user on 30/05/2019.
 */

public class OpenSansBoldTextView extends android.support.v7.widget.AppCompatTextView {

    public OpenSansBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public OpenSansBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OpenSansBoldTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/opensans_bold.ttf");
        setTypeface(tf, 1);
    }
}
