package com.dicoding.githubuserapp

import java.io.Serializable

data class UserItem (
    var username:String,
    var avatar: String
        ) : Serializable