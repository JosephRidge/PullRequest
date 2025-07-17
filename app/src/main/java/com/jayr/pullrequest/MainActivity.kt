package com.jayr.pullrequest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
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

















