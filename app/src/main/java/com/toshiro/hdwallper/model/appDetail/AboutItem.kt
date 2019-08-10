package com.toshiro.hdwallper.model.appDetail

import com.google.gson.annotations.SerializedName

data class AboutItem(

	@field:SerializedName("app_name")
	val appName: String? = null,

	@field:SerializedName("app_logo")
	val appLogo: String? = null,

	@field:SerializedName("app_contact")
	val appContact: String? = null,

	@field:SerializedName("app_privacy_policy")
	val appPrivacyPolicy: String? = null,

	@field:SerializedName("app_version")
	val appVersion: String? = null,

	@field:SerializedName("app_email")
	val appEmail: String? = null,

	@field:SerializedName("app_developed_by")
	val appDevelopedBy: String? = null,

	@field:SerializedName("app_author")
	val appAuthor: String? = null,

	@field:SerializedName("app_website")
	val appWebsite: String? = null,

	@field:SerializedName("app_description")
	val appDescription: String? = null
)