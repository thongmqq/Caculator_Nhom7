package com.example.a0306221175

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api :: class)
@Composable
fun FirstScreen (navController: NavController){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Nguyễn Hoàng Minh Thông - 0306221175")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                )
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //Column -> Text
            Text(text = "CKC PORTAL", style = TextStyle(fontSize = 45.sp, fontWeight = FontWeight.Bold, color = Color.Red))

            // Column -> Image
            Image(painter = painterResource(id = R.drawable.ckc),
                contentDescription = "Image",
                modifier = Modifier
                    .size(300.dp)
                    .padding(5.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
                ) {
                Button(onClick = { navController.navigate(Screen.SecondScreen.route) },
                    modifier = Modifier.padding(5.dp) ) {
                    Text(text = "Đăng ký tài khoản")
                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(5.dp) ) {
                    Text(text = "Đăng nhập")
                }
            }
        }
    }
}