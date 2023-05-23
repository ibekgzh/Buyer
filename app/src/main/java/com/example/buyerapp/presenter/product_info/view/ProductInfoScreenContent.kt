package com.example.buyerapp.presenter.product_info.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R
import com.example.buyerapp.domain.model.ProductInfo

@Composable
fun ProductInfoScreenContent(product: ProductInfo) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                product.title,
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W700,
                fontSize = 24.sp,
                lineHeight = 30.sp,
                color = Color.Black
            )

            Text(
                text = "В наличии: " + product.measureUnit.divValue + " " + product.measureUnit.title,
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                lineHeight = 30.sp,
                color = colorResource(id = R.color.gray3),
                modifier = Modifier.padding(top = 20.dp)
            )

            Text(
                text = "Количество: " + product.measureUnit.divValue,
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                lineHeight = 30.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 42.dp)
            )

            Text(
                text = "Описание продукта: " + product.description,
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W300,
                fontSize = 16.sp,
                lineHeight = 30.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 46.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {

                Divider(
                    color = colorResource(id = R.color.gray2),
                    thickness = 1.dp,
                    modifier = Modifier.padding(bottom = 5.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Стоимость:",
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        lineHeight = 30.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "₸ " + product.price / 100,
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W500,
                        fontSize = 26.sp,
                        lineHeight = 30.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                ) {
                    Button(
                        onClick = {
//                            onClickNext(cellPhone)
                        },
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .width(335.dp)
                            .height(52.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White, containerColor = Color.Black
                        ),
                        shape = RoundedCornerShape(80.dp)

                    ) {
                        Text(text = "Продолжить")
                    }
                }
            }
        }
    }
}