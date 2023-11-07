package com.example.testdemo.test.data

import com.example.testdemo.test.data.local.model.LocalOption
import com.example.testdemo.test.data.local.model.LocalQuestion
import com.example.testdemo.test.data.remote.model.RemoteQuestion
import com.example.testdemo.test.domain.model.Option
import com.example.testdemo.test.domain.model.Question

fun List<RemoteQuestion>.toLocalListFromRemote(): List<LocalQuestion> {
    return this.map { remoteQuestion ->
        LocalQuestion(
            id = remoteQuestion.id ?: "",
            question = remoteQuestion.question ?: "",
            options = remoteQuestion.options.map { remoteOption ->
                LocalOption(
                    id = remoteOption.id ?: "",
                    option = remoteOption.option ?: "",
                    questionId = remoteQuestion.id ?: ""
                )
            }
        )
    }
}

fun List<LocalQuestion>.toQuestionListFromLocal(): List<Question> {
    return this.map { localQuestion ->
        Question(
            id = localQuestion.id ?: "",
            question = localQuestion.question ?: "",
            options = localQuestion.options.map { localOption ->
                Option(
                    id = localOption.id ?: "",
                    option = localOption.option ?: "",
                )
            }
        )
    }
}