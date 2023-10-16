package com.example.matakuliah.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MataKuliah(
    @StringRes val matkulRes: Int,
    @StringRes val sksRes : Int,
    @DrawableRes val imageRes: Int
)
