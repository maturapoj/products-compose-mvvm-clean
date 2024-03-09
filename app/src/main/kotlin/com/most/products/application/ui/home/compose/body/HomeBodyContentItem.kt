package com.most.products.application.ui.home.compose.body

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.most.products.application.R
import com.most.products.application.core.theme.Typography
import com.most.products.application.core.theme.rounded8Dp
import com.most.products.application.core.theme.space4Dp
import com.most.products.application.core.theme.space8Dp
import com.most.products.application.ui.home.model.HomeBodyModel

@Composable
fun HomeBodyContentItem(
    modifier: Modifier,
    content: HomeBodyModel,
    onOpenDialog: (String) -> Unit
) {
    Card(
        modifier = modifier.height(290.dp),
        shape = RoundedCornerShape(rounded8Dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                model = content.imageUrl,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(space8Dp)
            ) {
                Spacer(modifier = Modifier.height(space8Dp))
                Text(
                    text = content.name,
                    style = Typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(space4Dp))
                Text(
                    text = content.desc,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = Typography.bodySmall,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = {
                        onOpenDialog.invoke(content.desc)
                    }) {
                    Text(
                        text = content.price,
                        style = Typography.labelMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeBodyContentItemPreview() {
    HomeBodyContentItem(
        modifier = Modifier.padding(space8Dp),
        content = HomeBodyModel(
            id = "1",
            name = "Movies",
            imageUrl = "https://loremflickr.com/640/480",
            departmentId = "1",
            desc = "desc",
            type = "type",
            price = "price"
        ),
        onOpenDialog = { }
    )
}