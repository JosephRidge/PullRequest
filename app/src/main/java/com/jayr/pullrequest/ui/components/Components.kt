package com.jayr.pullrequest.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.jayr.pullrequest.domain.models.Organization
import com.jayr.pullrequest.domain.models.Project
import com.jayr.pullrequest.domain.models.User
import com.jayr.pullrequest.ui.theme.badgeOrange


@Composable
fun UserDetailsRow(
    uriHandler: UriHandler,
    user: User
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
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
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )
        }
        Column {
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                text = user.nickname,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${user.organisations.size} organizations",
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = "${user.pull_requests?.size} pull requests",
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
        }
    }
}
@Composable
fun ContributionsCard(
    uriHandler: UriHandler,
    organization: Organization
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

@Composable
fun TextTitle(title:String) {
    Text(
        text = title,
        color = Color.Black,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
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