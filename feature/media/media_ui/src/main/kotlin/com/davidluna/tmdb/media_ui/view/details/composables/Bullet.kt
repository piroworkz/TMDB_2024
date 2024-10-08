package com.davidluna.tmdb.media_ui.view.details.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AnnotatedString.Builder.Bullet() {
    append(" \u2022 ")
}

@Composable
fun AnnotatedString.Builder.LabelStyle() {
    pushStyle(
        style = SpanStyle(
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Light
        )
    )
}