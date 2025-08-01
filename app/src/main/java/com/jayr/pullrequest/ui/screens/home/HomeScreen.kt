package com.jayr.pullrequest.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jayr.pullrequest.ui.components.ProjectCard
import com.jayr.pullrequest.ui.components.TextTitle

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val projects = homeViewModel.projects.collectAsState()
    val isLoading = homeViewModel.isLoading.collectAsState()

    Column(modifier = modifier) {
        if (isLoading.value != true) {
            TextTitle("${projects.value.size} Projects")
        }
        if (isLoading.value == true) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                itemsIndexed(projects.value) { index, project ->
                    ProjectCard(project)
                }
            }
        }
    }

}

