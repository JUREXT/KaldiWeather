package com.programming.kaldiweather.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.programming.kaldiweather.ui.theme.KaldiWeatherTheme

@Composable
fun SearchHistoryCard(
    modifier: Modifier = Modifier,
    element: String,
    onSelected: (String) -> Unit
) {
    Card(
        modifier = modifier
            .shadow(8.dp, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        border = BorderStroke(width = 2.dp, color = Color.DarkGray)
    ) {
        Text(
            text = element,
            modifier = Modifier
                .clickable(
                    role = Role.Button,
                    onClick = {
                        onSelected(element)
                    }
                )
                .wrapContentHeight()
                .padding(5.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchHistoryCardPreview() {
    KaldiWeatherTheme {
        SearchHistoryCard(
            modifier = Modifier.padding(5.dp),
            element = "Element",
            onSelected = {}
        )
    }
}