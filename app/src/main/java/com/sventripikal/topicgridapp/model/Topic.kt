package com.sventripikal.topicgridapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// Topic data class
data class Topic(
    @StringRes val name: Int,
    val numberOfCourses: Int,
    @DrawableRes val image: Int
)
