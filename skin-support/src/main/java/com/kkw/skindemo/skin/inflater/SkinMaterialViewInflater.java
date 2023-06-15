package com.kkw.skindemo.skin.inflater;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import com.kkw.skindemo.skin.widget.material.SkinMaterialAppBarLayout;
import com.kkw.skindemo.skin.widget.material.SkinMaterialBottomNavigationView;
import com.kkw.skindemo.skin.widget.material.SkinMaterialCollapsingToolbarLayout;
import com.kkw.skindemo.skin.widget.material.SkinMaterialCoordinatorLayout;
import com.kkw.skindemo.skin.widget.material.SkinMaterialFloatingActionButton;
import com.kkw.skindemo.skin.widget.material.SkinMaterialNavigationView;
import com.kkw.skindemo.skin.widget.material.SkinMaterialTabLayout;
import com.kkw.skindemo.skin.widget.material.SkinMaterialTextInputEditText;
import com.kkw.skindemo.skin.widget.material.SkinMaterialTextInputLayout;

/**
 * Created by ximsfei on 2017/1/13.
 */
public class SkinMaterialViewInflater implements SkinLayoutInflater {
    @Override
    public View createView(@NonNull Context context, final String name, @NonNull AttributeSet attrs) {
        if ("androidx.coordinatorlayout.widget.CoordinatorLayout".equals(name)) {
            return new SkinMaterialCoordinatorLayout(context, attrs);
        }
        if (!name.startsWith("com.google.android.material.")) {
            return null;
        }
        View view = null;
        switch (name) {
            case "com.google.android.material.appbar.AppBarLayout":
                view = new SkinMaterialAppBarLayout(context, attrs);
                break;
            case "com.google.android.material.tabs.TabLayout":
                view = new SkinMaterialTabLayout(context, attrs);
                break;
            case "com.google.android.material.textfield.TextInputLayout":
                view = new SkinMaterialTextInputLayout(context, attrs);
                break;
            case "com.google.android.material.textfield.TextInputEditText":
                view = new SkinMaterialTextInputEditText(context, attrs);
                break;
            case "com.google.android.material.navigation.NavigationView":
                view = new SkinMaterialNavigationView(context, attrs);
                break;
            case "com.google.android.material.floatingactionbutton.FloatingActionButton":
                view = new SkinMaterialFloatingActionButton(context, attrs);
                break;
            case "com.google.android.material.bottomnavigation.BottomNavigationView":
                view = new SkinMaterialBottomNavigationView(context, attrs);
                break;
            case "com.google.android.material.appbar.CollapsingToolbarLayout":
                view = new SkinMaterialCollapsingToolbarLayout(context, attrs);
                break;
            default:
                break;
        }
        return view;
    }
}
