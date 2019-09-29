package com.example.daftarmenu.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import java.security.AccessControlContext
import java.time.Instant

@Database(entities = arrayOf(MenuMakananModel::class,MenuMinumanModel::class),version = 1)
abstract class MenuDB:RoomDatabase(){

    abstract fun menuDao() :MenuDao

    companion object{
        var Instant:MenuDB?=null
        fun  getInstance(context: Context){

        }

        fun destroyInstance(){
            INSTANT = null
        }
    }
}