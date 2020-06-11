package com.example.opeyemiabdulsalam.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Filter(
    @Json(name = "id") val id: Int,
    @Json(name = "start_year") val startYear: Int,
    @Json(name = "end_year" ) val endYear: Int,
    @Json(name = "gender") val gender: String,
    @Json(name = "countries") val countries: List<String>,
    @Json(name = "colors") val colors: List<String>
): Parcelable {

}