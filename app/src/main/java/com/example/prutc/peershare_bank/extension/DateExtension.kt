package com.example.prutc.peershare_bank.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.toDate() = SimpleDateFormat("d MMM yyyy", Locale.getDefault()).format(this)

fun Date.toDateTime() = SimpleDateFormat("d MMM yyyy HH:mm:ss", Locale.getDefault()).format(this)