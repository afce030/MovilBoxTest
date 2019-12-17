package com.movilbox.mbprobe.model.persistence.methods

import androidx.lifecycle.LiveData
import androidx.room.*
import com.movilbox.mbprobe.model.persistence.entities.ProspectsEntity

@Dao
interface ProspectsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProspects(vararg prospects: ProspectsEntity)

    @Query("DELETE FROM prospects_table")
    suspend fun deleteProspects()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProspects(vararg prospects: ProspectsEntity)

    @Query("SELECT * FROM prospects_table")
    fun getProspects() : LiveData<List<ProspectsEntity>>

    @Query("SELECT * FROM prospects_table WHERE id_card IN (:ids)")
    fun getProspectsByIdCard(vararg ids: Long) : LiveData<List<ProspectsEntity>>

}