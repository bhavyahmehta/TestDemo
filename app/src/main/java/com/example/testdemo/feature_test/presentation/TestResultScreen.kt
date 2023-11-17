package com.example.testdemo.feature_test.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testdemo.R
import com.example.testdemo.core.composables.CenterAlignedTestTopAppBar
import com.example.testdemo.core.utility.ShowAlertDialog
import com.example.testdemo.feature_test.domain.model.Option
import com.example.testdemo.feature_test.domain.model.Question
import com.example.testdemo.feature_test.presentation.test.TestViewModel

// This screen shows result of test with selected options
@Composable
fun TestResultScreen(testViewModel: TestViewModel, onClickBack: () -> Boolean) {

    val exitResultAlert = rememberSaveable { mutableStateOf(false) }
    val result: Map<Question, Option> = testViewModel.result

    BackHandler {
        exitResultAlert.value = true
    }

    Scaffold(
        topBar = {
            CenterAlignedTestTopAppBar(
                title = stringResource(id = R.string.result)
            )
        },
    ) { innerPadding ->
        Box {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                val questions = result.keys.toList()
                val answers = result.values.toList()
                LazyColumn {
                    items(questions.size) { index: Int ->
                        ResultItem(questions[index], answers[index])
                    }
                }
            }
        }

        if (exitResultAlert.value) {
            ShowExitResultAlert(exitResultAlert, onClickBack)
        }
    }
}

@Composable
fun ResultItem(question: Question, option: Option) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
        Text(
            text = question.question,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp),
            text = option.option,
            fontSize = 16.sp
        )
        Divider(thickness = 1.dp)
    }
}

@Composable
private fun ShowExitResultAlert(
    exitResultAlert: MutableState<Boolean>,
    showStartTestScreen: () -> Boolean,
) {
    ShowAlertDialog(title = null,
        description = stringResource(id = R.string.exit_result_alert),
        confirmButtonText = null,
        onConfirmButtonClick = {},
        dismissButtonText = stringResource(id = R.string.ok),
        onDismissButtonClick = {
            exitResultAlert.value = false
            showStartTestScreen()
        },
        onDismissDialog = {
            exitResultAlert.value = false
        })
}