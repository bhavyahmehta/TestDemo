package com.example.testdemo.feature_test.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Option(
    val id: String,
    val option: String,
):Parcelable