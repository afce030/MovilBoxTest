package com.movilbox.mbprobe.model.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.movilbox.mbprobe.model.persistence.entities.ProspectsEntity
import com.movilbox.mbprobe.model.persistence.methods.ProspectsDAO
import com.movilbox.mbprobe.model.retrofit.dto.prospectsResponse.ProspectsResponse
import com.movilbox.mbprobe.model.retrofit.webServices.ProspectsWS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val prospectsWS: ProspectsWS,
    private val prospectsDAO: ProspectsDAO
): MainRepository{

    override fun requestProspectsInfo(token: String, page: Int) {
        val call = prospectsWS.getProspectsList(token, page)

        Log.d("mainApiRp", "ok1")

        call.enqueue(object : Callback<ArrayList<ProspectsResponse>>{
            override fun onFailure(call: Call<ArrayList<ProspectsResponse>>, t: Throwable) {
                Log.d("mainApiRp", t.cause.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<ProspectsResponse>>,
                response: Response<ArrayList<ProspectsResponse>>
            ) {

                when {
                    response.code() == 200 -> {
                        val data = response.body()
                        Log.d("mainApiRp", data?.get(0).toString())

                        val prospectEntities = mutableListOf<ProspectsEntity>()
                        data?.forEach { item ->

                            val entity = ProspectsEntity(
                                id = item.id?.toLong(),
                                name = item.name,
                                surname = item.surname,
                                id_card = item.schProspectIdentification?.toLong(),
                                phone = item.telephone?.toLong(),
                                statusCd = item.statusCd
                            )

                            prospectEntities.add(entity)

                        }

                        insertProspectsRoom(prospectEntities)

                    }

                    else -> {
                        Log.d("mainApiRp", response.raw().networkResponse.toString())
                    }
                }

            }

        })

    }

    override fun insertProspectsRoom(prospectsEntity: List<ProspectsEntity>) {

        CoroutineScope(IO).launch {
            prospectsDAO.insertProspects(*prospectsEntity.toTypedArray())
        }

    }

    override fun updateProspectsRoom(
        prospectsEntity: List<ProspectsEntity>,
        changeStatus: MutableLiveData<Boolean>
    ) {

        CoroutineScope(IO).launch {
            prospectsDAO.updateProspects(*prospectsEntity.toTypedArray())
            changeStatus.postValue(true)
        }

    }

    override fun deleteAllProspects() {

        CoroutineScope(IO).launch {
            prospectsDAO.deleteProspects()
        }

    }

    override fun getAllProspectsFromRoom(): LiveData<List<ProspectsEntity>> {
        return prospectsDAO.getProspects()
    }

    override fun getProspectsByIdCard(ids: List<Long>): LiveData<List<ProspectsEntity>> {
        return prospectsDAO.getProspectsByIdCard(*ids.toLongArray())
    }

}