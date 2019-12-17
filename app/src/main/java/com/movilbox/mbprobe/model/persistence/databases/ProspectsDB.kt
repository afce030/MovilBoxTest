package com.movilbox.mbprobe.model.persistence.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.movilbox.mbprobe.model.persistence.entities.ProspectsEntity
import com.movilbox.mbprobe.model.persistence.methods.ProspectsDAO

@Database(entities = [ProspectsEntity::class], exportSchema = false, version = 1)
abstract class ProspectsDB : RoomDatabase() {

    abstract fun prospectsDAO(): ProspectsDAO

}