package com.example.buyerapp.application.navigation

import com.example.buyerapp.presenter.confirm_otp.ConfirmOtpType

interface NavigationProvider {

    fun navigateUp()

    fun openOnBoarding()

    fun openInputCellPhone()

    fun openConfirmOtp(cellphone: String, confirmOtpType: ConfirmOtpType)

    fun openPinCode()

    fun openNewPinCode(smsToken: String, cellphone: String, confirmOtpType: ConfirmOtpType)

    fun openHome()

    fun openPersonalInfo(
        firstname: String,
        lastname: String,
        cellphone: String
    )

    fun openProductInfo(barcode: String)
}