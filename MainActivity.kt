package com.example.scaffoldscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scaffoldscreen.ui.theme.ScaffoldScreenTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldScreenTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen(modifier: Modifier = Modifier) {
    var _expression by remember { mutableStateOf("") }
    var _result by remember { mutableStateOf("0") }
    val lsKeys = listOf("C", "DEL", "%", "/",
        "7", "8", "9", "x",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "Ans", "0", ".", "=")

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = "Máy Tính Đơn giản") })
        }
    ) { paddingValues ->
        Column(modifier = modifier.padding(paddingValues)) {
            TextField(
                value = _expression,
                onValueChange = {},
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp),
                singleLine = true,
                readOnly = true,
                textStyle = TextStyle(fontSize = 45.sp, textAlign = TextAlign.Center),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.Cyan,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = _result,
                onValueChange = {},
                modifier = modifier.fillMaxWidth(),
                singleLine = true,
                readOnly = true,
                textStyle = TextStyle(fontSize = 35.sp, textAlign = TextAlign.Right),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.Cyan,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Divider(
                modifier = modifier.padding(top = 10.dp, bottom = 10.dp),
                color = Color.LightGray
            )
            LazyVerticalGrid(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 40.dp, start = 12.dp, end = 12.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalArrangement = Arrangement.Center,
                columns = GridCells.Fixed(4),
                content = {
                    items(lsKeys) { key ->
                        Button(onClick = {
                            handleButtonClick(key, _expression) { newExpression ->
                                _expression = newExpression
                                if (key == "=") {
                                    _result = calculateResult(newExpression)
                                }
                            }
                        }) {
                            Text(text = key)
                        }
                    }
                }
            )
        }
    }
}

fun handleButtonClick(key: String, currentExpression: String, updateExpression: (String) -> Unit) {
    when (key) {
        "C" -> updateExpression("")
        "DEL" -> {
            if (currentExpression.isNotEmpty()) {
                updateExpression(currentExpression.dropLast(1))
            }
        }
        "=" -> {
            if (isValidExpression(currentExpression)) {
                updateExpression(currentExpression)
            }
        }
        else -> {

            if (isValidExpression(currentExpression + key)) {
                updateExpression(currentExpression + key)
            }
        }
    }
}

// Hàm thực hiện phép tính đơn giản
fun calculateResult(expression: String): String {
    return try {
        val replacedExpression = expression.replace("x", "*")
        val result = evaluateExpression(replacedExpression)
        result.toString()
    } catch (e: Exception) {
        "Error"
    }
}

// Hàm để tính toán biểu thức
fun evaluateExpression(expression: String): Double {
    val parts = expression.split("(?<=[-+*/])|(?=[-+*/])".toRegex()).filter { it.isNotEmpty() }
    if (parts.isEmpty()) return 0.0

    var currentResult = parts[0].toDouble()

    for (i in 1 until parts.size step 2) {
        val operator = parts[i]
        val nextValue = parts[i + 1].toDouble()

        currentResult = when (operator) {
            "+" -> currentResult + nextValue
            "-" -> currentResult - nextValue
            "*" -> currentResult * nextValue
            "/" -> {
                if (nextValue != 0.0) currentResult / nextValue else Double.NaN // Tránh chia cho 0
            }
            else -> currentResult
        }
    }
    return currentResult
}

fun isValidExpression(expression: String): Boolean {
    // Kiểm tra xem có nhiều hơn một dấu . trong một số
    val parts = expression.split(" ")
    for (part in parts) {
        if (part.contains(".")) {
            if (part.count { it == '.' } > 1) {
                return false
            }
        }
    }
    
    if (expression.isNotEmpty() && expression.last().isDigit()) {
        return true
    }
    return false
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScaffoldScreenTheme {
        ScaffoldScreen()
    }
}
