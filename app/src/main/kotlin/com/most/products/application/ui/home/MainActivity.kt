package com.most.products.application.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.most.products.application.BuildConfig
import com.most.products.application.core.theme.ApplicationTheme
import com.most.products.application.ui.home.viewModel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ViewModelInject(BuildConfig.BASE_URL)
                }
            }
        }
    }
}

@Composable
fun ViewModelInject(
    userName : String,
    viewModel: HomeViewModel = koinViewModel()
){
    val context = LocalContext.current

    val text  = viewModel.departmentsItem.collectAsState()
    Text(text = text.value, modifier = Modifier.padding(8.dp))
    text.value.takeIf { it.isNotEmpty() }?.let {
        Toast.makeText(context, text.value, Toast.LENGTH_SHORT).show()
    }
}
