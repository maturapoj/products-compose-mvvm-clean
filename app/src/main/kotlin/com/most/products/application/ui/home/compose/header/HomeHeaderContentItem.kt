package com.most.products.application.ui.home.compose.header

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.most.products.application.R
import com.most.products.application.core.theme.rounded8Dp
import com.most.products.application.ui.home.model.HomeHeaderUiModel

@Composable
fun HomeHeaderContentItem(
    headerContent: HomeHeaderUiModel,
    onImageClicked: (String, String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(0.5.dp, Color.Black)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = headerContent.name,
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .clip(RoundedCornerShape(rounded8Dp))
                        .clickable {
                            onImageClicked.invoke(headerContent.id, headerContent.name)
                        },
                    contentScale = ContentScale.FillHeight,
                    model = headerContent.imageUrl,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    error = painterResource(id = R.drawable.ic_launcher_background)
                )
            }
        }
    }
}

@Preview
@Composable
private fun HeaderContentItemPreview() {
    HomeHeaderContentItem(
        headerContent = HomeHeaderUiModel(
            id = "1",
            name = "Movies",
            imageUrl = "imageUrl"
        ),
        onImageClicked = { _, _ -> }
    )
}