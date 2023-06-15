package com.kkw.skindemo.skin.inflater;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

/**
 * Created by kaiwang on 2023/6/15.
 */
public interface SkinLayoutInflater {
    View createView(@NonNull Context context, final String name, @NonNull AttributeSet attrs);
}
