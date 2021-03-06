package com.example.jetpackcomposeexample.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.basicofjetpackcompose.ui.theme.ComposeAppTheme
import com.example.jetpackcomposeexample.domain.model.MealModel
import com.example.jetpackcomposeexample.presentation.ui.meals.MealsCategoriesViewModel


@Composable()
fun MealsCategoriesScreen(
    viewModel: MealsCategoriesViewModel? = null
) {

//    val scope = rememberCoroutineScope()
//
//    scope.launch {
//        viewModel?.fetchMeals()
//    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel?.fetchMeals()
    })

//    val meals = viewModel?.mealsState?.value
    val meals = viewModel?.mealStateFlow?.collectAsState()?.value

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        meals?.let { mealsList ->
            items(mealsList) { meal ->
                MealCategory(meal = meal)
            }
        }
    }

}

@Composable
fun MealCategory(meal: MealModel) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row {
            Image(
                painter = rememberImagePainter(meal.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)
            ) {
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAppTheme {
        MealsCategoriesScreen()
    }
}