package com.worldsnas.sliderlibrary

import android.graphics.Color
import android.support.annotation.ColorInt

data class ItemInfo(val page: String = "", @ColorInt val colorInt: Int = Color.CYAN, val url : String = "")