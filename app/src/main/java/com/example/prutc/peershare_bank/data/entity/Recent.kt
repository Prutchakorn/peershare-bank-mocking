package com.example.prutc.peershare_bank.data.entity

data class Recent(
    val CreatedDateTime: String,
    val Id: Int,
    val Info: Info,
    val InfoId: Int,
    val IsActive: Boolean,
    val IsOwner: Boolean,
    val Owner: Owner,
    val OwnerId: Int,
    val Status: String
)