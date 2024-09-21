package com.albertokato.finapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class FinancialEntry(
    val type: String,
    val description: String,
    val value: Double,
) : Parcelable
