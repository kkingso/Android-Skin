package com.kkw.skindemo.skin.wrapper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public interface SkinWrapper {
    Context wrapContext(Context context, View parent, AttributeSet attrs);
}
