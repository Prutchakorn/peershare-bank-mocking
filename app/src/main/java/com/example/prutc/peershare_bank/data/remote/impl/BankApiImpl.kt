package com.example.prutc.peershare_bank.data.remote.impl

import com.example.prutc.peershare_bank.BuildConfig
import com.example.prutc.peershare_bank.data.entity.Recent
import com.example.prutc.peershare_bank.data.remote.BankApi
import com.example.prutc.peershare_bank.util.registerHeaderForJson
import com.example.prutc.peershare_bank.util.toObjects
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import io.reactivex.Single
import timber.log.Timber

class BankApiImpl : BankApi {
    val basePath = BuildConfig.BASE_URL
    override fun list(userId: Int): Single<List<Recent>> {
        registerHeaderForJson()
        return Single.create<List<Recent>> {
            "${basePath}/api/getrecentpeershares?userid=$userId"
                .httpGet()
                .responseString { request, response, result ->
                    Timber.d(request.cUrlString())
                    Timber.d(response.data.toString())

                    result.fold({ requestPayments ->
                        if (requestPayments == "\"No Active RTP Found\"") {
                            it.onError(Throwable(requestPayments))
                        } else {
                            it.onSuccess(requestPayments.toObjects(Array<Recent>::class.java))
                        }
                    }, { _ ->
                        it.onError(Throwable(response.statusCode.toString()))
                    })
                }
        }
    }
    override fun transfer(peerId: Int): Single<String> {
        registerHeaderForJson()
        return Single.create<String> {
            "${basePath}/api/sendrtp?peerid=$peerId"
                .httpPost()
                .responseString { request, response, result ->
                    Timber.d(request.cUrlString())
                    Timber.d(response.data.toString())

                    result.fold({ status ->
                        it.onSuccess(status)
                    }, { _ ->
                        it.onError(Throwable(response.statusCode.toString()))
                    })
                }
        }
    }
    override fun pay(rtpId: Int): Single<String> {
        registerHeaderForJson()
        return Single.create<String> {
            "${basePath}/api/PayRTP?rtpid=$rtpId"
                .httpPost()
                .responseString { request, response, result ->
                    Timber.d(request.cUrlString())
                    Timber.d(response.data.toString())

                    result.fold({ status ->
                        it.onSuccess(status)
                    }, { _ ->
                        it.onError(Throwable(response.statusCode.toString()))
                    })
                }
        }
    }
}