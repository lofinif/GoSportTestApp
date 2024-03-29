package com.lofinif.gosporttestapp.ui.compose.menu

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lofinif.gosporttestapp.R
import com.lofinif.gosporttestapp.data.menu.dto.AdsPoster
import com.lofinif.gosporttestapp.ui.compose.theme.GoToSportTypography
import com.lofinif.gosporttestapp.ui.menu.MenuViewModel
import com.lofinif.gosporttestapp.ui.menu.model.CategoryModel
import com.lofinif.gosporttestapp.ui.menu.model.FoodDetailsModel
import com.lofinif.gosporttestapp.ui.menu.state.MenuCategoryScreenState
import com.lofinif.gosporttestapp.ui.menu.state.MenuFoodScreenState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuScreen(
    viewModel: MenuViewModel = hiltViewModel()) {

    val stateCategory by viewModel.screenStateCategoryLiveData.observeAsState()
    val stateFood by viewModel.screenStateFoodLiveData.observeAsState()
    val stateAds by viewModel.postersFlow.collectAsStateWithLifecycle()
    viewModel.fetchCategory()

    Column() {
        TopBar()
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
        LazyColumn {
            item {
                ListAds(
                    stateAds
                )
            }

            stickyHeader {
                when (stateCategory) {
                    MenuCategoryScreenState.Loading -> {
                        CategoryListLoading()
                    }

                    MenuCategoryScreenState.Error -> {

                    }

                    is MenuCategoryScreenState.Loaded -> {
                        CategoryList(
                            categoryModels = (stateCategory as MenuCategoryScreenState.Loaded).model,
                            viewModel
                        )
                    }

                    null -> {}
                }
            }

            when (stateFood) {
                MenuFoodScreenState.Error -> {
                    item {
                        FoodListError()
                    }
                }

                MenuFoodScreenState.Loading -> {
                    item {
                        CategoryFoodLoading()
                        CategoryFoodLoading()
                        CategoryFoodLoading()
                    }
                }

                is MenuFoodScreenState.Loaded -> {
                    foodsList((stateFood as MenuFoodScreenState.Loaded).model)
                }

                null -> {}
            }
        }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    TopAppBar(
        title = {
            Text(text = "Москва",
                color = Color.Black,
                style = GoToSportTypography.titleLarge,
            )
            Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_vector_city_selector_12x8dp),
                contentDescription = "city selector",
                modifier = Modifier.padding(start = 86.dp, top = 8.dp)
            )
                },
        actions = { Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_vector_qr_code_24dp),
            contentDescription = "qr",
            modifier = Modifier.padding(end = 12.dp)
        )
                  },
    )

}

