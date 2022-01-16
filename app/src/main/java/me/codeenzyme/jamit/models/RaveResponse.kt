package me.codeenzyme.jamit.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RaveResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
): Parcelable {

    @Parcelize
    data class Data(
        @SerializedName("AccountId")
        val accountId: Int,
        @SerializedName("acctvalrespcode")
        val acctvalrespcode: String,
        @SerializedName("amount")
        val amount: Int,
        @SerializedName("appfee")
        val appfee: Double,
        @SerializedName("authModelUsed")
        val authModelUsed: String,
        @SerializedName("authurl")
        val authurl: String,
        @SerializedName("chargeResponseCode")
        val chargeResponseCode: String,
        @SerializedName("chargeResponseMessage")
        val chargeResponseMessage: String,
        @SerializedName("charge_type")
        val chargeType: String,
        @SerializedName("charged_amount")
        val chargedAmount: Int,
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("currency")
        val currency: String,
        @SerializedName("customer.AccountId")
        val customerAccountId: Int,
        @SerializedName("customer.createdAt")
        val customerCreatedAt: String,
        @SerializedName("customer.email")
        val customerEmail: String,
        @SerializedName("customer.fullName")
        val customerFullName: String,
        @SerializedName("customerId")
        val customerId: Int,
        @SerializedName("customer.id")
        val customerDotId: Int,
        @SerializedName("customer.updatedAt")
        val customerUpdatedAt: String,
        @SerializedName("cycle")
        val cycle: String,
        @SerializedName("device_fingerprint")
        val deviceFingerprint: String,
        @SerializedName("flwMeta")
        val flwMeta: FlwMeta,
        @SerializedName("flwRef")
        val flwRef: String,
        @SerializedName("fraud_status")
        val fraudStatus: String,
        @SerializedName("IP")
        val iP: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("is_live")
        val isLive: Int,
        @SerializedName("merchantbearsfee")
        val merchantbearsfee: Int,
        @SerializedName("merchantfee")
        val merchantfee: Int,
        @SerializedName("modalauditid")
        val modalauditid: String,
        @SerializedName("narration")
        val narration: String,
        @SerializedName("orderRef")
        val orderRef: String,
        @SerializedName("paymentId")
        val paymentId: String,
        @SerializedName("paymentType")
        val paymentType: String,
        @SerializedName("raveRef")
        val raveRef: String,
        @SerializedName("redirectUrl")
        val redirectUrl: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("txRef")
        val txRef: String,
        @SerializedName("updatedAt")
        val updatedAt: String,
        @SerializedName("vbvrespcode")
        val vbvrespcode: String,
        @SerializedName("vbvrespmessage")
        val vbvrespmessage: String
    ): Parcelable {

        @Parcelize
        class FlwMeta: Parcelable
    }
}