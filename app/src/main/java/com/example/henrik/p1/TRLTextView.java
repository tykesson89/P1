package com.example.henrik.p1;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Henrik on 2016-09-20.
 */
public class TRLTextView extends TextView {

    public TRLTextView(Context context) {
        super(context);
    }

    public TRLTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TRLTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
    }
}
