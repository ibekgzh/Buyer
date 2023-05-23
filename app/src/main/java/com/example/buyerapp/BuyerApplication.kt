package com.example.buyerapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BuyerApplication(val key: String? = null) : Application() {
}