package com.kkw.skindemo

import android.os.Bundle
import android.view.View
import com.kkw.skindemo.databinding.ActivityMainBinding
import com.kkw.skindemo.skin.SkinCompatActivity
import com.kkw.skindemo.skin.SkinCompatManager
import com.kkw.skindemo.skin.utils.SkinPreference

class MainActivity : SkinCompatActivity() {
    lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    fun changeSkin(view: View) {
        if ("dark.skin" == SkinPreference.getInstance().skinName) {
            SkinCompatManager.getInstance().restoreDefaultTheme()
        } else {
            SkinCompatManager.getInstance().loadSkin("dark.skin", null, SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS)
        }
    }

//    override fun getDelegate(): AppCompatDelegate {
//        return SkinAppCompatDelegateImpl.get(this, this)
//    }
}