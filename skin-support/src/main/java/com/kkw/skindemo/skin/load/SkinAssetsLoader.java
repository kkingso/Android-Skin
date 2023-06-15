package com.kkw.skindemo.skin.load;

import android.content.Context;

import com.kkw.skindemo.skin.SkinCompatManager;
import com.kkw.skindemo.skin.utils.SkinConstants;
import com.kkw.skindemo.skin.utils.SkinFileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SkinAssetsLoader extends SkinSDCardLoader {
    @Override
    protected String getSkinPath(Context context, String skinName) {
        return copySkinFromAssets(context, skinName);
    }

    @Override
    public String getTargetResourceEntryName(Context context, String skinName, int resId) {
        return null;
    }

    @Override
    public int getType() {
        return SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS;
    }

    private String copySkinFromAssets(Context context, String name) {
        String skinPath = new File(SkinFileUtils.getSkinDir(context), name).getAbsolutePath();
        try {
            InputStream is = context.getAssets().open(
                    SkinConstants.SKIN_DEPLOY_PATH + File.separator + name);
            OutputStream os = new FileOutputStream(skinPath);
            int byteCount;
            byte[] bytes = new byte[1024];
            while ((byteCount = is.read(bytes)) != -1) {
                os.write(bytes, 0, byteCount);
            }
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skinPath;
    }
}
