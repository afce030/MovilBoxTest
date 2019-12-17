package com.movilbox.mbprobe.model.retrofit.dto.prospectsResponse

import com.google.gson.annotations.SerializedName

data class ProspectsResponse(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("cityCode")
	val cityCode: String? = null,

	@field:SerializedName("roleId")
	val roleId: Int? = null,

	@field:SerializedName("observation")
	val observation: String? = null,

	@field:SerializedName("sectionCode")
	val sectionCode: String? = null,

	@field:SerializedName("telephone")
	val telephone: String? = null,

	@field:SerializedName("statusCd")
	val statusCd: Int? = null,

	@field:SerializedName("acceptSearch")
	val acceptSearch: Boolean? = null,

	@field:SerializedName("userId")
	val userId: Any? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("appointableId")
	val appointableId: Any? = null,

	@field:SerializedName("schProspectIdentification")
	val schProspectIdentification: String? = null,

	@field:SerializedName("neighborhoodCode")
	val neighborhoodCode: String? = null,

	@field:SerializedName("callcenter")
	val callcenter: Boolean? = null,

	@field:SerializedName("surname")
	val surname: String? = null,

	@field:SerializedName("disable")
	val disable: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("visited")
	val visited: Boolean? = null,

	@field:SerializedName("rejectedObservation")
	val rejectedObservation: Any? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("zoneCode")
	val zoneCode: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("campaignCode")
	val campaignCode: String? = null

)