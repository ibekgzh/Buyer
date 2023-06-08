package com.example.buyerapp.application.navigation

import com.example.buyerapp.presenter.confirm_otp.ConfirmOtpType
import com.example.buyerapp.presenter.destinations.HomeScreenDestination
import com.example.buyerapp.presenter.home.HomeTabsDestination
import com.example.buyerapp.presenter.pincode.PinCodeScreenMode

interface NavigationProvider {

    fun navigateUp()

    fun navigateUp(destination: HomeScreenDestination)

    fun openOnBoarding()

    fun openInputCellPhone()

    fun openConfirmOtp(cellphone: String, confirmOtpType: ConfirmOtpType)

    fun openPinCode(pinCodeScreenMode: PinCodeScreenMode, oldPin: String? = null)

    fun openNewPinCode(smsToken: String, cellphone: String, confirmOtpType: ConfirmOtpType)

    fun openHome(homeTabsDestination: HomeTabsDestination = HomeTabsDestination.Main)

    fun openPersonalInfo(
        firstname: String,
        lastname: String,
        cellphone: String
    )

    fun openProductInfo(barcode: String)

    fun openCart()

    fun openStore(id: Long)

    fun openOrderHistory()

    fun openOrderDetails(id: Long)

    fun openPromos(storeId: Long)

    fun openLastNotificationsOfStores()

    fun openFilteredNotifications(storeId: Long)
}