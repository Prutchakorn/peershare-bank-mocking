package com.example.prutc.peershare_bank.data.repository.impl

import android.arch.lifecycle.LiveData
import com.example.prutc.peershare_bank.data.entity.Recent
import com.example.prutc.peershare_bank.data.remote.BankApi
import com.example.prutc.peershare_bank.data.repository.BankRepository
import com.example.prutc.peershare_bank.data.repository.DataWrapper

class BankRepositoryImpl(val remoteSource: BankApi) : BankRepository {
    override fun list(userId: Int): LiveData<DataWrapper<List<Recent>>> {
        return object : LiveData<DataWrapper<List<Recent>>>() {
            init {
                remoteSource.list(userId).subscribe({ requestPayments ->
                    value = DataWrapper(
                        data = requestPayments,
                        error = null,
                        statusCode = null,
                        message = null
                    )
                }, { error ->
//                    val statusCode = error.message.toString().toInt()
                    value = DataWrapper(
                        data = null,
                        error = error,
                        statusCode = null,
                        message = error.message
                    )
                })
            }
        }
    }

    override fun transfer(peerId: Int): LiveData<DataWrapper<String>> {
        return object : LiveData<DataWrapper<String>>() {
            init {
                remoteSource.transfer(peerId).subscribe({ status ->
                    value = DataWrapper(
                        data = status,
                        error = null,
                        statusCode = null,
                        message = null
                    )
                }, { error ->
                    val statusCode = error.message.toString().toInt()
                    value = DataWrapper(
                        data = null,
                        error = error,
                        statusCode = statusCode,
                        message = ""
                    )
                })
            }
        }
    }

    override fun pay(rtpId: Int): LiveData<DataWrapper<String>> {
        return object : LiveData<DataWrapper<String>>() {
            init {
                remoteSource.pay(rtpId).subscribe({ status ->
                    value = DataWrapper(
                        data = status,
                        error = null,
                        statusCode = null,
                        message = null
                    )
                }, { error ->
                    val statusCode = error.message.toString().toInt()
                    value = DataWrapper(
                        data = null,
                        error = error,
                        statusCode = statusCode,
                        message = ""
                    )
                })
            }
        }
    }
}