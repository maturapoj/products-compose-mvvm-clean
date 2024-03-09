package com.most.products.application.ui.home.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.most.products.application.core.composable.ProductDialog
import com.most.products.application.core.extensions.collectWithLifecycle
import com.most.products.application.core.theme.ApplicationTheme
import com.most.products.application.core.theme.space8Dp
import com.most.products.application.ui.home.compose.body.HomeBodyContent
import com.most.products.application.ui.home.compose.header.HomeHeaderContent
import com.most.products.application.ui.home.model.HomeBodyModel
import com.most.products.application.ui.home.model.HomeBodyUiModel
import com.most.products.application.ui.home.model.HomeEvent
import com.most.products.application.ui.home.model.HomeHeaderUiModel
import com.most.products.application.ui.home.model.HomeUiState
import com.most.products.application.ui.home.viewModel.HomeViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeMainScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {

    HandleEvent(event = viewModel.event)

    LaunchedEffect(Unit) {
        viewModel.getDepartments()
    }

    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
        onImageClicked = { id, departmentName ->
            viewModel.getProductId(id, departmentName)
        }
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onImageClicked: (String, String) -> Unit
) {
    val headerContent = uiState.headerContent.orEmpty()
    val bodyContent = uiState.bodyContent?.bodyModel.orEmpty()
    val departmentName = uiState.bodyContent?.departmentName

    Column(modifier = modifier) {
        HomeHeaderContent(
            modifier = Modifier.padding(space8Dp),
            headerContent,
            onImageClicked
        )

        HomeBodyContent(
            modifier = Modifier.padding(space8Dp),
            departmentName = departmentName,
            bodyContent = bodyContent,
            onOpenDialog = {
                uiState.openDialog?.invoke(it)
            }
        )
    }
}

@Composable
fun HandleEvent(
    event: Flow<HomeEvent>
) {
    val openDialog = rememberSaveable { mutableStateOf(false) }
    val description: MutableState<String?> = remember { mutableStateOf(null) }

    if (openDialog.value) {
        description.value?.let { desc ->
            ProductDialog(
                description = desc,
                onDismiss = { isShowDialog ->
                    openDialog.value = isShowDialog
                }
            )
        }
    }

    event.collectWithLifecycle {
        when (it) {
            is HomeEvent.ShowDialog -> {
                openDialog.value = true
                description.value = it.desc
            }
        }
    }
}

@Preview
@Composable
private fun HomeMainScreenPreview() {
    ApplicationTheme {
        HomeScreen(
            modifier = Modifier,
            uiState = HomeUiState(
                headerContent = listOf(
                    HomeHeaderUiModel(
                        id = "1",
                        name = "Movies",
                        imageUrl = "imageUrl"
                    ),
                    HomeHeaderUiModel(
                        id = "2",
                        name = "Health",
                        imageUrl = "imageUrl"
                    ),
                    HomeHeaderUiModel(
                        id = "2",
                        name = "Baby",
                        imageUrl = "imageUrl"
                    ),
                ),
                bodyContent = HomeBodyUiModel(
                    departmentName = "Movies",
                    bodyModel = listOf(
                        HomeBodyModel(
                            id = "1",
                            name = "Movies",
                            imageUrl = "https://loremflickr.com/640/480",
                            departmentId = "1",
                            desc = "desc",
                            type = "type",
                            price = "price"
                        ),
                        HomeBodyModel(
                            id = "1",
                            name = "Movies",
                            imageUrl = "https://loremflickr.com/640/480",
                            departmentId = "1",
                            desc = "desc",
                            type = "type",
                            price = "price"
                        ),
                        HomeBodyModel(
                            id = "1",
                            name = "Movies",
                            imageUrl = "https://loremflickr.com/640/480",
                            departmentId = "1",
                            desc = "desc",
                            type = "type",
                            price = "price"
                        ),
                    ),
                )
            ),
            onImageClicked = { _, _ -> }
        )
    }
}
