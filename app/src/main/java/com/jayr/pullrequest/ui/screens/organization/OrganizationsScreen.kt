package com.jayr.pullrequest.ui.screens.organization

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jayr.pullrequest.ui.components.ContributionsCard
import com.jayr.pullrequest.ui.components.TextTitle

@Composable
fun OrganizationsScreen(
    organizationsViewmodel: OrganizationsViewmodel = viewModel(), modifier: Modifier
) {
    val organizations = organizationsViewmodel.organizations.collectAsState()

    val uriHandler = LocalUriHandler.current
    Column {
        TextTitle("${organizations.value.size} organizations")
         LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            itemsIndexed(organizations.value) { index, organization ->
                ContributionsCard(uriHandler, organization)
            }
        }
    }


}
