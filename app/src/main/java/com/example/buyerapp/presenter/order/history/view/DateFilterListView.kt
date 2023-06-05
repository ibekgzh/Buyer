package com.example.buyerapp.presenter.order.history.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import com.example.buyerapp.core.util.dateBefore1Year
import com.example.buyerapp.core.util.dateBefore3Month
import com.example.buyerapp.core.util.dateBefore6Month
import com.example.buyerapp.core.util.dateBeforeMonth
import com.example.buyerapp.core.util.dateBeforeWeek
import com.example.buyerapp.core.util.dateYesterday

@Composable
fun DateFilterListView(selected: Int, onSelected: (filter: Filter) -> Unit) {

    Column(modifier = Modifier.padding(bottom = 40.dp)) {
        filters().map {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 15.dp)
                    .clickable { onSelected(it) },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    it.name,
                    fontFamily = FontFamily.Default,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    lineHeight = 30.sp,
                    color = Color.Black,
                )

                RadioButton(
                    selected = it.code == selected,
                    onClick = {
                        onSelected(it)
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = colorResource(id = R.color.switch_background)
                    )
                )
            }

            Divider(
                color = colorResource(id = R.color.gray2),
                thickness = 1.dp,
            )
        }
    }
}

data class Filter(
    val code: Int,
    val name: String,
    val startDate: String
)

fun filters() = listOf(
    Filter(code = 0, name = "1 день", startDate = dateYesterday()),
    Filter(code = 1, name = "1 неделя", startDate = dateBeforeWeek()),
    Filter(code = 2, name = "1 месяц", startDate = dateBeforeMonth()),
    Filter(code = 3, name = "3 месяца", startDate = dateBefore3Month()),
    Filter(code = 4, name = "6 месяцев", startDate = dateBefore6Month()),
    Filter(code = 5, name = "1 год", startDate = dateBefore1Year())
)