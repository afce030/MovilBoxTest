package com.movilbox.mbprobe.model.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prospects_table")
class ProspectsEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "surname")
    var surname: String? = null,

    @ColumnInfo(name = "id_card")
    var id_card: Long? = null,

    @ColumnInfo(name = "phone")
    var phone: Long? = null,

    @ColumnInfo(name = "statusCd")
    val statusCd: Int? = null

)