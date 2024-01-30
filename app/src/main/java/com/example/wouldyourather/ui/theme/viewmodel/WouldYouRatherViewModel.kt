package com.example.wouldyourather.ui.theme.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.example.wouldyourather.data.TOTAL_QUESTIONS
import com.example.wouldyourather.data.questions
import com.example.wouldyourather.ui.theme.screens.WouldYouRatherUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WouldYouRatherViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        WouldYouRatherUiState()
    )
    val uiState: StateFlow<WouldYouRatherUiState> = _uiState.asStateFlow()

    init {
        resetGame()
    }


    fun showOptionDescription() {
        _uiState.update { currentState ->
            currentState.copy(
                currentOption2Text = currentState.currentOption2.optionDescription,
                currentOption1Text = currentState.currentOption1.optionDescription
            )
        }
    }


    fun displayNextQuestion() {
        val incrementedCount =
            incrementCurrentQuestionCount(_uiState.value.currentQuestionIndex)
        updateGameState(count = incrementedCount)
    }

    private fun isLastRound(currentCount: Int) = currentCount == TOTAL_QUESTIONS

    @VisibleForTesting
    fun updateGameState(count: Int) {
        if (isLastRound(count)) {
            _uiState.update { currentState ->
                currentState.copy(isGameOver = true)
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    currentQuestionIndex = count,
                    currentQuestion = questions[count].question,
                    currentOption1 = questions[count].options.first,
                    currentOption2 = questions[count].options.second,
                    currentOption1Text = questions[count].options.first.optionText,
                    currentOption2Text = questions[count].options.second.optionText
                )
            }
        }

    }

    private fun incrementCurrentQuestionCount(count: Int) = count.inc()


    fun displayPrevQuestion() {
        if (hasPreviousQuestions(_uiState.value.currentQuestionIndex)) {
            val decrementedCount =
                decrementCurrentQuestionCount(_uiState.value.currentQuestionIndex)
            updateGameState(decrementedCount)
        }
    }

    private fun decrementCurrentQuestionCount(count: Int) = count.dec()

    private fun hasPreviousQuestions(currentIndex: Int) = currentIndex > 0

    fun showOptionTextAgain() {
        _uiState.update { currentState ->
            currentState.copy(
                currentOption2Text = currentState.currentOption2.optionText,
                currentOption1Text = currentState.currentOption1.optionText
            )
        }
    }

    fun resetGame() {
        _uiState.update { currentState ->
            currentState.copy(
                currentQuestionIndex = 0,
                currentQuestion = questions[0].question,
                currentOption1 = questions[0].options.first,
                currentOption2 = questions[0].options.second,
                currentOption1Text = questions[0].options.first.optionText,
                currentOption2Text = questions[0].options.second.optionText,
                isGameOver = false
            )
        }
    }
}
