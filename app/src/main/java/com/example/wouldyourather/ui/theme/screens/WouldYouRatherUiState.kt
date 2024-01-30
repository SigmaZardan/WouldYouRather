package com.example.wouldyourather.ui.theme.screens


import com.example.wouldyourather.data.questions
import com.example.wouldyourather.model.Option


data class WouldYouRatherUiState(
    val currentQuestionIndex: Int = 0,
    val currentQuestion: String = questions[currentQuestionIndex].question,
    val currentOption1: Option = questions[currentQuestionIndex].options.first,
    val currentOption2: Option = questions[currentQuestionIndex].options.second,
    val currentOption1Text: String = currentOption1.optionText,
    val currentOption2Text: String = currentOption2.optionText,
    val isGameOver: Boolean = false,
    val openDialog: Boolean = false

)
