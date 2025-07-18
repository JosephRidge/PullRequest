package com.jayr.pullrequest.ui.screens.organization

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.jayr.pullrequest.domain.models.Organization
import com.jayr.pullrequest.ui.components.ContributionsCard
import com.jayr.pullrequest.ui.components.TextTitle

@Composable
fun ContributionsScreen(
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
