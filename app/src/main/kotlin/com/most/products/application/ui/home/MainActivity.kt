package com.most.products.application.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.most.products.application.core.theme.ApplicationTheme
import com.most.products.application.ui.home.compose.HomeMainScreen
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
                    HomeMainScreen()
                    ViewModelInject()
                }
            }
        }
    }
}

@Composable
fun ViewModelInject(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getDepartments()
    }

    val uiState by viewModel.uiState.collectAsState()

    val departments = uiState.departments.orEmpty()
    val products = uiState.products.orEmpty()

    Column {
        LazyRow(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = departments,
                key = {
                    it.id
                }) { department ->
                Column {
                    AsyncImage(
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                viewModel.getProductId(department.id, department.name)
                            },
                        model = department.imageUrl,
                        contentDescription = null,
                    )
                    Text(text = department.name, textAlign = TextAlign.Center)
                }
            }
        }

        uiState.departmentName?.let {
            Text(text = "Product list : $it")
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
        ) {
            items(products) { product ->
                Card(
                    modifier = Modifier.padding(8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column {
                        AsyncImage(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .align(Alignment.CenterHorizontally),
                            model = product.imageUrl,
                            contentDescription = null,
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = product.name)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = product.desc.orEmpty(),
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TextButton(
                                modifier = Modifier.align(Alignment.End),
                                onClick = {
                                    // open dialog
                                }) {
                                Text(text = "price")
                            }
                        }
                    }
                }
            }
        }
    }
}
