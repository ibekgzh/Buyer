package com.example.buyerapp.presenter.order.details.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R
import com.lightspark.composeqr.QrCodeView

@Composable
fun QrCodeContent(code: String) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 10.dp, 10.dp, 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.gray6))
                .height(3.dp)
                .width(40.dp)
                .clip(shape = RoundedCornerShape(100.dp))
        )
        Text(
            text = "QR-code Чек",
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.W600,
            fontSize = 18.sp,
            lineHeight = 30.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(0.dp, 20.dp, 0.dp, 30.dp)
        )
        QrCodeView(
            data = code,
            modifier = Modifier
                .size(200.dp)
        )
    }
}