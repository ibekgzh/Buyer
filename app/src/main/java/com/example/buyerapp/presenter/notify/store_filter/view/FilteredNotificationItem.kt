package com.example.buyerapp.presenter.notify.store_filter.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R
import com.example.buyerapp.domain.model.fcm.LastNotificationOfStore

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilteredNotificationItem(lastNotificationOfStore: LastNotificationOfStore) {

    Column(modifier = Modifier.padding(10.dp)) {
        FlowRow(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        topEnd = 15.dp,
                        bottomEnd = 15.dp,
                        bottomStart = 15.dp
                    )
                )
                .background(color = colorResource(id = R.color.gray2))
                .padding(15.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Column(modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()) {
                Text(
                    text = lastNotificationOfStore.body,
                    fontFamily = FontFamily.Default,
                    fontStyle = FontStyle.Normal,
                    fontSize = 16.sp,
                    lineHeight = 30.sp,
                    color = colorResource(id = R.color.black2),
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(alignment = Alignment.Bottom)
            ) {
                Text(
                    text = lastNotificationOfStore.sentTime,
                    fontFamily = FontFamily.Default,
                    fontStyle = FontStyle.Normal,
                    fontSize = 13.sp,
                    lineHeight = 30.sp,
                    color = colorResource(id = R.color.gray1)
                )
            }
        }
    }
}