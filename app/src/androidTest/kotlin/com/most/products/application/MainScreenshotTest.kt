package com.most.products.application

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import com.karumi.shot.ScreenshotTest
import com.most.products.application.core.composable.ProductDescriptionDialogContent
import com.most.products.application.core.theme.space8Dp
import com.most.products.application.ui.home.compose.body.HomeBodyContent
import com.most.products.application.ui.home.compose.header.HomeHeaderContent
import com.most.products.application.ui.home.model.HomeBodyModel
import com.most.products.application.ui.home.model.HomeHeaderUiModel
import org.junit.Rule
import org.junit.Test

class MainScreenshotTest: ScreenshotTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun testHomeHeaderContent() {
        composeRule.setContent {
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
        compareScreenshot(composeRule)
    }

    @Test
    fun testHomeBodyContent() {
        composeRule.setContent {
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
        compareScreenshot(composeRule)
    }

    @Test
    fun testProductDescriptionDialogContent() {
        composeRule.setContent {
            ProductDescriptionDialogContent(
                description = "description",
                onDismiss = {}
            )
        }
        compareScreenshot(composeRule)
    }
}