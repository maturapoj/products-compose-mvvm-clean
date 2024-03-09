package com.most.products.application.ui.home.compose.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.most.products.application.core.theme.space8Dp
import com.most.products.application.ui.home.model.HomeHeaderUiModel

@Composable
fun HomeHeaderContent(
    modifier: Modifier = Modifier,
    headerContentList: List<HomeHeaderUiModel>,
    onImageClicked: (String, String) -> Unit
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = headerContentList,
            key = { it.keyId }
        ) { headerContent: HomeHeaderUiModel ->
            HomeHeaderContentItem(headerContent, onImageClicked)
        }
    }
}

@Preview
@Composable
private fun HomeHeaderContentPreview() {
    HomeHeaderContent(
        modifier = Modifier.padding(space8Dp),
        headerContentList = listOf(
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
        onImageClicked = { _, _ -> }
    )
}
