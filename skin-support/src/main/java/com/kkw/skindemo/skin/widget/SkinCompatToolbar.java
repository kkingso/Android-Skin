package com.kkw.skindemo.skin.widget;

import static com.kkw.skindemo.skin.widget.SkinCompatHelper.INVALID_ID;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.kkw.skindemo.skin.R;
import com.kkw.skindemo.skin.res.SkinCompatResources;
import com.kkw.skindemo.skin.res.SkinCompatVectorResources;

/**
 * Created by ximsfei on 17-1-12.
 */

public class SkinCompatToolbar extends Toolbar implements SkinCompatSupportable {
    private int mTitleTextColorResId = INVALID_ID;
    private int mSubtitleTextColorResId = INVALID_ID;
    private int mNavigationIconResId = INVALID_ID;
    private SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinCompatToolbar(Context context) {
        this(context, null);
    }

    public SkinCompatToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, androidx.appcompat.R.attr.toolbarStyle);
    }

    @SuppressLint("CustomViewStyleable")
    public SkinCompatToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, androidx.appcompat.R.styleable.Toolbar, defStyleAttr, 0);
        mNavigationIconResId = a.getResourceId(androidx.appcompat.R.styleable.Toolbar_navigationIcon, INVALID_ID);

        int titleAp = a.getResourceId(androidx.appcompat.R.styleable.Toolbar_titleTextAppearance, INVALID_ID);
        int subtitleAp = a.getResourceId(androidx.appcompat.R.styleable.Toolbar_subtitleTextAppearance, INVALID_ID);
        a.recycle();
        if (titleAp != INVALID_ID) {
            a = context.obtainStyledAttributes(titleAp, R.styleable.SkinTextAppearance);
            mTitleTextColorResId = a.getResourceId(R.styleable.SkinTextAppearance_android_textColor, INVALID_ID);
            a.recycle();
        }
        if (subtitleAp != INVALID_ID) {
            a = context.obtainStyledAttributes(subtitleAp, R.styleable.SkinTextAppearance);
            mSubtitleTextColorResId = a.getResourceId(R.styleable.SkinTextAppearance_android_textColor, INVALID_ID);
            a.recycle();
        }
        a = context.obtainStyledAttributes(attrs, androidx.appcompat.R.styleable.Toolbar, defStyleAttr, 0);
        if (a.hasValue(androidx.appcompat.R.styleable.Toolbar_titleTextColor)) {
            mTitleTextColorResId = a.getResourceId(androidx.appcompat.R.styleable.Toolbar_titleTextColor, INVALID_ID);
        }
        if (a.hasValue(androidx.appcompat.R.styleable.Toolbar_subtitleTextColor)) {
            mSubtitleTextColorResId = a.getResourceId(androidx.appcompat.R.styleable.Toolbar_subtitleTextColor, INVALID_ID);
        }
        a.recycle();
        applyTitleTextColor();
        applySubtitleTextColor();
        applyNavigationIcon();
    }

    private void applyTitleTextColor() {
        mTitleTextColorResId = SkinCompatHelper.checkResourceId(mTitleTextColorResId);
        if (mTitleTextColorResId != INVALID_ID) {
            setTitleTextColor(SkinCompatResources.getColor(getContext(), mTitleTextColorResId));
        }
    }

    private void applySubtitleTextColor() {
        mSubtitleTextColorResId = SkinCompatHelper.checkResourceId(mSubtitleTextColorResId);
        if (mSubtitleTextColorResId != INVALID_ID) {
            setSubtitleTextColor(SkinCompatResources.getColor(getContext(), mSubtitleTextColorResId));
        }
    }

    private void applyNavigationIcon() {
        mNavigationIconResId = SkinCompatHelper.checkResourceId(mNavigationIconResId);
        if (mNavigationIconResId != INVALID_ID) {
            setNavigationIcon(SkinCompatVectorResources.getDrawableCompat(getContext(), mNavigationIconResId));
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
    public void setNavigationIcon(@DrawableRes int resId) {
        super.setNavigationIcon(resId);
        mNavigationIconResId = resId;
        applyNavigationIcon();
    }

    @Override
    public void applySkin() {
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
        applyTitleTextColor();
        applySubtitleTextColor();
        applyNavigationIcon();
    }

}