@Composable
fun ListAds(adsPosters: List<AdsPoster>, ){
    LazyRow(
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 16.dp,
            start = 16.dp,
            end = 16.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.background(Color(0xFFFFFFFF))
    )
    {
        items(adsPosters.size) {
            val adsPoster = adsPosters[it]
            ListAdsItem(adsPoster)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ListAdsItem(adsPoster: AdsPoster){
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier
            .width(300.dp)
            .height(109.5.dp)
    ) {
        GlideImage(
            model = adsPoster.source,
            contentDescription = "ads",
            modifier = Modifier
                .width(R.dimen.ad_poster_width.dp)
                .height(R.dimen.ad_poster_height.dp)
        )
    }
}

@Composable
fun CategoryList(categoryModels: List<CategoryModel>, viewModel: MenuViewModel){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
    ) {

    LazyRow(contentPadding = PaddingValues(
        bottom = 16.dp,
        start = 16.dp,
        end = 16.dp
    ),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
        items(categoryModels.size) {
            val categoryModel = categoryModels[it]
            ListCategoryItem(categoryModel, viewModel)
        }
    }
    }
}

@Composable
fun ListCategoryItem(categoryModel: CategoryModel, viewModel: MenuViewModel){
    Surface(
        modifier = Modifier
            .width(88.dp)
            .height(32.dp)
            .clip(RoundedCornerShape(6.dp))
            .padding(top = 8.dp),
        onClick = { viewModel.fetchFood(categoryModel)}
        ) {
        Text(
            text = categoryModel.category,
            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.padding(top = 5.dp),
            color = Color(0xFFC3C4C9)
        )
    }
}

fun LazyListScope.foodsList(foodsDetailsModel: List<FoodDetailsModel>){
        items(foodsDetailsModel.size) {
            val foodDetailsModel = foodsDetailsModel[it]
            ListFoodItem(foodDetailsModel)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ListFoodItem(foodDetailsModel: FoodDetailsModel){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (title, description, image, price, border) = createRefs()
        GlideImage(
            model = foodDetailsModel.image,
            contentDescription = "food image",
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
                .width(135.dp)
                .height(135.dp)
        )
        Text(text = foodDetailsModel.name,
            style = GoToSportTypography.bodyLarge,
            fontSize = 16.sp,
            modifier = Modifier.constrainAs(title){
                start.linkTo(image.end, margin = 22.dp)
                top.linkTo(parent.top, margin = 16.dp)

            }
        )
        Text(text = foodDetailsModel.description!!,
            style = GoToSportTypography.bodyMedium,
            color = Color(0xFFAAAAAD),
            modifier = Modifier.constrainAs(description){
                width = Dimension.preferredWrapContent
                linkTo(start = image.end, startMargin = 22.dp, end = parent.end, endMargin = 50.dp, bias = 0f )
                top.linkTo(title.bottom, margin = 5.dp)
            }
        )
        Card(shape = RoundedCornerShape(6.dp),
            border = BorderStroke(1.dp, Color(0xFFFD3A69)),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
            ),
            modifier = Modifier.constrainAs(price){
                linkTo(start = image.end, end = parent.end, endMargin = 16.dp, bias = 1f)
                linkTo(top = description.bottom, bottom = border.bottom, bottomMargin = 16.dp, bias = 1f)
            }
            ) {
            Text(text = "От 340 р",
                style = GoToSportTypography.titleSmall,
                color = Color(0xFFFD3A69),
                modifier =  Modifier.padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            )
        }
        HorizontalDivider(thickness = 1.dp, color = Color(0xFFF3F5F9),
            modifier = Modifier.constrainAs(border){
                linkTo(top = image.bottom, bottom = parent.bottom, topMargin = 16.dp)
            })
    }
}

@Composable
fun CategoryListLoading(){
    Row {
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = Color.LightGray,
            modifier = Modifier
                .padding(start = 8.dp)
                .width(88.dp)
                .height(32.dp)

        ) {}
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = Color.LightGray,
            modifier = Modifier
                .padding(start = 8.dp)
                .width(88.dp)
                .height(32.dp)

        ) {}
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = Color.LightGray,
            modifier = Modifier
                .padding(start = 8.dp)
                .width(88.dp)
                .height(32.dp)

        ) {}
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = Color.LightGray,
            modifier = Modifier
                .padding(start = 8.dp)
                .width(88.dp)
                .height(32.dp)

        ) {}
    }
}
@Preview
@Composable
fun CategoryFoodLoading(){
    ConstraintLayout(
    ) {
        val (title, description, image, secondDescription) = createRefs()
        Surface(
           color = Color.LightGray,
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                }
                .width(135.dp)
                .height(135.dp)
        ){}
        Surface(
            color = Color.LightGray,
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(image.end, margin = 22.dp)
                    top.linkTo(parent.top, margin = 16.dp)
                }
                .width(160.dp)
                .height(15.dp)
        ){}
        Surface(
            color = Color.LightGray,
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(title.bottom, margin = 5.dp)
                    start.linkTo(image.end, margin = 22.dp)
                }
                .width(120.dp)
                .height(15.dp)
        ){}
        Surface(
            color = Color.LightGray,
            modifier = Modifier
                .constrainAs(secondDescription) {
                    top.linkTo(description.bottom, margin = 5.dp)
                    start.linkTo(image.end, margin = 22.dp)
                }
                .width(90.dp)
                .height(15.dp)
        ){}
    }
}
@Preview
@Composable
fun CategoryListError() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Возникла ошибка",
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Ещё раз!",
            modifier = Modifier.padding(16.dp),
        )
    }
}
@Composable
fun FoodListError(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Возникла ошибка",
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Ещё раз!",
            modifier = Modifier.padding(16.dp)
        )
    }
}

