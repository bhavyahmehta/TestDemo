package com.example.testdemo.feature_test.presentation.test

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testdemo.core.di.IoDispatcher
import com.example.testdemo.feature_test.domain.TestUseCases
import com.example.testdemo.feature_test.domain.model.Option
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

    /*init {
        getTestData()
    }*/

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(
            error = exception.message,
            isLoading = false
        )
    }

    fun getTestData() {
        getTestJob?.cancel()
        getTestJob = viewModelScope.launch(dispatcher + errorHandler) { // + errorHandler
            _state.value = _state.value.copy(
                testData = testUseCases.getTestData(),
                isLoading = false
            )
        }
    }

    fun onOptionClicked(selectedOption: Option) {
        // options.forEach { it.isSelected = false }
        //options.find { it.id == selectedOption.id }?.isSelected = true
    }

}