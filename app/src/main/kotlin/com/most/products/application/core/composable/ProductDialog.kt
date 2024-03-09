package com.most.products.application.core.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.most.products.application.core.theme.Typography
import com.most.products.application.core.theme.rounded16Dp
import com.most.products.application.core.theme.space16Dp

@Composable
fun ProductDialog(description: String, onDismiss: (Boolean) -> Unit) {
    Dialog(
        onDismissRequest = {
            onDismiss.invoke(false)
        }
    ) {
        ProductDialogContent(
            description = description,
            onDismiss = onDismiss
        )
    }
}

@Composable
fun ProductDialogContent(description: String, onDismiss: (Boolean) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(rounded16Dp)
    ) {
        Column {
            Spacer(modifier = Modifier.height(space16Dp))
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Product Description",
                style = Typography.titleMedium
            )
            Text(
                modifier = Modifier.padding(space16Dp),
                text = description,
                style = Typography.bodyMedium,
            )
            HorizontalDivider(modifier = Modifier.height(1.dp))
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    onDismiss.invoke(false)
                },
            ) {
                Text(
                    text = "Close",
                    style = Typography.titleSmall,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProductDialogContentPreview() {
    ProductDialogContent("description") { }
}
