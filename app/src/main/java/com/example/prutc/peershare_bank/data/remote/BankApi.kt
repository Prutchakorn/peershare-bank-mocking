package com.example.prutc.peershare_bank.data.remote

import com.example.prutc.peershare_bank.data.entity.Recent
import io.reactivex.Single

interface BankApi {
    fun list(userId: Int): Single<List<Recent>>
    fun transfer(peerId: Int): Single<String>
    fun pay(rtpId: Int): Single<String>
}