package com.elshafee.androiden.shoppingitemlist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elshafee.androiden.shoppingitemlist.model.ShoppingItem


@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase :RoomDatabase() {

    abstract fun getShoppingDao():ShoppingDao


    companion object{
        @Volatile
        private var instace:ShoppingDatabase?=null
        private val LOCK = Any()

        operator fun invoke(context:Context) = instace?: synchronized(LOCK){
            instace?: createDatabase(context).also {
                instace = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java,
                "ShoppingDB.db"
            ).build()
    }

}