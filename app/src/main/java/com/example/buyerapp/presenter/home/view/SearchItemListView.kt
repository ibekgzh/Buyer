package com.example.buyerapp.presenter.home.view

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.buyerapp.R
import com.example.buyerapp.domain.model.Store

@Composable
fun SearchItemListView(
    store: Store, selected: Boolean,
    onSelected: (storeId: Int) -> Unit
) {

    Column(modifier = Modifier
        .padding(bottom = 40.dp)
        .clickable {
            onSelected(store.id)
        }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(text = store.title)
            }

            RadioButton(
                selected = selected,
                onClick = {
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