package com.example.buyerapp.application.navigation

import com.example.buyerapp.presenter.confirm_otp.ConfirmOtpType
import com.example.buyerapp.presenter.destinations.DirectionDestination
import com.example.buyerapp.presenter.pincode.PinCodeScreenMode

interface NavigationProvider {

    fun navigateUp()

    fun navigateUp(destination: DirectionDestination)

    fun openOnBoarding()

    fun openInputCellPhone()

    fun openConfirmOtp(cellphone: String, confirmOtpType: ConfirmOtpType)

    fun openPinCode(pinCodeScreenMode: PinCodeScreenMode, oldPin: String? = null)

    fun openNewPinCode(smsToken: String, cellphone: String, confirmOtpType: ConfirmOtpType)

    fun openHome()

    fun openPersonalInfo(
        firstname: String,
        lastname: String,
        cellphone: String
    )

    fun openProductInfo(barcode: String)

    fun openBasket()

    fun openShop()

}