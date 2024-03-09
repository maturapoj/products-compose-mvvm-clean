package com.most.products.application.ui.home.compose.body

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.most.products.application.core.theme.ApplicationTheme
import com.most.products.application.core.theme.Typography
import com.most.products.application.core.theme.space8Dp
import com.most.products.application.ui.home.model.HomeBodyModel

@Composable
fun HomeBodyContent(
    modifier: Modifier = Modifier,
    departmentName: String?,
    bodyContent: List<HomeBodyModel>,
    onOpenDialog: (String) -> Unit,
) {
    departmentName?.let {
        Text(
            modifier = modifier,
            text = "Product list : $it",
            style = Typography.titleLarge
        )
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
    ) {
        items(
            items = bodyContent,
            key = { it.keyId }
        ) { content ->
            HomeBodyContentItem(
                modifier = modifier,
                content = content,
                onOpenDialog = onOpenDialog
            )
        }
    }
}

@Preview
@Composable
private fun BodyContentItemPreview() {
    ApplicationTheme {
        Column {
            HomeBodyContent(
                modifier = Modifier.padding(space8Dp),
                departmentName = "Movies",
                bodyContent = listOf(
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
                        id = "2",
                        name = "Movies2",
                        imageUrl = "https://loremflickr.com/640/480",
                        departmentId = "2",
                        desc = "desc2",
                        type = "type2",
                        price = "price2"
                    ),
                    HomeBodyModel(
                        id = "3",
                        name = "Movies",
                        imageUrl = "https://loremflickr.com/640/480",
                        departmentId = "3",
                        desc = "desc3",
                        type = "type3",
                        price = "price3"
                    ),
                ),
                onOpenDialog = {}
            )
        }
    }
}
