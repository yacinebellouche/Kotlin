package com.example.kotlin_bmi


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin_bmi.ui.theme.Kotlin_bmiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kotlin_bmiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Bmi()
                }
            }
        }
    }
}

@Composable
fun Bmi(modifier: Modifier = Modifier) {
    var heightInput: String by remember {
        mutableStateOf(value = "")
    }
    var weightInput: String by remember {
        mutableStateOf(value = "")
    }
    val height = heightInput.toFloatOrNull() ?: 0.0f
    val weight = weightInput.toIntOrNull() ?: 0
    val bmi = if (height > 0 && weight > 0) weight / (height * height) else 0.0

    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = stringResource(R.string.body_mass_index),
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
        )
        OutlinedTextField(
            value = heightInput,
            onValueChange = { heightInput = it.replace(oldChar = ',', newChar = '.') },
            label = { Text(text = stringResource(R.string.height)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = weightInput,
            onValueChange = { weightInput = it.replace(oldChar = ',', newChar = '.') },
            label = { Text(text = stringResource(R.string.weight)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier.fillMaxWidth()
        )
        Text(
            text = stringResource(
                R.string.result,
                String.format("%.2f", bmi).replace(oldChar = ',', newChar = '.')
            ),
            modifier = modifier.padding(5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Kotlin_bmiTheme {
        Bmi()
    }

}