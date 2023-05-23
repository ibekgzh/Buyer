package com.example.buyerapp.presenter.input_cell_phone.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R
import com.example.buyerapp.core.widget.DropDownMenu
import com.example.buyerapp.core.widget.PhoneField

@Composable
fun InputCellPhoneContent(onClickNext: (cellPhone: String) -> Unit) {

    var cellPhone by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp),
    ) {

        LinearProgressIndicator(
            progress = 0.33f,
            modifier = Modifier
                .padding(top = 30.dp, bottom = 40.dp)
                .fillMaxWidth()
                .height(2.dp),
            color = colorResource(id = R.color.black2)
        )

        Text(
            text = "ДОБАВИТЬ НОМЕР",
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.W800,
            fontSize = 24.sp,
            lineHeight = 30.sp,
            color = colorResource(id = R.color.black2),
        )
        Text(
            text = "Мы должны подтвердить его, отправив\n" + "сообщение",
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.W300,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            color = colorResource(id = R.color.black2),
            modifier = Modifier
                .padding(0.dp, 11.dp, 0.dp, 0.dp),
        )
        Text(
            text = "Номер телфона",
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.W700,
            fontSize = 16.sp,
            lineHeight = 30.sp,
            color = colorResource(id = R.color.black2),
            modifier = Modifier
                .padding(0.dp, 11.dp, 0.dp, 0.dp),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp, 0.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .height(52.dp)
                    .weight(0.4f)
            ) {
                DropDownMenu(list = LocalContext.current.resources.getStringArray(R.array.countries))
            }
            Box(modifier = Modifier.weight(0.05f))
            Box(
                modifier = Modifier
                    .height(52.dp)
                    .weight(0.55f)
            ) {
                PhoneField(
                    cellPhone,
                    mask = "+0 (000) 000 0000",
                    maskNumber = '0',
                    onPhoneChanged = { cellPhone = it },
                    modifier = Modifier
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp),
        ) {
            Button(
                onClick = {
                    onClickNext(cellPhone)
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
                Text(text = "Далее")
            }
        }
        Text(
            text = "Продолжая, вы подтверждаете, что являетесь владельцем или основным пользователем этого номера мобильного телефона. Вы соглашаетесь получать автоматические текстовые сообщения для подтверждения вашего номера телефона.",
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.W300,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            maxLines = 5,
            color = colorResource(id = R.color.gray1),
            modifier = Modifier
                .padding(0.dp, 35.dp, 0.dp, 0.dp),
        )
    }
}