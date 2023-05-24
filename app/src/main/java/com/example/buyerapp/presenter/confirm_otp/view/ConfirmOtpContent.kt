package com.example.buyerapp.presenter.confirm_otp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R
import com.example.buyerapp.core.widget.OtpPinView

@Composable
fun ConfirmOtpContent(
    cellPhone: String,
    onInputPinComplete: (newPin: String) -> Unit,
    onRetry: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
            .padding(20.dp)
    ) {

        LinearProgressIndicator(
            progress = 0.66f,
            modifier = Modifier
                .padding(top = 30.dp, bottom = 40.dp)
                .fillMaxWidth()
                .height(2.dp),
            color = colorResource(id = R.color.black2)
        )

        Text(
            text = "Потверждение номера".uppercase(),
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.W800,
            fontSize = 24.sp,
            lineHeight = 30.sp,
            color = colorResource(id = R.color.black2),
        )

        Text(
            text = "Чтобы подтвердить учетную запись, введите 4-значный код, отправленный на номер $cellPhone",
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.W300,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            color = colorResource(id = R.color.black2),
            modifier = Modifier.padding(top = 11.dp)
        )

        Text(
            text = "Код",
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.W700,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = colorResource(id = R.color.black2),
            modifier = Modifier.padding(top = 14.dp)
        )

        OtpPinView(
            onInputPinCompleted = { onInputPinComplete(it) }
        )

        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Не получили код ?",
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W300,
                fontSize = 14.sp,
                lineHeight = 24.sp,
                color = colorResource(id = R.color.gray1),
            )

            Box(
                Modifier
                    .padding(start = 10.dp)
                    .clickable {
                        onRetry()
                    }) {
                Text(
                    text = "Попробовать еще",
                    fontFamily = FontFamily.Default,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.W700,
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    color = colorResource(id = R.color.black2),
                )
            }
        }
    }
}