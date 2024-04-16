package com.davidluna.architectcoders2024.app.ui.screens.master

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidluna.architectcoders2024.app.ui.theme.ArchitectCoders2024Theme

@Composable
fun MoviesScreen() {

    LazyColumn {
        item {
            LazyHorizontalGrid(rows = GridCells.Adaptive(150.dp)) {

            }
        }
    }

}

@Preview
@Composable
private fun MoviesScreenPreview() {
    ArchitectCoders2024Theme {
        MoviesScreen()
    }
}