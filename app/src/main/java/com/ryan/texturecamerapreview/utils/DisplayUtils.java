package com.ryan.texturecamerapreview.utils;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Author: Zack
 * Email:  newzzack@gmail.com
 * Date:   2020.04.01 10:31
 */
public class DisplayUtils {

    /**
     * dp转换px
     *
     * @param dp dp值
     * @return 转换后的px值
     */
    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * sp转换px
     *
     * @param sp sp值
     * @return 转换后的px值
     */
    public static float sp2px(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics());
    }
}
