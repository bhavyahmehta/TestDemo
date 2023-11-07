package com.example.testdemo.test.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testdemo.test.domain.TestUseCases
import com.example.testdemo.test.domain.model.Option
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(private val testUseCases: TestUseCases) : ViewModel() {

    val options = arrayListOf<Option>()

    fun getTestData(){
        viewModelScope.launch(Dispatchers.IO) {
            testUseCases.getTestData()
        }
    }

    fun onOptionClicked(selectedOption: Option) {
       // options.forEach { it.isSelected = false }
        //options.find { it.id == selectedOption.id }?.isSelected = true
    }

}