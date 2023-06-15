package com.kkw.skindemo.skin.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import com.kkw.skindemo.skin.res.*;

/**
 * Created by ximsfei on 2017/1/13.
 */

public class SkinCompatAutoCompleteTextView extends AppCompatAutoCompleteTextView implements SkinCompatSupportable {
    private static final int[] TINT_ATTRS = {
            android.R.attr.popupBackground
    };
    private int mDropDownBackgroundResId = SkinCompatHelper.INVALID_ID;
    private final SkinCompatTextHelper mTextHelper;
    private final SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinCompatAutoCompleteTextView(Context context) {
        this(context, null);
    }

    public SkinCompatAutoCompleteTextView(Context context, AttributeSet attrs) {
        this(context, attrs, androidx.appcompat.R.attr.autoCompleteTextViewStyle);
    }

    public SkinCompatAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, TINT_ATTRS, defStyleAttr, 0);
        if (a.hasValue(0)) {
            mDropDownBackgroundResId = a.getResourceId(0, SkinCompatHelper.INVALID_ID);
        }
        a.recycle();
        applyDropDownBackgroundResource();
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
        mTextHelper = SkinCompatTextHelper.create(this);
        mTextHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    @Override
    public void setDropDownBackgroundResource(@DrawableRes int resId) {
        super.setDropDownBackgroundResource(resId);
        mDropDownBackgroundResId = resId;
        applyDropDownBackgroundResource();
    }

    private void applyDropDownBackgroundResource() {
        mDropDownBackgroundResId = SkinCompatHelper.checkResourceId(mDropDownBackgroundResId);
        if (mDropDownBackgroundResId != SkinCompatHelper.INVALID_ID) {
            Drawable drawable = SkinCompatVectorResources.getDrawableCompat(getContext(), mDropDownBackgroundResId);
            if (drawable != null) {
                setDropDownBackgroundDrawable(drawable);
            }
        }
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resId) {
        super.setBackgroundResource(resId);
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.onSetBackgroundResource(resId);
        }
    }

    @Override
    public void setTextAppearance(int resId) {
        setTextAppearance(getContext(), resId);
    }

    @Override
    public void setTextAppearance(Context context, int resId) {
        super.setTextAppearance(context, resId);
        if (mTextHelper != null) {
            mTextHelper.onSetTextAppearance(context, resId);
        }
    }

    @Override
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(
            @DrawableRes int start, @DrawableRes int top, @DrawableRes int end, @DrawableRes int bottom) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        if (mTextHelper != null) {
            mTextHelper.onSetCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        }
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(
            @DrawableRes int left, @DrawableRes int top, @DrawableRes int right, @DrawableRes int bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        if (mTextHelper != null) {
            mTextHelper.onSetCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        }
    }

    @Override
    public void applySkin() {
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
        if (mTextHelper != null) {
            mTextHelper.applySkin();
        }
        applyDropDownBackgroundResource();
    }

}
