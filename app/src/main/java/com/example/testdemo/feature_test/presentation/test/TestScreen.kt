package com.example.testdemo.feature_test.presentation.test

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testdemo.R
import com.example.testdemo.core.constants.ContentDescriptions
import com.example.testdemo.core.utility.ShowAlertDialog
import com.example.testdemo.feature_test.domain.model.Option
import com.example.testdemo.ui.theme.Pink80
import com.example.testdemo.ui.theme.Purple80
import com.example.testdemo.ui.theme.PurpleGrey40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(testViewModel: TestViewModel) {

    val state = testViewModel.state.value
    val selectedOption = rememberSaveable { mutableStateOf<Option?>(null) }
    val noOptionSelectedPopupShown = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        testViewModel.getInitialTestData()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.test),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        },
    ) { innerPadding ->
        Box {
            if (state.displayData != null) {
                val displayQuestionWithOptions = state.displayData
                val question = displayQuestionWithOptions.question

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
                                    testViewModel.onOptionClicked(selected)
                                })
                        }
                    }
                    Button(
                        onClick = {
                            if (selectedOption.value!=null) {
                                testViewModel.getNextTestData()
                            } else {
                                noOptionSelectedPopupShown.value = true
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.next).uppercase(),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }
            if (state.isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        Modifier.semantics {
                            this.contentDescription = ContentDescriptions.LOADING_INDICATOR
                        }
                    )
                }
            }
            if (state.error != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.error
                    )
                }
            }
            if (noOptionSelectedPopupShown.value){
                ShowAlertDialog( title = null,
                    description = stringResource(id = R.string.select_option),
                    confirmButtonText = null,
                    onConfirmButtonClick = {},
                    dismissButtonText = stringResource(id = R.string.ok),
                    onDismissButtonClick = {noOptionSelectedPopupShown.value = false},
                    onDismissDialog = { noOptionSelectedPopupShown.value = false})
            }
        }
    }
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
