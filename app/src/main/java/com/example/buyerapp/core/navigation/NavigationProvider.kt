package com.example.buyerapp.core.navigation

interface NavigationProvider {

    fun navigateUp()

    fun openOnBoarding()

    fun openInputCellPhone()

    fun openConfirmOtp(cellphone: String)

    fun openPinCode(changePin: Boolean = false, authKey: String? = null, cellPhone: String? = null)

    fun openNewPinCode(smsToken: String, cellphone: String)

    fun openHome()

    fun openPersonalInfo(
        firstname: String,
        lastname: String,
        cellphone: String
    )

    fun openProductInfo(barcode: String)
}