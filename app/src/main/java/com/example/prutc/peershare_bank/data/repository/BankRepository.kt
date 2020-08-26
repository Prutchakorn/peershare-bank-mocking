package com.example.prutc.peershare_bank.data.repository

import android.arch.lifecycle.LiveData
import com.example.prutc.peershare_bank.data.entity.Recent

interface BankRepository {
    fun list(userId: Int): LiveData<DataWrapper<List<Recent>>>
    fun transfer(peerId: Int): LiveData<DataWrapper<String>>
    fun pay(rtpId: Int): LiveData<DataWrapper<String>>
}