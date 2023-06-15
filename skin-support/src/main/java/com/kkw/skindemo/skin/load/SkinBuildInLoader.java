package com.kkw.skindemo.skin.load;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

import com.kkw.skindemo.skin.SkinCompatManager;
import com.kkw.skindemo.skin.res.SkinCompatResources;

public class SkinBuildInLoader implements SkinCompatManager.SkinLoaderStrategy {
    @Override
    public String loadSkinInBackground(Context context, String skinName) {
        SkinCompatResources.getInstance().setupSkin(
                context.getResources(),
                context.getPackageName(),
                skinName,
                this);
        return skinName;
    }

    @Override
    public String getTargetResourceEntryName(Context context, String skinName, int resId) {
        return context.getResources().getResourceEntryName(resId) + "_" + skinName;
    }

    @Override
    public ColorStateList getColor(Context context, String skinName, int resId) {
        return null;
    }

    @Override
    public ColorStateList getColorStateList(Context context, String skinName, int resId) {
        return null;
    }

    @Override
    public Drawable getDrawable(Context context, String skinName, int resId) {
        return null;
    }

    @Override
    public int getType() {
        return SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN;
    }
}
