package com.example.wouldyourather.model


data class Question(
    val question: String,
    val options: Pair<Option, Option>

)

data class Option(
    val optionId: Int = 0,
    val optionText: String,
    val optionDescription: String
)
