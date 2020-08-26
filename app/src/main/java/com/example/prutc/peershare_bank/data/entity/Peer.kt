package com.example.prutc.peershare_bank.data.entity

data class Peer(
    val CreatedDateTime: String,
    val Id: Int,
    val InfoId: Int,
    val IsActive: Boolean,
    val IsOwner: Boolean,
    val PaidDateTime: String,
    val PersonalPrice: Int,
    val Status: Status,
    val StatusId: Int,
    val User: User,
    val UserId: Int
)