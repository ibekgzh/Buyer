package com.example.buyerapp.presenter.input_cell_phone

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.buyerapp.R
import com.example.buyerapp.application.navigation.NavigationProvider
import com.example.buyerapp.core.widget.SurfaceTopToolBarBack
import com.example.buyerapp.presenter.confirm_otp.ConfirmOtpType
import com.example.buyerapp.presenter.input_cell_phone.view.InputCellPhoneContent
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun InputCellPhoneScreen(
    navigator: NavigationProvider
) {
    val context = LocalContext.current

    SurfaceTopToolBarBack(
        onOnclickBackButton = { navigator.navigateUp() }
    ) {
        InputCellPhoneContent(
            onClickNext = {
                if (it.length == 11) {
                    navigator.openConfirmOtp(
                        cellphone = it,
                        ConfirmOtpType.AUTH_COMPLETE
                    )
                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.input_correct_format_phone),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }
}