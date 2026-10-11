package com.example.lab5_calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab5_calculator.ui.theme.Lab5_calculatorTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Slider
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab5_calculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var billAmount by remember { mutableStateOf("") }
    var dishesCount by remember { mutableStateOf("") }
    var tipPercent by remember { mutableStateOf(15f) }

    val discountPercent = when (dishesCount.toIntOrNull() ?: 0) {
        in 1..2 -> 3
        in 3..5 -> 5
        in 6..10 -> 7
        in 11..Int.MAX_VALUE -> 10
        else -> 0
    }
    val originalAmount = billAmount.toDoubleOrNull() ?: 0.0

    val discountedAmount = originalAmount *
            (1 - discountPercent / 100.0)

    val tipAmount = discountedAmount * tipPercent / 100

    val finalAmount = discountedAmount + tipAmount

    Column(
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Калькулятор чаевых",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Сумма заказа:",
                modifier = Modifier.width(130.dp)
            )

            OutlinedTextField(
                value = billAmount,
                onValueChange = { billAmount = it },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Количество блюд:",
                modifier = Modifier.width(175.dp)
            )

            OutlinedTextField(
                value = dishesCount,
                onValueChange = { dishesCount = it },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Чаевые: ${tipPercent.roundToInt()}%"
        )

        Slider(
            value = tipPercent,
            onValueChange = { tipPercent = it },
            valueRange = 0f..25f,
            steps = 24
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Скидка",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = discountPercent == 3,
                    onClick = {}
                )
                Text("3%")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = discountPercent == 5,
                    onClick = {}
                )
                Text("5%")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = discountPercent == 7,
                    onClick = {}
                )
                Text("7%")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = discountPercent == 10,
                    onClick = {}
                )
                Text("10%")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Сумма со скидкой: %.2f ₽".format(discountedAmount),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Чаевые: %.2f ₽".format(tipAmount),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Итого к оплате: %.2f ₽".format(finalAmount),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab5_calculatorTheme {
        Greeting("Android")
    }
}