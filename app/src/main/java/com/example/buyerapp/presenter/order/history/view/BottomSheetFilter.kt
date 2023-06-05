package com.example.buyerapp.presenter.order.history.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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

@Composable
fun BottomSheetFilter(selectedFilter: Int, onSelected: (filter: Filter) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            .wrapContentHeight()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "")

        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.gray6))
                .height(3.dp)
                .width(40.dp)
                .clip(shape = RoundedCornerShape(100.dp))
        )

        Text(
            "Фильтр по дате",
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.W700,
            fontSize = 18.sp,
            lineHeight = 30.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
        )

        DateFilterListView(selected = selectedFilter, onSelected = {
            onSelected(it)
        })

        Button(
            onClick = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(80.dp)
        ) {
            Text(
                text = "Применить",
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                lineHeight = 30.sp,
                color = Color.White,
            )
        }
    }
}