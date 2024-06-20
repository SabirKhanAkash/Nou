package com.akash.nou.view.feature.homepage.composable

import Constant
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akash.nou.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Constant().app_theme_color),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.global_map_icon),
                    contentDescription = "map_icon"
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 35.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = "টিকিট কাউন্টার",
                    style = TextStyle(
                        fontFamily = Constant().balooda2font,
                        color = Color.White,
                        fontSize = 20.sp,
                    )
                )
                Text(
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = "সিট বুক করুন",
                    style = TextStyle(
                        fontFamily = Constant().balooda2font,
                        color = Color.White,
                        fontSize = 18.sp,
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Surface(
                    shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 15.dp, horizontal = 20.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(25.dp))
                        DropDownMenuBox(
                            "সিটের ধরণ", R.drawable.seat_icon, R.array.seat_category
                        )
                        Spacer(modifier = Modifier.height(25.dp))
                        DropDownMenuBox(
                            "যাত্রা শুরুর স্থান", R.drawable.source_icon, R.array.zilla
                        )
                        Spacer(modifier = Modifier.height(25.dp))
                        DropDownMenuBox(
                            "যাত্রা শেষের স্থান", R.drawable.destination_icon, R.array.zilla
                        )
                        Spacer(modifier = Modifier.height(25.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(1.0f),
                            horizontalArrangement = Arrangement.Absolute.SpaceBetween
                        ) {
                            DatePickerPopUp("যাত্রার তারিখ", R.drawable.calendar_icon)
                            DatePickerPopUp("যাত্রার সময়", R.drawable.calendar_icon)
                        }
                    }
                }
            }
        }

    }
}

