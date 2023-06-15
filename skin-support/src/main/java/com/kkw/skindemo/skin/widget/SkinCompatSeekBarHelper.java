package com.kkw.skindemo.skin.widget;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.SeekBar;

import com.kkw.skindemo.skin.res.SkinCompatVectorResources;

/**
 * Created by ximsfei on 17-1-21.
 */
public class SkinCompatSeekBarHelper extends SkinCompatProgressBarHelper {
    private final SeekBar mView;

    private int mThumbResId = INVALID_ID;

    public SkinCompatSeekBarHelper(SeekBar view) {
        super(view);
        mView = view;
    }

    @Override
    void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        super.loadFromAttributes(attrs, defStyleAttr);

        TypedArray a = mView.getContext().obtainStyledAttributes(attrs, androidx.appcompat.R.styleable.AppCompatSeekBar, defStyleAttr, 0);
        mThumbResId = a.getResourceId(androidx.appcompat.R.styleable.AppCompatSeekBar_android_thumb, INVALID_ID);
        a.recycle();

        applySkin();
    }

    @Override
    public void applySkin() {
        super.applySkin();
        mThumbResId = checkResourceId(mThumbResId);
        if (mThumbResId != INVALID_ID) {
            mView.setThumb(SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mThumbResId));
        }
    }
}
