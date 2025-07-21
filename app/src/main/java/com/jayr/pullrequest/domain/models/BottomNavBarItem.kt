package com.jayr.pullrequest.domain.models

data class BottomNavBarItem(
    val route:String,
    val icon:Int,
    var iconSelected: Boolean,
    var iconNotSelected:Boolean
)
