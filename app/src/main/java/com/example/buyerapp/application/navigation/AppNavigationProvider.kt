package com.example.buyerapp.application.navigation

import androidx.navigation.NavController
import com.example.buyerapp.presenter.confirm_otp.ConfirmOtpType
import com.example.buyerapp.presenter.destinations.CartScreenDestination
import com.example.buyerapp.presenter.destinations.ConfirmOtpScreenDestination
import com.example.buyerapp.presenter.destinations.FilteredNotificationsScreenDestination
import com.example.buyerapp.presenter.destinations.HomeScreenDestination
import com.example.buyerapp.presenter.destinations.InputCellPhoneScreenDestination
import com.example.buyerapp.presenter.destinations.LastNotificationsOfStoresScreenDestination
import com.example.buyerapp.presenter.destinations.NewPinCodeScreenDestination
import com.example.buyerapp.presenter.destinations.OnBoardingScreenDestination
import com.example.buyerapp.presenter.destinations.OrderDetailsScreenDestination
import com.example.buyerapp.presenter.destinations.OrderHistoryScreenDestination
import com.example.buyerapp.presenter.destinations.PersonalInfoScreenDestination
import com.example.buyerapp.presenter.destinations.PinCodeScreenDestination
import com.example.buyerapp.presenter.destinations.ProductInfoScreenDestination
import com.example.buyerapp.presenter.destinations.PromoScreenDestination
import com.example.buyerapp.presenter.destinations.StoreScreenDestination
import com.example.buyerapp.presenter.home.HomeTabsDestination
import com.example.buyerapp.presenter.pincode.PinCodeScreenMode
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popUpTo

class AppNavigationProvider(private val navController: NavController) : NavigationProvider {

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun navigateUp(destination: HomeScreenDestination) {
        navController.popBackStack(destination.route, false)
    }

    override fun openOnBoarding() {
        navController.navigate(
            OnBoardingScreenDestination
        ) {
            popUpTo(navController.graph.id)
        }
    }

    override fun openInputCellPhone() {
        navController.navigate(InputCellPhoneScreenDestination)
    }

    override fun openConfirmOtp(cellphone: String, confirmOtpType: ConfirmOtpType) {
        navController.navigate(ConfirmOtpScreenDestination(confirmOtpType, cellphone))
    }

    override fun openPinCode(pinCodeScreenMode: PinCodeScreenMode, oldPin: String?) {
        navController.navigate(PinCodeScreenDestination(pinCodeScreenMode, oldPin))
    }

    override fun openNewPinCode(
        smsToken: String,
        cellphone: String,
        confirmOtpType: ConfirmOtpType
    ) {
        navController.navigate(NewPinCodeScreenDestination(confirmOtpType, smsToken, cellphone))
    }

    override fun openHome(homeTabsDestination: HomeTabsDestination) {
        navController.navigate(
            HomeScreenDestination(false, homeTabsDestination)
        ) {
            popUpTo(navController.graph.id)
        }
    }

    override fun openHomeForFilterPromo() {
        navController.navigate(HomeScreenDestination(true, HomeTabsDestination.Main))
    }

    override fun openPersonalInfo(firstname: String, lastname: String, cellphone: String) {
        navController.navigate(PersonalInfoScreenDestination(firstname, lastname, cellphone))
    }

    override fun openProductInfo(barcode: String) {
        navController.navigate(ProductInfoScreenDestination(barcode))
    }

    override fun openCart() {
        navController.navigate(CartScreenDestination)
    }

    override fun openStore(id: Long) {
        navController.navigate(StoreScreenDestination(id))
    }

    override fun openOrderHistory() {
        navController.navigate(OrderHistoryScreenDestination)
    }

    override fun openOrderDetails(id: Long) {
        navController.navigate(OrderDetailsScreenDestination(id))
    }

    override fun openPromos(storeId: Long) {
        navController.navigate(PromoScreenDestination(storeId)){
            popUpTo(PromoScreenDestination){
                inclusive = true
            }
        }
    }

    override fun openLastNotificationsOfStores() {
        navController.navigate(LastNotificationsOfStoresScreenDestination)
    }

    override fun openFilteredNotifications(storeId: Long) {
        navController.navigate(FilteredNotificationsScreenDestination(storeId))
    }

    override fun logout() {
        navController.navigate(OnBoardingScreenDestination) {
            popUpTo(navController.graph.id)
        }
    }
}