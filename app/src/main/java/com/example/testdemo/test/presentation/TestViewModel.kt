package com.example.testdemo.test.presentation

import androidx.lifecycle.ViewModel
import com.example.testdemo.test.domain.Option
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor():ViewModel() {

    val options = arrayListOf<Option>()
    init {
        repeat(4) { index ->
            options.add(Option(id = index, text = "Option $index"))
        }
    }

    fun onOptionClicked(selectedOption: Option) {
        options.forEach { it.isSelected = false }
        options.find { it.id == selectedOption.id }?.isSelected = true
    }

}