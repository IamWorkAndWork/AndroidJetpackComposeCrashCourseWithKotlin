package com.example.profilecardlayouttheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.profilecardlayouttheme.ui.theme.JetpackComposeCrashCourseForAndroidWithKotlinTheme

class MainLazyListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCrashCourseForAndroidWithKotlinTheme() {
//                UserListScreen(navController = navController)
            }
        }
    }
}

@Composable
fun UserListScreen(
    userProfiles: List<UserProfile> = userProfileList,
    navController: NavHostController? = null
) {
    Scaffold(topBar = {
        AppBar(
            title = "User List Lazy",
            icon = Icons.Default.Home
        )
    }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyColumn() {
                items(userProfiles) { userProfile ->
                    ProfileCard(userProfile = userProfile) { userProfile ->
                        navController?.navigate("user_details/${userProfile.name}")
                    }
                }
            }
        }
    }

}

