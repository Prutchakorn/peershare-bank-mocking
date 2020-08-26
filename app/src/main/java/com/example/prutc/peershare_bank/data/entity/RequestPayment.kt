package com.example.prutc.peershare_bank.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class RequestPayment(
    val ActivePeriod: Int,
    val Amount: Double,
    val Id: Long,
    val IsActive: Boolean,
    val ReceiverPromptPay: String,
    val RequestedDateTime: Date,
    val SenderPromptPay: String
): Parcelable