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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
fun ContributionsScreen(
    organizationsViewmodel: OrganizationsViewmodel = viewModel(), modifier: Modifier
) {
    val organizations = organizationsViewmodel.organizations.collectAsState()

    val uriHandler = LocalUriHandler.current
    Column {
        Text(
            text = "${organizations.value.size} organizations",
            color = Color.Black,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(12.dp)
        )
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            itemsIndexed(organizations.value) { index, organization ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ), elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    ), modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .clickable(
                            onClick = {
                                uriHandler.openUri(organization.link)
                            }
                        )
                ) {

                    AsyncImage(
                        model = organization.avatar_url,
                        contentDescription = "laucher",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(64.dp)
                    )
                        Column {
                            Text(
                                text = "${organization.login} ",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            Text(
                                text = "${organization.users.size} contributers",
                                color = Color.Gray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }


                }
            }
        }
    }


}