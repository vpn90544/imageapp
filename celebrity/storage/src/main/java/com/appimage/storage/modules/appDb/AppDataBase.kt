package com.appimage.storage.modules.appDb

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appimage.storage.modules.dao.DaoDb
import com.appimage.storage.modules.dto.EntityDB

@Database(version = 1, entities = [EntityDB::class], exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getDao(): DaoDb

    companion object {
        private var db_instance: AppDataBase? = null
        fun getAppDataBase(context: Application): AppDataBase {
            if (db_instance ==null){
                db_instance = Room.databaseBuilder<AppDataBase>(
                    context , AppDataBase::class.java,"app_db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return db_instance!!
        }
    }
}