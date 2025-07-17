package com.jayr.pullrequest.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgeDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.jayr.pullrequest.domain.models.Project

@Composable
fun HomeScreen(
homeViewModel: HomeViewModel = viewModel(),
modifier: Modifier
){
    val projects = homeViewModel.projects.collectAsState()
    val isLoading = homeViewModel.isLoading.collectAsState()

    Column(modifier = modifier){
       if(isLoading.value == true){
           CircularProgressIndicator()
       }else{
           LazyVerticalGrid(columns = GridCells.Fixed(2)){
               itemsIndexed(projects.value) { index, project ->
                   Card(
                       modifier = Modifier.padding(12.dp)
                   ) {
                       Box(
                           contentAlignment = Alignment.TopEnd
                       ){
                           AsyncImage(
                               model = "https://images.pexels.com/photos/731217/pexels-photo-731217.jpeg",
                               contentDescription = null,
                               contentScale = ContentScale.Crop,
                           )
                           Badge(  containerColor  = Color.Black,

                           ){

                               Text(
                                   text = project.main_language,
                                   color = Color.Gray,
                                   fontSize = 12.sp,
                                   overflow = TextOverflow.Ellipsis
                               )
                           }
                       }
                       Text(
                           text = project.description,
                           maxLines = 2,
                           fontSize = 16.sp,
                           overflow = TextOverflow.Ellipsis
                       )


                   }
               }
           }
       }
    }

}