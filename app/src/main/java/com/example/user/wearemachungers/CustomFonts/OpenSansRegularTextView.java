package com.example.user.wearemachungers.CustomFonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by user on 30/05/2019.
 */

public class OpenSansRegularTextView extends android.support.v7.widget.AppCompatTextView {

    public OpenSansRegularTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public OpenSansRegularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OpenSansRegularTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/opensans_bold.ttf");
        setTypeface(tf, 1);
    }
}
