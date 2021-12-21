package com.example.profilecardlayouttheme

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun UserApplication(userProfiles: List<UserProfile> = userProfileList) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "user_list_lazy") {
        composable("user_list") {
            MainScreen(userProfiles)
        }
        composable("user_list_lazy") {
            UserListScreen(userProfiles, navController)
        }
        composable(
            route = "user_details/{userName}",
            arguments = listOf(navArgument("userName") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("userName")?.let {
                UserProfileDetailsScreen(it, navController)
            }
        }
    }
}