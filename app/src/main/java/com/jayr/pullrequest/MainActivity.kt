package com.jayr.pullrequest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jayr.pullrequest.ui.components.Navigation
import com.jayr.pullrequest.ui.components.Routes
import com.jayr.pullrequest.ui.screens.users.UsersScreen
import com.jayr.pullrequest.ui.theme.PullRequestTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
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
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                      BottomNavigation {
                          val navBackStackEntry = navController.currentBackStackEntryAsState()
                          val currentDestination = navBackStackEntry.value?.destination
                          Routes.entries.forEach { route ->
                              /*BottomNavigationItem(
                                  icon = { Icon(painter= painterResource(R.drawable.engineering_laptop), contentDescription = route.name) },
                                  label = { Text(route.name) },
                                  selected = currentDestination?.hierarchy?  == true,
                                  //          selected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true,,
                                  onClick = {
                                      navController.navigate(route.route) {
                                          // Pop up to the start destination of the graph to
                                          // avoid building up a large stack of destinations
                                          // on the back stack as users select items
                                          popUpTo(navController.graph.findStartDestination().id) {
                                              saveState = true
                                          }
                                          // Avoid multiple copies of the same destination when
                                          // reselecting the same item
                                          launchSingleTop = true
                                          // Restore state when reselecting a previously selected item
                                          restoreState = true
                                      }
                                  }
                              )*/



                          }
                      }
                    }

                ) { innerPadding ->
                    Navigation(
                       innerPadding = innerPadding,
                        navController = navController
                    )
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PullRequestTheme {
        Greeting("Android")
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

















