/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.view.feature.homepage.composable

import Constant
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.akash.nou.model.BottomNavItem
import com.akash.nou.presentation.NavGraph

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomeScreen(navCtrl: NavController) {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigationBar(navController = navController) }) { innerPadding ->
        NavGraph(
            navController = navCtrl,
            modifier = Modifier.padding(innerPadding),
            navHostController = navController
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.ticket,
        BottomNavItem.history,
        BottomNavItem.profile,
    )
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Constant.small_light_gray, shape = RoundedCornerShape(12.dp)
            ), containerColor = Constant.small_light_gray, tonalElevation = 25.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        var currentRoute = navBackStackEntry?.destination?.route

        items.forEachIndexed { _, item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                icon = {
                    Icon(
                        painter = if (currentRoute == item.route) painterResource(id = item.selectedIcon)
                        else painterResource(id = item.unselectedIcon),
                        tint = if (currentRoute == item.route) (Constant.app_theme_color)
                        else (Constant.light_gray_3),
                        contentDescription = item.title.toString(),
                    )
                },
                label = {
                    Text(
                        stringResource(id = item.title),
                        style = if (currentRoute == item.route) TextStyle(
                            fontSize = 15.sp,
                            color = Constant.app_theme_color,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontFamily = Constant.balooda2font,
                        )
                        else TextStyle(
                            textAlign = TextAlign.Center,
                            fontFamily = Constant.balooda2font,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = Constant.app_theme_color,
                    selectedTextColor = Constant.app_theme_color,
                    selectedIndicatorColor = Constant.very_light_gray,
                    disabledIconColor = Constant.light_gray,
                    disabledTextColor = Constant.light_gray,
                    unselectedIconColor = Constant.medium_gray,
                    unselectedTextColor = Constant.medium_gray,
                ),
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    currentRoute = item.route
                },
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun HomePageScreenPreview() {
    HomePageScreenPreviewContent()
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomePageScreenPreviewContent() {
    val navController = rememberNavController()
    HomeScreen(navController)
}
