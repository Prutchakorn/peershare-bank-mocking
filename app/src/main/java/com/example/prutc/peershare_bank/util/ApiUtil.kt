package com.example.prutc.peershare_bank.util

import com.github.kittinunf.fuel.core.FuelManager

fun registerHeaderForJson() {
    FuelManager.instance.baseHeaders = mapOf(
        "Content-Type" to "application/json;boundary=${System.currentTimeMillis().toString(36)}")
}