package com.akash.nou.view.feature.homepage.composable

import Constant
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akash.nou.R
import showTopToast

@OptIn(ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun TicketScreen(context: Context) {
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
                        Spacer(modifier = Modifier.height(30.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier.padding(end = 10.dp)
                            ) {
                                DatePickerPopUp("যাত্রার তারিখ", R.drawable.calendar_icon)
                            }
                            Box(
                                modifier = Modifier.padding(start = 10.dp)
                            ) {
                                TimePickerPopUp("যাত্রার সময়", R.drawable.time_icon)
                            }
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(1.0f),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            var passengerCount by rememberSaveable {
                                mutableIntStateOf(0)
                            }
                            var childPassengerCount by rememberSaveable {
                                mutableIntStateOf(0)
                            }
                            NumericStepper("যাত্রী সংখ্যা (প্রাপ্তবয়স্ক)",
                                Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(end = 10.dp),
                                passengerCount,
                                decreaseNumber = {
                                    if (passengerCount > 0) passengerCount -= 1
                                    if (passengerCount == 0) childPassengerCount = 0
                                },
                                increaseNumber = {
                                    if (passengerCount < 5) passengerCount += 1
                                }
                            )
                            NumericStepper("যাত্রী সংখ্যা (শিশু)",
                                Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp),
                                childPassengerCount,
                                decreaseNumber = {
                                    if (childPassengerCount > 0) childPassengerCount -= 1
                                },
                                increaseNumber = {
                                    if (childPassengerCount < 5 && passengerCount > 0) childPassengerCount += 1
                                    if (passengerCount == 0) showTopToast(
                                        context,
                                        "অন্তত একজন প্রাপ্তবয়স্ক যাত্রী প্রয়োজন",
                                        "short",
                                        "positive"
                                    )
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        ElevatedButton(
                            content = {
                                Text(
                                    text = "টিকিট খুঁজুন",
                                    style = TextStyle(
                                        fontFamily = Constant().balooda2font,
                                        fontSize = 15.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    ),
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Constant().app_theme_color,
                                    shape = RoundedCornerShape(12.dp)
                                ),
                            border = BorderStroke(
                                color = Color.Transparent,
                                width = 1.dp
                            ),
                            enabled = true,
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 16.dp,
                                pressedElevation = 6.dp,
                                disabledElevation = 0.dp
                            ),
                            colors = ButtonColors(
                                containerColor = Constant().app_theme_color,
                                contentColor = Color.White,
                                disabledContainerColor = Color.Gray,
                                disabledContentColor = Color.White
                            ),
                            shape = RoundedCornerShape(12.dp),
                            onClick = { showTopToast(context, "Ticket", "short", "positive") },
                            contentPadding = ButtonDefaults.TextButtonWithIconContentPadding
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun TicketScreenPreview() {
    TicketScreenPreviewContent()
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun TicketScreenPreviewContent() {
    val context = androidx.compose.ui.platform.LocalContext.current
    TicketScreen(context)
}

