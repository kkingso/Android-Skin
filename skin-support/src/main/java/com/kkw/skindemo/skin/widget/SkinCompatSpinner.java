package com.kkw.skindemo.skin.widget;

import static com.kkw.skindemo.skin.widget.SkinCompatHelper.INVALID_ID;
import static com.kkw.skindemo.skin.widget.SkinCompatHelper.checkResourceId;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatSpinner;
import com.kkw.skindemo.skin.R;

import com.kkw.skindemo.skin.res.SkinCompatVectorResources;

/**
 * Created by ximsfei on 17-1-21.
 */

public class SkinCompatSpinner extends AppCompatSpinner implements SkinCompatSupportable {
    private static final String TAG = SkinCompatSpinner.class.getSimpleName();

    private static final int[] ATTRS_ANDROID_SPINNERMODE = {android.R.attr.spinnerMode};

    private static final int MODE_DIALOG = 0;
    private static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;

    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mPopupBackgroundResId = INVALID_ID;

    public SkinCompatSpinner(Context context) {
        this(context, null);
    }

    public SkinCompatSpinner(Context context, int mode) {
        this(context, null, androidx.appcompat.R.attr.spinnerStyle, mode);
    }

    public SkinCompatSpinner(Context context, AttributeSet attrs) {
        this(context, attrs, androidx.appcompat.R.attr.spinnerStyle);
    }

    public SkinCompatSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, MODE_THEME);
    }

    public SkinCompatSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        this(context, attrs, defStyleAttr, mode, null);
    }

    public SkinCompatSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode, Resources.Theme popupTheme) {
        super(context, attrs, defStyleAttr, mode, popupTheme);
        TypedArray a = context.obtainStyledAttributes(attrs, androidx.appcompat.R.styleable.Spinner, defStyleAttr, 0);

        if (getPopupContext() != null) {
            if (mode == MODE_THEME) {
                if (Build.VERSION.SDK_INT >= 11) {
                    // If we're running on API v11+ we will try and read android:spinnerMode
                    TypedArray aa = null;
                    try {
                        aa = context.obtainStyledAttributes(attrs, ATTRS_ANDROID_SPINNERMODE,
                                defStyleAttr, 0);
                        if (aa.hasValue(0)) {
                            mode = aa.getInt(0, MODE_DIALOG);
                        }
                    } catch (Exception e) {
                        Log.i(TAG, "Could not read android:spinnerMode", e);
                    } finally {
                        if (aa != null) {
                            aa.recycle();
                        }
                    }
                } else {
                    // Else, we use a default mode of dropdown
                    mode = MODE_DROPDOWN;
                }
            }

            if (mode == MODE_DROPDOWN) {
                final TypedArray pa = getPopupContext().obtainStyledAttributes(attrs, androidx.appcompat.R.styleable.Spinner, defStyleAttr, 0);
                mPopupBackgroundResId = pa.getResourceId(androidx.appcompat.R.styleable.Spinner_android_popupBackground, INVALID_ID);
                pa.recycle();
            }
        }
        a.recycle();

        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    @Override
    public void setPopupBackgroundResource(@DrawableRes int resId) {
        super.setPopupBackgroundResource(resId);
        mPopupBackgroundResId = resId;
        applyPopupBackground();
    }

    private void applyPopupBackground() {
        mPopupBackgroundResId = checkResourceId(mPopupBackgroundResId);
        if (mPopupBackgroundResId != INVALID_ID) {
            setPopupBackgroundDrawable(SkinCompatVectorResources.getDrawableCompat(getContext(), mPopupBackgroundResId));
        }
    }

    @Override
    public void applySkin() {
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
        applyPopupBackground();
    }

}
