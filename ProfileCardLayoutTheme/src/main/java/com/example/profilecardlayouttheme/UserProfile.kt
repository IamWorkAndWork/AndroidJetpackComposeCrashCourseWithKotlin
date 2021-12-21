package com.example.profilecardlayouttheme

data class UserProfile constructor(
    val name: String,
    val status: Boolean,
    val drawableId: Int
)


val userProfileList = arrayListOf<UserProfile>(
    UserProfile(name = "John Rambo", status = true, drawableId = R.drawable.ic_baseline_account_circle),
    UserProfile(name = "John Wall", status = false, drawableId = R.drawable.ic_baseline_account_circle),
    UserProfile(name = "John Rambo2", status = true, drawableId = R.drawable.ic_baseline_account_circle),
    UserProfile(name = "John Wall2", status = false, drawableId = R.drawable.ic_baseline_account_circle),
    UserProfile(name = "John Rambo3", status = true, drawableId = R.drawable.ic_baseline_account_circle),
    UserProfile(name = "John Wall3", status = false, drawableId = R.drawable.ic_baseline_account_circle),
    UserProfile(name = "John Rambo4", status = true, drawableId = R.drawable.ic_baseline_account_circle),
    UserProfile(name = "John Wall4", status = false, drawableId = R.drawable.ic_baseline_account_circle),
    UserProfile(name = "John Rambo5", status = true, drawableId = R.drawable.ic_baseline_account_circle),
    UserProfile(name = "John Wall5", status = false, drawableId = R.drawable.ic_baseline_account_circle)
)