package com.akash.nou.view.feature.homepage.composable

import Constant
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.akash.nou.model.BottomNavItem

@Composable
fun HomePageScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        NavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.ticket,
        BottomNavItem.history,
        BottomNavItem.profile,
    )
    NavigationBar(containerColor = Color.White, tonalElevation = 15.dp) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        var currentRoute = navBackStackEntry?.destination?.route

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                icon = {
                    Icon(
                        painter = if (currentRoute == item.route)
                            painterResource(id = item.selectedIcon)
                        else
                            painterResource(id = item.unselectedIcon),
                        tint = if (currentRoute == item.route)
                            (Constant().app_theme_color)
                        else
                            (Constant().light_gray_3),
                        contentDescription = item.title.toString(),
                    )
                },
                label = {
                    Text(
                        stringResource(id = item.title),
                        style = if (currentRoute == item.route) TextStyle(
                            fontSize = 15.sp,
                            color = Constant().app_theme_color,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontFamily = Constant().balooda2font,
                        )
                        else TextStyle(
                            textAlign = TextAlign.Center,
                            fontFamily = Constant().balooda2font,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomePageScreen()
}
