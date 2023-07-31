package com.example.buyerapp.presenter.order.details.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R
import com.example.buyerapp.core.util.toColor
import com.example.buyerapp.domain.model.order_info.OrderDetails

@Composable
fun OrderDetailsContent(order: OrderDetails, onClickQrCode: (code: String) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = 20.dp)
    ) {

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(20.dp)
        ) {
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = (order.price / 100).toString() + " KZT",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W600,
                        fontSize = 32.sp,
                        lineHeight = 30.sp,
                        color = Color.Black
                    )

                    DashedDivider(
                        color = colorResource(id = R.color.gray2),
                        thickness = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp, bottom = 20.dp)
                    )

                    for (it in order.items) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = it.item.title + " " + it.item.measureUnit.divValue + " " + it.item.measureUnit.title,
                                fontFamily = FontFamily.Default,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.W600,
                                fontSize = 15.sp,
                                lineHeight = 15.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.weight(0.6f)
                            )

                            Spacer(modifier = Modifier.weight(0.1f))

                            Text(
                                text = (it.totalPrice / 100).toString() + " KZT",
                                fontFamily = FontFamily.Default,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.W400,
                                fontSize = 13.sp,
                                lineHeight = 30.sp,
                                color = Color.Black,
                                textAlign = TextAlign.End,
                                modifier = Modifier.weight(0.3f)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Итого",
                            fontFamily = FontFamily.Default,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp,
                            lineHeight = 30.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = (order.price / 100).toString() + " KZT",
                            fontFamily = FontFamily.Default,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp,
                            lineHeight = 30.sp,
                            color = Color.Black,
                            textAlign = TextAlign.End
                        )
                    }

                    DashedDivider(
                        color = colorResource(id = R.color.gray2),
                        thickness = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 20.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Время покупки",
                            fontFamily = FontFamily.Default,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp,
                            lineHeight = 30.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = order.regDate,
                            fontFamily = FontFamily.Default,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp,
                            lineHeight = 30.sp,
                            color = Color.Black,
                            textAlign = TextAlign.End
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 26.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Способ оплаты",
                            fontFamily = FontFamily.Default,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp,
                            lineHeight = 30.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = order.paymentType.descr,
                            fontFamily = FontFamily.Default,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp,
                            lineHeight = 30.sp,
                            color = Color.Black,
                            textAlign = TextAlign.End
                        )
                    }

                    DashedDivider(
                        color = colorResource(id = R.color.gray2),
                        thickness = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 40.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Статус:",
                            fontFamily = FontFamily.Default,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.W600,
                            fontSize = 20.sp,
                            lineHeight = 30.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = order.state.descr,
                            fontFamily = FontFamily.Default,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp,
                            lineHeight = 30.sp,
                            color = order.state.color.toColor(),
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                onClickQrCode(order.qrCode)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(80.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.scan_barcode),
                contentDescription = "QR-code Чек",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.padding(end = 20.dp)
            )

            Text(
                "QR-code Чек",
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W600,
                fontSize = 13.sp,
                lineHeight = 20.sp,
            )
        }
    }
}

@Composable
fun DashedDivider(
    thickness: Dp,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    phase: Float = 10f,
    intervals: FloatArray = floatArrayOf(10f, 10f),
    modifier: Modifier
) {
    Canvas(
        modifier = modifier
    ) {
        val dividerHeight = thickness.toPx()
        drawRoundRect(
            color = color,
            style = Stroke(
                width = dividerHeight,
                pathEffect = PathEffect.dashPathEffect(
                    intervals = intervals,
                    phase = phase
                )
            )
        )
    }
}
