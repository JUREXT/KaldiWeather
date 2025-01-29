package com.programming.kaldiweather.ui.component

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.programming.kaldiweather.R
import com.programming.kaldiweather.ui.theme.KaldiWeatherTheme

@Composable
fun AutoCompleteTextField(
    modifier: Modifier = Modifier,
    suggestions: List<String>,
    @StringRes enterSuggestionRes: Int,
    @StringRes nothingEnteredRes: Int,
    onSelectedSuggestion: (String) -> Unit
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    var filteredSuggestions by remember { mutableStateOf(suggestions) }
    var showSuggestions by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            BasicTextField(
                value = textFieldValue,
                onValueChange = { newValue ->
                    if (newValue.text != textFieldValue.text) {
                        textFieldValue = newValue
                        // Show suggestions only when typing and input is not empty
                        showSuggestions = newValue.text.isNotEmpty()
                        // Update filtered suggestions based on current input
                        filteredSuggestions = if (newValue.text.isEmpty()) {
                            suggestions
                        } else {
                            suggestions.filter { it.contains(newValue.text, ignoreCase = true) }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(16.dp),
                textStyle = TextStyle(color = Color.White, fontSize = 22.sp),
                cursorBrush = SolidColor(Color.Blue),
                decorationBox = { innerTextField ->
                    if (textFieldValue.text.isEmpty()) {
                        Text(
                            text = stringResource(id = enterSuggestionRes),
                            style = TextStyle(color = Color.White, fontSize = 18.sp)
                        )
                    }
                    innerTextField()
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, RoundedCornerShape(8.dp))
                .background(Color.Black, shape = RoundedCornerShape(8.dp))
                .animateContentSize()
        ) {
            if (showSuggestions && filteredSuggestions.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    items(filteredSuggestions.count()) { index ->
                        val suggestion = filteredSuggestions[index]
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    textFieldValue = TextFieldValue(
                                        text = suggestion,
                                        selection = TextRange(suggestion.length)
                                    )
                                    showSuggestions = false

                                    keyboardController?.hide()
                                    onSelectedSuggestion(suggestion)
                                }
                                .padding(12.dp)
                        ) {
                            Text(
                                text = suggestion,
                                color = Color.White,
                                style = TextStyle(fontSize = 18.sp),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            } else {
                Text(
                    text = stringResource(id = nothingEnteredRes),
                    style = TextStyle(color = Color.Gray, fontSize = 16.sp),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AutoCompleteTextFieldPreview() {
    val suggestions = listOf("Celje", "Laško", "ljubljana", "Koper")

    KaldiWeatherTheme {
        AutoCompleteTextField(
            modifier = Modifier.fillMaxWidth(),
            suggestions = suggestions,
            enterSuggestionRes = R.string.enter_suggestion_city_text,
            nothingEnteredRes = R.string.suggestion_not_present,
            onSelectedSuggestion = {}
        )
    }
}