package com.kkw.skindemo.skin.inflater;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.kkw.skindemo.skin.widget.SkinCompatConstraintLayout;

public class SkinConstraintViewInflater implements SkinLayoutInflater {
    @Override
    public View createView(Context context, final String name, AttributeSet attrs) {
        View view = null;
        switch (name) {
            case "androidx.constraintlayout.widget.ConstraintLayout":
                view = new SkinCompatConstraintLayout(context, attrs);
                break;
            default:
                break;
        }
        return view;
    }
}
