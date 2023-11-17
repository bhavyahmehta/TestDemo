package com.example.testdemo.feature_test.presentation.test

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testdemo.R
import com.example.testdemo.core.composables.BigRoundedButton
import com.example.testdemo.core.composables.CenterAlignedTestTopAppBar
import com.example.testdemo.core.composables.ShowCircularLoading
import com.example.testdemo.core.utility.ShowAlertDialog
import com.example.testdemo.feature_test.domain.model.Option
import com.example.testdemo.feature_test.domain.model.Question
import com.example.testdemo.feature_test.domain.model.QuestionWithOptions
import com.example.testdemo.ui.theme.Pink80
import com.example.testdemo.ui.theme.Purple80
import com.example.testdemo.ui.theme.PurpleGrey40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(
    testViewModel: TestViewModel,
    onClickBack: () -> Boolean,
    onClickFinishTest: () -> Unit,
) {

    val state = testViewModel.state.value
    val isLastQuestion = testViewModel.isLastQuestion
    val selectedOption = rememberSaveable { mutableStateOf<Option?>(null) }
    val noOptionSelectedAlert = rememberSaveable { mutableStateOf(false) }
    val exitTestAlert = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        testViewModel.getInitialTestData()
    }

    BackHandler {
        exitTestAlert.value = true
    }

    Scaffold(
        topBar = {
            CenterAlignedTestTopAppBar(
                title = stringResource(id = R.string.test)
            )
        },
    ) { innerPadding ->
        Box {
            if (state.displayData != null) {
                val displayQuestionWithOptions = state.displayData
                val question = displayQuestionWithOptions.question
                ShowQuestionWithOptions(
                    innerPadding,
                    question,
                    displayQuestionWithOptions,
                    selectedOption,
                    testViewModel,
                    noOptionSelectedAlert,
                    isLastQuestion,
                    onClickFinishTest
                )
            }
            if (state.isLoading) {
                ShowCircularLoading()
            }
            if (state.error != null) {
                ShowError(state)
            }
            if (noOptionSelectedAlert.value) {
                ShowNoOptionSelectedAlert(noOptionSelectedAlert)
            }
            if (exitTestAlert.value) {
                ShowExitTestAlert(exitTestAlert, onClickBack)
            }
        }
    }
}

@Composable
private fun ShowQuestionWithOptions(
    innerPadding: PaddingValues,
    question: Question,
    displayQuestionWithOptions: QuestionWithOptions,
    selectedOption: MutableState<Option?>,
    testViewModel: TestViewModel,
    noOptionSelectedAlert: MutableState<Boolean>,
    isLastQuestion: State<Boolean>,
    showResultScreen: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            text = question.question,
            fontSize = 20.sp,
            color = PurpleGrey40
        )
        Spacer(modifier = Modifier.height(32.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(displayQuestionWithOptions.options) { option ->
                SingleSelectionCard(option,
                    selectedValue = selectedOption.value,
                    onSelectListener = { selected ->
                        selectedOption.value = selected
                    })
            }
        }
        NextQuestionButton(selectedOption, testViewModel, noOptionSelectedAlert, isLastQuestion,showResultScreen)
    }
}

@Composable
private fun NextQuestionButton(
    selectedOption: MutableState<Option?>,
    testViewModel: TestViewModel,
    noOptionSelectedAlert: MutableState<Boolean>,
    isLastQuestion: State<Boolean>,
    showResultScreen: () -> Unit,
) {
    BigRoundedButton(
        onClick = {
            if (selectedOption.value != null) {
                testViewModel.saveSelectedOption(selectedOption.value!!)
                if (isLastQuestion.value){
                    showResultScreen()
                }else{
                    testViewModel.getNextTestData()
                }
                noOptionSelectedAlert.value = false
                selectedOption.value = null

            } else {
                noOptionSelectedAlert.value = true
            }
        }, title = stringResource(
            id =
            if (isLastQuestion.value) {
                R.string.finish
            } else {
                R.string.next
            }
        ).uppercase()
    )
}

@Composable
private fun ShowError(state: TestStates) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = state.error ?: ""
        )
    }
}

@Composable
private fun ShowNoOptionSelectedAlert(noOptionSelectedAlert: MutableState<Boolean>) {
    ShowAlertDialog(title = null,
        description = stringResource(id = R.string.select_option),
        confirmButtonText = null,
        onConfirmButtonClick = {},
        dismissButtonText = stringResource(id = R.string.ok),
        onDismissButtonClick = { noOptionSelectedAlert.value = false },
        onDismissDialog = { noOptionSelectedAlert.value = false })
}

@Composable
private fun ShowExitTestAlert(
    exitTestAlert: MutableState<Boolean>,
    onClickBack: () -> Boolean,
) {
    ShowAlertDialog(title = null,
        description = stringResource(id = R.string.exit_test_alert),
        confirmButtonText = stringResource(id = R.string.yes),
        onConfirmButtonClick = {
            exitTestAlert.value = false
            onClickBack()
        },
        dismissButtonText = stringResource(id = R.string.no),
        onDismissButtonClick = {
            exitTestAlert.value = false
        },
        onDismissDialog = {
            exitTestAlert.value = false
        })
}

@Composable
fun SingleSelectionCard(
    option: Option,
    selectedValue: Option?,
    onSelectListener: (Option) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable(true, onClick = {
                onSelectListener(option)
            })
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(if (option.id == selectedValue?.id) Pink80 else Purple80)
                .padding(8.dp),
            text = option.option,
            fontSize = 18.sp,
            color = PurpleGrey40
        )
    }
}
