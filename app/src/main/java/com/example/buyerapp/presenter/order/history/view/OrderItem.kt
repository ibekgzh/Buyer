package com.example.buyerapp.presenter.order.history.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R
import com.example.buyerapp.core.util.toColor
import com.example.buyerapp.domain.model.order_info.Order

@Composable
fun OrderItem(
    order: Order,
    onSelectItem: (order: Order) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelectItem(order)
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(id = R.drawable.order),
                    contentDescription = order.id.toString(),
                    contentScale = ContentScale.Crop,
                )
                Column(modifier = Modifier.padding(start = 18.dp)) {
                    Text(
                        order.store.title,
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        lineHeight = 30.sp,
                        color = colorResource(id = R.color.black2),
                    )
                    Text(
                        order.state.descr,
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        lineHeight = 30.sp,
                        color = order.state.color.toColor(),
                        modifier = Modifier.padding(top = 3.dp)
                    )
                }
            }

            Text(
                "-${order.price} KZT",
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                lineHeight = 30.sp,
                color = "#DB5C5C".toColor(),
            )
        }

        Divider(
            color = colorResource(id = R.color.gray2),
            thickness = 1.dp,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )
    }

}


