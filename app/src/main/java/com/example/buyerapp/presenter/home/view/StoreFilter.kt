package com.example.buyerapp.presenter.home.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R
import com.example.buyerapp.domain.model.Pageable
import com.example.buyerapp.domain.model.Store

@Composable
fun StoreFilter(
    stores: Pageable<Store>,
    onSearchTextChanged: (text: String) -> Unit,
    onChooseShop: (store: Store) -> Unit
) {

    val shopName = remember { mutableStateOf(TextFieldValue("")) }

    val selected = remember { mutableStateOf(0) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 60.dp)
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
            "Выберите магазин",
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.W700,
            fontSize = 18.sp,
            lineHeight = 30.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 20.dp, bottom = 18.dp)
        )

        TextField(
            value = shopName.value,
            onValueChange = { value ->
                shopName.value = value
                onSearchTextChanged(value.text)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            leadingIcon = {
                Icon(
                    painterResource(id = R.drawable.search),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp)
                )
            },
            trailingIcon = {
                if (shopName.value != TextFieldValue("")) {
                    IconButton(
                        onClick = {
                            shopName.value =
                                TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                        }
                    ) {
                        Icon(
                            painterResource(id = R.drawable.close),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(80.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                cursorColor = Color.Black,
                backgroundColor = colorResource(id = R.color.gray2),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        LazyColumn {

            items(stores.data) { store ->
                SearchItemListView(
                    store = store,
                    selected = selected.value == store.id,
                    onSelected = {
                        selected.value = it
                    }
                )
            }
        }

        Button(
            onClick = {
                if (selected.value == 0) {
                    Toast.makeText(context, "Не выбран магазин", Toast.LENGTH_SHORT).show()
                } else {
                    onChooseShop(stores.data.first { t -> t.id == selected.value })
                }
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
                text = "Выбрать магазин",
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