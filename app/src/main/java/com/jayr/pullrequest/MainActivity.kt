package com.jayr.pullrequest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jayr.pullrequest.domain.models.BottomNavBarItem
import com.jayr.pullrequest.ui.components.Navigation
import com.jayr.pullrequest.ui.components.Routes
import com.jayr.pullrequest.ui.theme.PullRequestTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashscreen = installSplashScreen()
        var keepSplashScreen = true
        super.onCreate(savedInstanceState)
        splashscreen.setKeepOnScreenCondition { keepSplashScreen }
        lifecycleScope.launch {
            delay(5000)
            keepSplashScreen = false
        }
        enableEdgeToEdge()
        setContent {
            PullRequestTheme {
                val navController = rememberNavController()
                val selectedItem = remember { mutableIntStateOf(0) }
                Scaffold(
                    modifier = Modifier.fillMaxSize(), bottomBar = {
                       BottomNavigation {
                            val navBackStackEntry = navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry.value?.destination

                            val bottomNavItems = listOf<BottomNavBarItem>(
                                BottomNavBarItem(
                                    route = Routes.Contributions.name,
                                    icon = R.drawable.projects,
                                    iconSelected = false,
                                    iconNotSelected = true
                                ), BottomNavBarItem(
                                    route = Routes.Organizations.name,
                                    icon = R.drawable.organization,
                                    iconSelected = false,
                                    iconNotSelected = true
                                ), BottomNavBarItem(
                                    route = Routes.Users.name,
                                    icon = R.drawable.community,
                                    iconSelected = false,
                                    iconNotSelected = true
                                )
                            )
                            bottomNavItems.forEachIndexed { index, route ->
                                BottomNavigationItem(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    icon = {
                                        Icon(
                                            painter = painterResource(route.icon),
                                            contentDescription = "${route.route} button",
                                            tint = if (selectedItem.intValue == index) {
                                                Color.Cyan
                                            } else {
                                                Color.White
                                            }
                                        )
                                    },
                                    label = {
                                        if (selectedItem.intValue == index) {
                                            Text(
                                                route.route,
                                                fontSize = 12.sp,
                                                color = Color.White,
                                                modifier = Modifier.padding(vertical = 2.dp)
                                            )
                                        }
                                    },
                                    selected = currentDestination?.hierarchy?.any { it.route == route.route } == true,
                                    onClick = {
                                        selectedItem.intValue = index
                                        route.iconSelected = !route.iconSelected
                                        navController.navigate(route.route) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // re-selecting the same item
                                            launchSingleTop = true
                                            // Restore state when re-selecting a previously selected item
                                            restoreState = true
                                        }
                                    })


                            }
                        }
                    }

                ) { innerPadding ->
                    Navigation(
                        innerPadding = innerPadding, navController = navController
                    )
                 }
            }
        }
    }
}
/*
* API CALL:
*  - Client ( is the individual requesting from for data from the DB
*  - The DB  + API are located in the server
*  - the api gives out the data inform of a JSON object ( kotlin is unable to read this)
*  - we need a converter to parse the json response
* -  logging api calls from the api/ server ( debugging purposes)
* */

/*
* To achieve the above:
*  - Retrofit ( the http client )
*  - Converter - Moshi**
*  - Intercepter - a logger anytime you make an api call i want to print it or rathe rlog it in the logcat
*
* */


/*
* DIRECTORIES:
*  - api: RetrofitClient => Object ( intialize the api itself..
*  - interface: List of mehtods ( GET...DELETE) ... all our CRUD operations
*  - models: data models
* */

















