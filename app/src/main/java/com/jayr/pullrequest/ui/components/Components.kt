package com.jayr.pullrequest.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil3.compose.AsyncImage
import com.jayr.pullrequest.R
import com.jayr.pullrequest.domain.models.Organization
import com.jayr.pullrequest.domain.models.Project
import com.jayr.pullrequest.domain.models.User
import com.jayr.pullrequest.ui.screens.home.HomeScreen
import com.jayr.pullrequest.ui.screens.organization.OrganizationsScreen
import com.jayr.pullrequest.ui.screens.users.UsersScreen
import com.jayr.pullrequest.ui.theme.Purple40
import com.jayr.pullrequest.ui.theme.Purple80
import com.jayr.pullrequest.ui.theme.badgeOrange


@Composable
fun Navigation(
    navController: NavHostController, innerPadding: PaddingValues
) {
    NavHost(
        navController, startDestination = Routes.Contributions.name, Modifier.padding(innerPadding)
    ) {
        composable(route = Routes.Contributions.name) { HomeScreen(modifier = Modifier) }
        composable(route = Routes.Organizations.name) { OrganizationsScreen(modifier = Modifier) }
        composable(route = Routes.Users.name) { UsersScreen(modifier = Modifier) }
    }
}

@Composable
fun UserDetailsRow(
    uriHandler: UriHandler, user: User
) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier.padding(horizontal = 8.dp), onClick = {
                uriHandler.openUri(user.github_profile)
            }) {
            AsyncImage(
                model = R.drawable.coding_person,
                contentDescription = "Icon of person",
                modifier = Modifier
                    .size(220.dp)
                    .clip(CircleShape)
            )
        }
        Column {
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                text = user.nickname, fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                color = Purple40
                )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.organization),
                    contentDescription = "Icon of Organization",
                    tint = Purple40,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                Text(
                    text = "${user.organisations.size} organizations",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.projects),
                    contentDescription = "Icon of projects",
                    tint = Purple40,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                Text(
                    text = "${user.pull_requests?.size} pull requests",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
        }
    }
}

@Composable
fun ContributionsCard(
    uriHandler: UriHandler, organization: Organization
) {
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
                })
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = organization.avatar_url,
                contentDescription = "laucher",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .padding(4.dp)
            )
            Column {
                Text(
                    text = "${organization.login} ",
                    color = Color.Black,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Text(
                    text = "${organization.users.size} contributers",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }

}

@Composable
fun TextTitle(title: String) {
    Text(
        text = title,
        color = Purple40,
        fontSize = 32.sp,
        fontWeight = FontWeight.Light,
        modifier = Modifier.padding(12.dp)
    )
}

@Composable
fun ProjectCard(project: Project) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ), modifier = Modifier.padding(12.dp)
    ) {
        Box(
            contentAlignment = Alignment.TopEnd
        ) {
            AsyncImage(
                model = "https://images.pexels.com/photos/731217/pexels-photo-731217.jpeg",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(100.dp)
            )
            Badge(
                containerColor = badgeOrange, modifier = Modifier.padding(8.dp)

            ) {
                Text(
                    text = project.main_language,
                    color = Color.Black,
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
        Text(
            text = project.description,
            maxLines = 2,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp)
        )
    }
}