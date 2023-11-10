package com.example.testdemo.feature_test.data

import com.example.testdemo.feature_test.data.local.model.LocalQuestionWithOptions
import com.example.testdemo.feature_test.domain.model.Option
import com.example.testdemo.feature_test.domain.model.Question
import com.example.testdemo.feature_test.domain.model.QuestionWithOptions

fun List<LocalQuestionWithOptions>.toQuestionWithOptionsListFromLocal(): List<QuestionWithOptions> {
    return this.map { item ->
        QuestionWithOptions(
            question = item.toQuestionFromLocalQuestionWithOptions(),
            options = item.toOptionsFromLocalQuestionWithOptions()
        )
    }
}

fun LocalQuestionWithOptions.toQuestionFromLocalQuestionWithOptions():Question{
return Question(
    id = question.questionId,
    question = question.question)
}

fun LocalQuestionWithOptions.toOptionsFromLocalQuestionWithOptions():List<Option>{
    return options.map { Option(
        id = it.optionId,
        option = it.option
    ) }
}