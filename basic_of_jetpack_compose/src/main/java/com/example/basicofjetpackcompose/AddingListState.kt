package com.example.basicofjetpackcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.basicofjetpackcompose.ui.theme.ComposeTheme

@Composable
fun AddingListState() {

    val greetingListState = remember {
        mutableStateListOf<String>("John", "Rambo")
    }

    val newNameStateContent = remember {
        mutableStateOf("test")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingList(
            greetingListState, buttonClick = {
                greetingListState.add(newNameStateContent.value)
            }, textFieldValue = newNameStateContent.value,
            textFieldUpdate = { newName -> newNameStateContent.value = newName })
    }

}

@Composable
fun GreetingList(
    greetingListState: List<String>, buttonClick: () -> Unit,
    textFieldValue: String,
    textFieldUpdate: (newName: String) -> Unit
) {

    for (name in greetingListState) {
        Greeting(name = name)
    }

    val newNameStateContent = remember {
        mutableStateOf("")
    }
    TextField(value = textFieldValue, onValueChange = textFieldUpdate)

    Button(onClick = { buttonClick.invoke() }) {
        Text("Add New Name")
    }

}

@Composable
fun Greeting(name: String) {
    Text(
        text = name
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeTheme {
        AddingListState()
    }
}