package com.example.testdemo.feature_test.presentation.test

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testdemo.core.di.IoDispatcher
import com.example.testdemo.feature_test.domain.TestUseCases
import com.example.testdemo.feature_test.domain.model.Option
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
    private var getTestJob: Job? = null
    private var allTestData = listOf<QuestionWithOptions>()
    private var currentQuestionDisplayIndex = 0
    private var currentSelectedOption: Option? = null

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(
            error = exception.message,
            isLoading = false
        )
    }

    fun getInitialTestData() {
        getTestJob?.cancel()
        getTestJob = viewModelScope.launch(dispatcher + errorHandler) { // + errorHandler
            allTestData = testUseCases.getTestData()
            _state.value = _state.value.copy(
                displayData = getCurrentQuestionWithOptions(),
                isLoading = false
            )
        }
    }

    private fun getCurrentQuestionWithOptions() =
        if (currentQuestionDisplayIndex < allTestData.size) allTestData[currentQuestionDisplayIndex] else null

    fun getNextTestData() {
        if (currentSelectedOption != null) {
            currentQuestionDisplayIndex++
            _state.value = _state.value.copy(
                displayData = getCurrentQuestionWithOptions(),
                isLoading = false
            )
        } else {
            _state.value = _state.value.copy(
                displayData = getCurrentQuestionWithOptions(),
                isLoading = false,
                showOptionNotSelectedError = true
            )
        }
    }

    fun onOptionClicked(selectedOption: Option) {
        currentSelectedOption = selectedOption
    }

}