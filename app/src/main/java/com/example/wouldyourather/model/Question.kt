package com.example.wouldyourather.model


// this is a question class that holds the questions to be in the card
data class Question(
    val question: String,
    val options: Pair<Option, Option>

)

data class Option(
    val optionId: Int = 0,
    val optionText: String,
    val optionDescription: String
)
