package com.example.basicofjetpackcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.basicofjetpackcompose.ui.theme.ComposeTheme

@Composable
fun AddingListStateViewModel(mainViewModel: MainViewModel) {

    val nameStateContent = mainViewModel.textFieldState.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingListViewModel(
            textFieldValue = nameStateContent.value.toString(),
            textFieldUpdate = { newName -> mainViewModel.textFieldState.value = newName })
    }

}

@Composable
fun GreetingListViewModel(
    textFieldValue: String,
    textFieldUpdate: (newName: String) -> Unit
) {

    TextField(value = textFieldValue, onValueChange = textFieldUpdate)

    Button(onClick = { }) {
        Text("Add New Name")
    }

}

@Composable
fun GreetingViewModel(name: String) {
    Text(
        text = name
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ComposeTheme {
        AddingListStateViewModel(mainViewModel = MainViewModel())
    }
}