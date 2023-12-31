package com.appimage.core.di.db.appDb

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appimage.core.di.BaseDb
import com.appimage.core.di.db.dao.DaoDb
import com.appimage.core.di.db.entity.EntityAllImagesDB
import com.appimage.core.di.db.entity.EntityLikeImagesDB

@Database(
    version = 1,
    entities = [
        EntityAllImagesDB::class, EntityLikeImagesDB::class
               ],
    exportSchema = false)
abstract class AppDataBase : RoomDatabase(),BaseDb {
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