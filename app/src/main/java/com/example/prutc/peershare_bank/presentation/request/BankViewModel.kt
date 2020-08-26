package com.example.prutc.peershare_bank.presentation.request

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.prutc.peershare_bank.data.entity.Recent
import com.example.prutc.peershare_bank.data.remote.impl.BankApiImpl
import com.example.prutc.peershare_bank.data.repository.impl.BankRepositoryImpl

class BankViewModel: ViewModel() {

    var bankRepository = BankRepositoryImpl(BankApiImpl())

    val state = State(
        requestPaymentsLoading = MutableLiveData()
    )

    val requestPayments = MutableLiveData<List<Recent>>()
    val status = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()
    val errorCode = MutableLiveData<Int>()

    fun list(userId: Int) {
        state.requestPaymentsLoading.value =   true
        bankRepository.list(userId).observeForever {
            state.requestPaymentsLoading.value = false
            val data = it?.data
            data?.let {
                requestPayments.value = it
            } ?: {
                errorCode.value = it?.statusCode
            }()
        }
    }

    fun transfer(peerId: Int) {
        state.requestPaymentsLoading.value =   true
        bankRepository.transfer(peerId).observeForever {
            state.requestPaymentsLoading.value = false
            val data = it?.data
            data?.let {
                status.value = it
            } ?: {
                errorMessage.value = it?.message
            }()
        }
    }

    fun pay(rtpId: Int) {
        state.requestPaymentsLoading.value =   true
        bankRepository.pay(rtpId).observeForever {
            state.requestPaymentsLoading.value = false
            val data = it?.data
            data?.let {
                status.value = it
            } ?: {
                errorMessage.value = it?.message
            }()
        }
    }
    data class State(
        val requestPaymentsLoading: MutableLiveData<Boolean>
    )
}