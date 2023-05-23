package com.example.buyerapp.core.navigation

interface NavigationProvider {

    fun navigateUp()

    fun openOnBoarding()

    fun openInputCellPhone()

    fun openConfirmOtp(cellphone: String)

    fun openPinCode()

    fun openNewPinCode(smsToken: String, cellphone: String)

    fun openHome()

    fun openPersonalInfo(
        firstname: String,
        lastname: String,
        cellphone: String
    )

    fun openProductInfo(barcode: String)
}