package com.example.profilecardlayouttheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.profilecardlayouttheme.ui.theme.JetpackComposeCrashCourseForAndroidWithKotlinTheme
import com.example.profilecardlayouttheme.ui.theme.lightGreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCrashCourseForAndroidWithKotlinTheme {
                UserApplication()
            }
        }
    }
}

@Composable
fun MainScreen(userProfiles: List<UserProfile> = userProfileList) {
    Scaffold(topBar = {
        AppBar(
            title = "User List",
            icon = Icons.Default.Home
        )
    }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column() {
                for (profile in userProfiles) {
                    ProfileCard(userProfile = profile) { userProfile ->

                    }
                }
            }
        }
    }

}

@Composable
fun AppBar(
    title: String,
    icon: ImageVector,
    iconClickAction: (() -> Unit)? = null
) {
    TopAppBar(
        navigationIcon = {
            Icon(
                icon, contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable {
                        iconClickAction?.invoke()
                    }
            )
        },
        title = { Text(title) },

        )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileCard(userProfile: UserProfile, clickAction: (UserProfile) -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(
                align = Alignment.Top
            ),
        elevation = 8.dp,
        backgroundColor = Color.White,
        onClick = { clickAction.invoke(userProfile) }
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(userProfile.drawableId, userProfile.status)
            ProfileContent(userProfile.name, userProfile.status)
        }
    }
}

@Composable
fun ProfilePicture(drawableId: Int, onlineStatus: Boolean, imageSize: Dp = 72.dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = if (onlineStatus) MaterialTheme.colors.lightGreen else Color.Red
        ),
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp
    ) {
//        Image(
//            ImageVector.vectorResource(id = drawableId),
//            modifier = Modifier.size(imageSize),
//            contentDescription = "",
//            contentScale = ContentScale.Crop
//        )
        Image(
            painter = rememberImagePainter("https://lh3.googleusercontent.com/a-/AOh14Gjo6edzoX7RbKUgOI69hH4E-e53WZJ_xIk2JZg9=k-s64"),
            contentDescription = null,
            modifier = Modifier.size(imageSize)
        )
    }
}

@Composable
fun ProfileContent(name: String, onlineStatus: Boolean, alignment: Alignment.Horizontal = Alignment.Start) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
    ) {
        CompositionLocalProvider(
            LocalContentAlpha provides
                    if (onlineStatus) 1f else (ContentAlpha.medium)
        ) {
            Text(
                name,
                style = MaterialTheme.typography.h5
            )
        }
        CompositionLocalProvider(LocalContentAlpha provides (ContentAlpha.medium)) {
            Text(
                if (onlineStatus) "Active Now" else "Offline",
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UserListScreen(userProfiles = userProfileList)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    UserProfileDetailsScreen()
}