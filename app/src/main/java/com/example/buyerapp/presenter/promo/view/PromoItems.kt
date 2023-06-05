package com.example.buyerapp.presenter.promo.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.buyerapp.R
import com.example.buyerapp.core.util.toColor
import com.example.buyerapp.domain.model.promo.Promo

@Composable
fun PromoItems(promo: Promo, imageLoader: ImageLoader, onClick: () -> Unit) {

    val painter = rememberAsyncImagePainter(promo.url, imageLoader)

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .clickable {
            onClick()
        }) {
        Image(
            painter = painter,
            contentDescription = promo.url,
            modifier = Modifier
                .size(50.dp)
                .padding(end = 20.dp)
        )
        Column (modifier = Modifier.align(Alignment.CenterVertically)){
            Text(
                text = promo.title,
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W600,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = "#979797".toColor()
            )
            Text(
                text = promo.description,
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                lineHeight = 30.sp,
                color = "#979797".toColor()
            )
        }
    }
    Divider(
        color = colorResource(id = R.color.gray2),
        thickness = 1.dp,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
    )
}