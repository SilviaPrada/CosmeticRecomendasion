package com.dicoding.cosmeticrecomendations

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cosmetic(
    val name: String,
    val description: String,
    val photo: String,
    val price: String,
    val size: String,
    val benefit: String
) : Parcelable