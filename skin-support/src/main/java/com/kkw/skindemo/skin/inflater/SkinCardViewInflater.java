package com.kkw.skindemo.skin.inflater;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import com.kkw.skindemo.skin.widget.SkinCompatCardView;

/**
 * Created by ximsf on 2017/3/5.
 */
public class SkinCardViewInflater implements SkinLayoutInflater {
    @Override
    public View createView(@NonNull Context context, final String name, @NonNull AttributeSet attrs) {
        View view = null;
        switch (name) {
            case "androidx.cardview.widget.CardView":
                view = new SkinCompatCardView(context, attrs);
                break;
            default:
                break;
        }
        return view;
    }
}
