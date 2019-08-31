package com.yehonatan.latestmovies.database

import android.content.Context

class RoomRepo {
    companion object{
        private var instance:RoomRepo? = null

        fun getInstance(context: Context): RoomRepo? {
            if (instance == null){
                RoomRepo()
            }
            return instance
        }
    }
}