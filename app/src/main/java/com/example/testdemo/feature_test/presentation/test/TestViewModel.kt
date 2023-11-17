package com.example.testdemo.feature_test.presentation.test

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testdemo.core.di.IoDispatcher
import com.example.testdemo.feature_test.domain.TestUseCases
import com.example.testdemo.feature_test.domain.model.Option
import com.example.testdemo.feature_test.domain.model.Question
import com.example.testdemo.feature_test.domain.model.QuestionWithOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val testUseCases: TestUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _state = mutableStateOf(TestStates())
    val state: State<TestStates> = _state
    private val _isLastQuestion = mutableStateOf(false)
    val isLastQuestion: State<Boolean> = _isLastQuestion
    private var getTestJob: Job? = null
    private var allTestData = listOf<QuestionWithOptions>()
    private var currentQuestionDisplayIndex = 0
    var result = mutableMapOf<Question, Option>()

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(
            error = exception.message,
            isLoading = false
        )
    }

    fun getInitialTestData() {
        resetToInitial()
        getTestJob = viewModelScope.launch(dispatcher + errorHandler) { // + errorHandler
            allTestData = testUseCases.getTestData()
            _state.value = _state.value.copy(
                displayData = getCurrentQuestionWithOptions(),
                isLoading = false
            )
        }
    }

    private fun resetToInitial() {
        _isLastQuestion.value = false
        currentQuestionDisplayIndex = 0
        getTestJob?.cancel()
        result = mutableMapOf()
    }

    private fun getCurrentQuestionWithOptions() =
        if (currentQuestionDisplayIndex < allTestData.size) allTestData[currentQuestionDisplayIndex] else null

    fun getNextTestData() {
        currentQuestionDisplayIndex++
        _isLastQuestion.value = false
        _state.value = _state.value.copy(
            displayData = getCurrentQuestionWithOptions(),
            isLoading = false
        )
        if (currentQuestionDisplayIndex == allTestData.size - 1) {
            _isLastQuestion.value = true
        }
    }

    fun saveSelectedOption(selectedOption: Option) {
        result[allTestData[currentQuestionDisplayIndex].question] = selectedOption
    }
}