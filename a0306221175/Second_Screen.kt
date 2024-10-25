package com.example.a0306221175

import android.annotation.SuppressLint
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.formatWithSkeleton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api :: class)
@Composable
fun SecondScreen (navController: NavController){
    var _FullName by remember {
        mutableStateOf("")
    }
    var _Phone by remember {
        mutableStateOf("")
    }
    var _UserName by remember {
        mutableStateOf("")
    }
    var _PassWord by remember {
        mutableStateOf("")
    }

    var _SnackbarMessage by remember {
        mutableStateOf("")
    }
    var _SnackbarHideandOpen by remember {
        mutableStateOf(false)
    }
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

            Image(painter = painterResource(id = R.drawable.ckc),
                contentDescription = "Image",
                modifier = Modifier
                    .size(200.dp)
                    .padding(10.dp)
            )

            Text(text = "ĐĂNG KÝ TÀI KHOẢN", style = TextStyle(fontSize = 35.sp, color = Color.Blue))
            
            OutlinedTextField(
                value = _FullName,
                onValueChange = {_FullName = it},
                label = { Text(text = "Full Name")},
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Person")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = _Phone,
                onValueChange = {newPhoneNumber -> if(newPhoneNumber.all { it.isDigit()}){_Phone = newPhoneNumber} },
                label = { Text(text = "Phone")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = "Phone")},

                )
            OutlinedTextField(
                value = _UserName,
                onValueChange = {_UserName = it},
                label = { Text(text = "Username")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                leadingIcon = { Icon(Icons.Default.PersonOutline, contentDescription = "User")},

                )
            OutlinedTextField(
                value = _PassWord,
                onValueChange = {_PassWord = it},
                label = { Text(text = "Password")},
                leadingIcon = { Icon(Icons.Default.Key, contentDescription = "Password")},

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                , visualTransformation = PasswordVisualTransformation()
            )
            Button(onClick = {
                var counter = 0
                for (i in _UserName){
                    if(i.toString() == " "){
                        counter += 1
                    }
                }

                if(_FullName == "" || _Phone == "" || _UserName == "" || _PassWord == ""){
                    _SnackbarMessage = "Không được để trống bất cứ ô nhập nào"
                }else if(counter > 0){
                    _SnackbarMessage = "UserName không để khoảng trống"
                }else if(_PassWord.length < 8){
                    _SnackbarMessage = "Vui lòng điền Mật khẩu có độ dài tối thiểu là 8 ký tự"
                }
                else{
                    _SnackbarMessage= "Đăng kí thành công"
                }

                _SnackbarHideandOpen = true
                
            }, modifier = Modifier.padding(10.dp) ) {
                Text(text = "Đăng ký")
            }

        }
    }
    if(_SnackbarHideandOpen == true){
        Snackbar{
            Text(text = _SnackbarMessage, style = TextStyle(fontSize = 20.sp))
        }
    }
    
}