package com.example.wouldyourather.ui.test

import com.example.wouldyourather.data.TOTAL_QUESTIONS
import com.example.wouldyourather.data.questions
import com.example.wouldyourather.decrementCurrentQuestionCount
import com.example.wouldyourather.incrementCurrentQuestionCount
import com.example.wouldyourather.ui.theme.viewmodel.WouldYouRatherViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class WouldYouRatherViewModelTest {
    // we can create a single of the view model and run the test
    // because all the tests that we run here will be working in isolation
    private val viewModel = WouldYouRatherViewModel()

    // success path -> when one of the option card is clicked, show the option text on both the cards

    @Test
    fun wouldYouRatherViewModel_OptionCardClicked_ShowedOptionTextOnBothCard() {
        var currentGameState = viewModel.uiState.value
        val expectedOptionOnCard1 = currentGameState.currentOption1.optionDescription
        val expectedOptionOnCard2 = currentGameState.currentOption2.optionDescription

        // when we click the option card it calls the show option description function
        viewModel.showOptionDescription()
        currentGameState = viewModel.uiState.value

        assertEquals(expectedOptionOnCard1, currentGameState.currentOption1Text)
        assertEquals(expectedOptionOnCard2, currentGameState.currentOption2Text)
    }


    @Test
    fun wouldYouRatherViewModel_OnNextButtonClicked_UpdatedQuestionAndOptionCardText() {
        var currentGameState = viewModel.uiState.value
        val incrementedQuestionCount =
            incrementCurrentQuestionCount(currentGameState.currentQuestionIndex)
        val expectedQuestion = questions[incrementedQuestionCount].question
        val expectedOption1Text = questions[incrementedQuestionCount].options.first.optionText
        val expectedOption2Text = questions[incrementedQuestionCount].options.second.optionText
        viewModel.updateGameState(incrementedQuestionCount)
        currentGameState = viewModel.uiState.value
        assertEquals(expectedQuestion, currentGameState.currentQuestion)
        assertEquals(expectedOption1Text, currentGameState.currentOption1Text)
        assertEquals(expectedOption2Text, currentGameState.currentOption2Text)
    }

    @Test
    fun wouldYouRatherViewModel_OnPreviousButtonClicked_UpdatedQuestionAndOptionCardText() {
        var currentGameState = viewModel.uiState.value
        val incrementedCount = incrementCurrentQuestionCount(currentGameState.currentQuestionIndex)

        viewModel.updateGameState(incrementedCount)
        currentGameState = viewModel.uiState.value

        val decrementedQuestionCount =
            decrementCurrentQuestionCount(currentGameState.currentQuestionIndex)

        val expectedQuestion = questions[decrementedQuestionCount].question
        val expectedOption1Text = questions[decrementedQuestionCount].options.first.optionText
        val expectedOption2Text = questions[decrementedQuestionCount].options.second.optionText
        viewModel.updateGameState(decrementedQuestionCount)
        currentGameState = viewModel.uiState.value
        assertEquals(expectedQuestion, currentGameState.currentQuestion)
        assertEquals(expectedOption1Text, currentGameState.currentOption1Text)
        assertEquals(expectedOption2Text, currentGameState.currentOption2Text)
    }

    @Test
    fun wouldYouRatherViewModel_OnShowOptionButtonClicked_ShowedTheOptionsTextAgain() {
        var currentGameState = viewModel.uiState.value
        val expectedTextOnOptionCard1 = currentGameState.currentOption1.optionText
        val expectedTextOnOptionCard2 = currentGameState.currentOption2.optionText

        // when we click the option card it calls the show option description function
        viewModel.showOptionDescription()
        viewModel.showOptionTextAgain()
        currentGameState = viewModel.uiState.value
        assertEquals(expectedTextOnOptionCard1, currentGameState.currentOption1Text)
        assertEquals(expectedTextOnOptionCard2, currentGameState.currentOption2Text)


    }


    // there is no error path i think
    // cause what will you even test
    // but there is boundary case to test
    /// boundary test case 1
    @Test
    fun wouldYouRatherViewModel_Initialization_FirstQuestionAndOptionsLoaded() {
        val currentGameState = viewModel.uiState.value
        val expectedQuestion = questions[currentGameState.currentQuestionIndex].question
        val expectedOption1Text =
            questions[currentGameState.currentQuestionIndex].options.first.optionText
        val expectedOption2Text =
            questions[currentGameState.currentQuestionIndex].options.second.optionText
        assertEquals(expectedOption1Text, currentGameState.currentOption1Text)
        assertEquals(expectedOption2Text, currentGameState.currentOption2Text)
        assertEquals(expectedQuestion, currentGameState.currentQuestion)
    }

    // boundary test case 2
    @Test
    fun wouldYouRatherViewModel_AllOptionCardsSelected_UiStateUpdatedCorrectly() {
        var currentGameState = viewModel.uiState.value // 0
        viewModel.updateGameState(currentGameState.currentQuestionIndex)
        currentGameState = viewModel.uiState.value
        repeat(TOTAL_QUESTIONS) {
            val incrementedQuestionCount =
                incrementCurrentQuestionCount(currentGameState.currentQuestionIndex)
            viewModel.updateGameState(incrementedQuestionCount)
            currentGameState = viewModel.uiState.value
        }
        assertEquals(TOTAL_QUESTIONS, currentGameState.currentQuestionIndex + 1)
        assertTrue(currentGameState.isGameOver)
    }


}