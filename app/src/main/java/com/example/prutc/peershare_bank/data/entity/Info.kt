package com.example.prutc.peershare_bank.data.entity

data class Info(
    val CreatedDateTime: String,
    val Id: Int,
    val Name: String,
    val NumberOfPeople: Int,
    val Peers: List<Peer>,
    val RemainingPrice: Int,
    val TotalPrice: Int
)