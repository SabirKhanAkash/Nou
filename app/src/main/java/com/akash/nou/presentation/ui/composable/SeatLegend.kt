package com.akash.nou.view.feature.homepage.composable

import Constant
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akash.nou.R

@Composable
fun SeatLegend() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(6.dp)
            )
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(6.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent)
                .weight(1.0f, fill = true)
                .padding(
                    vertical = 5.dp,
                    horizontal = 16.dp
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.30f, fill = true),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    colorFilter = ColorFilter.tint(color = Color.Gray),
                    painter = painterResource(id = R.drawable.ic_chair),
                    contentDescription = "Seat icon",
                    modifier = Modifier
                        .padding(2.dp)
                )
                Text(
                    text = "ফাকা সীট",
                    color = Constant.medium_gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.35f, fill = true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    colorFilter = ColorFilter.tint(color = Color.Green),
                    painter = painterResource(id = R.drawable.ic_chair),
                    contentDescription = "Seat icon",
                    modifier = Modifier
                        .padding(2.dp)
                )
                Text(
                    text = "নির্বাচিত সীট",
                    color = Constant.medium_gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.35f, fill = true),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    colorFilter = ColorFilter.tint(color = Constant.app_theme_color),
                    painter = painterResource(id = R.drawable.ic_chair),
                    contentDescription = "Seat icon",
                    modifier = Modifier
                        .padding(2.dp)
                )
                Text(
                    text = "বিক্রিত সীট",
                    color = Constant.medium_gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            }
        }
    }
}