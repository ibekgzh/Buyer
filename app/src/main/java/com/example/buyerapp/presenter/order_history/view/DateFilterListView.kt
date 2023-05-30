package com.example.buyerapp.presenter.order_history.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.buyerapp.core.util.dateBefore1Year
import com.example.buyerapp.core.util.dateBefore3Month
import com.example.buyerapp.core.util.dateBefore6Month
import com.example.buyerapp.core.util.dateBeforeMonth
import com.example.buyerapp.core.util.dateBeforeWeek
import com.example.buyerapp.core.util.dateYesterday

@Composable
fun DateFilterListView(selected: Int, onSelected: (filter: Filter) -> Unit) {

    filters().map {
        Row(modifier = Modifier.clickable { onSelected(it) }) {
            Text(it.name, modifier = Modifier)
            RadioButton(
                selected = it.code == selected,
                onClick = {
                    
                })
        }
    }
}

data class Filter(
    val code: Int,
    val name: String,
    val value: String
)

fun filters() = listOf<Filter>(
    Filter(code = 0, name = "1 день", value = dateYesterday()),
    Filter(code = 1, name = "1 неделя", value = dateBeforeWeek()),
    Filter(code = 2, name = "1 месяц", value = dateBeforeMonth()),
    Filter(code = 3, name = "3 месяца", value = dateBefore3Month()),
    Filter(code = 4, name = "6 месяцев", value = dateBefore6Month()),
    Filter(code = 5, name = "1 год", value = dateBefore1Year())
)