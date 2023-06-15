package com.kkw.skindemo.skin;


import static com.kkw.skindemo.skin.widget.SkinCompatHelper.*;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;
import com.kkw.skindemo.skin.observe.*;
import com.kkw.skindemo.skin.wrapper.SkinCompatDelegate;
import com.kkw.skindemo.skin.res.*;

/**
 * Created by ximsfei on 17-1-8.
 */
public class SkinCompatActivity extends AppCompatActivity implements SkinObserver {

    private SkinCompatDelegate mSkinDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), getSkinDelegate());
        super.onCreate(savedInstanceState);
        updateStatusBarColor();
        updateWindowBackground();
    }

    @NonNull
    public SkinCompatDelegate getSkinDelegate() {
        if (mSkinDelegate == null) {
            mSkinDelegate = SkinCompatDelegate.create(this);
        }
        return mSkinDelegate;
    }

    @Override
    protected void onResume() {
        super.onResume();
        SkinCompatManager.getInstance().addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinCompatManager.getInstance().deleteObserver(this);
    }

    protected void updateStatusBarColor() {
    }

    protected void updateWindowBackground() {
        int windowBackgroundResId = SkinCompatThemeUtils.getWindowBackgroundResId(this);
        if (checkResourceId(windowBackgroundResId) != INVALID_ID) {
            Drawable drawable = SkinCompatVectorResources.getDrawableCompat(this, windowBackgroundResId);
            if (drawable != null) {
                getWindow().setBackgroundDrawable(drawable);
            }
        }
    }

    @Override
    public void updateSkin(SkinObservable observable, Object o) {
        updateStatusBarColor();
        updateWindowBackground();
        getSkinDelegate().applySkin();
    }
}
