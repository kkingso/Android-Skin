package com.kkw.skindemo

import android.app.Application
import com.kkw.skindemo.skin.SkinCompatManager
import com.kkw.skindemo.skin.inflater.SkinAppCompatViewInflater
import com.kkw.skindemo.skin.inflater.SkinCardViewInflater
import com.kkw.skindemo.skin.inflater.SkinConstraintViewInflater
import com.kkw.skindemo.skin.inflater.SkinMaterialViewInflater

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SkinCompatManager.withoutActivity(this)
            .addInflater(SkinAppCompatViewInflater())
            .addInflater(SkinConstraintViewInflater())
            .addInflater(SkinCardViewInflater())
            .addInflater(SkinMaterialViewInflater())
            .setSkinStatusBarColorEnable(false)
            .setSkinWindowBackgroundEnable(false)
            .loadSkin()
    }
}