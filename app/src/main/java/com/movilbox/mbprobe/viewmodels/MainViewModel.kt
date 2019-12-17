package com.movilbox.mbprobe.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movilbox.mbprobe.model.persistence.entities.ProspectsEntity
import com.movilbox.mbprobe.model.repositories.MainRepositoryImpl
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepositoryImpl: MainRepositoryImpl): ViewModel() {

    var prospectsArray : LiveData<List<ProspectsEntity>> = MutableLiveData()
    var changeStatus: MutableLiveData<Boolean> = MutableLiveData(false)

    var mediatorLiveData: MediatorLiveData<List<Any>> = MediatorLiveData()

    fun requestProspectsRetrofit(token: String, page: Int){
        mainRepositoryImpl.requestProspectsInfo(token, page)
    }

    fun requestProspectsByIdRoom(ids: List<Long>){

        prospectsArray = mainRepositoryImpl.getProspectsByIdCard(ids)

        mediatorLiveData.addSource(prospectsArray){
            mediatorLiveData.value = it
        }
    }

    fun requestAllProspectsRoom(){
        prospectsArray = mainRepositoryImpl.getAllProspectsFromRoom()
    }

    fun updateProspect(prospects: List<ProspectsEntity>){

        mediatorLiveData.addSource(changeStatus){
            mediatorLiveData.value = listOf(it)
        }

        mainRepositoryImpl.updateProspectsRoom(prospects, changeStatus)
    }

}