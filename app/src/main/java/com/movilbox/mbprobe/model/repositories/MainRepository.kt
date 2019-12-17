package com.movilbox.mbprobe.model.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.movilbox.mbprobe.model.persistence.entities.ProspectsEntity

interface MainRepository {

    //Retrofit
    fun requestProspectsInfo(token: String, page: Int)

    //Room
    fun insertProspectsRoom(prospectsEntity: List<ProspectsEntity>)

    fun updateProspectsRoom(
        prospectsEntity: List<ProspectsEntity>,
        changeStatus: MutableLiveData<Boolean>
    )

    fun deleteAllProspects()

    fun getAllProspectsFromRoom(): LiveData<List<ProspectsEntity>>
    fun getProspectsByIdCard(ids: List<Long>): LiveData<List<ProspectsEntity>>

}