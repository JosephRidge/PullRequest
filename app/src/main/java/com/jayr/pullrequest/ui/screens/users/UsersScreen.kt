package com.jayr.pullrequest.ui.screens.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jayr.pullrequest.domain.models.User
import com.jayr.pullrequest.ui.theme.badgeOrange

@Composable
fun UsersScreen(
userViewModel: UserViewModel = viewModel(),
modifier: Modifier
){
val users = userViewModel.users.collectAsState()
    val uriHandler = LocalUriHandler.current
    Column(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            itemsIndexed(users.value) {index, user->
              Row(
                  modifier = Modifier.fillMaxWidth() ,
                  verticalAlignment = Alignment.CenterVertically
              ) {
                  IconButton(
                      modifier = Modifier.padding(horizontal = 8.dp),

                              onClick = {
                          uriHandler.openUri(user.github_profile)
                      }
                  ) {
                      Icon(
                          imageVector = Icons.Outlined.AccountCircle,
                          tint = badgeOrange,
                          contentDescription = "Icon of person",
                          modifier = Modifier.size(150.dp).clip(CircleShape)
                      )
                  }
                  Column {   Spacer(modifier = Modifier.padding(vertical = 4.dp))
                      Text(
                          text=user.nickname,
                          fontSize = 18.sp,
                          fontWeight = FontWeight.Bold
                      )
                      Text(
                          text="${user.organisations.size} organizations",
                          fontSize = 12.sp,
                          fontWeight = FontWeight.Light
                      )
                      Text(
                          text="${user.pull_requests?.size} pull requests",
                          fontSize = 12.sp,
                          fontWeight = FontWeight.Light
                      )
                      Spacer(modifier = Modifier.padding(vertical = 4.dp)) }
              }
                HorizontalDivider(color = Color.Gray, thickness = 0.5.dp)



            }
        }
    }

}