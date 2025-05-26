package me.unidok.random

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Перечисление категорий
enum class Category(
    val displayName: String
) {
    NUMBER("Число") {
        @Composable
        override fun screen() = RandomNumberGenerator()
    },

    UUID("UUID") {
        @Composable
        override fun screen() = RandomUUIDGenerator()
    };

    @Composable
    abstract fun screen()
}

@Composable
fun CategorySelectionScreen() {
    var selectedCategory by remember { mutableStateOf(Category.NUMBER) }

    Row(modifier = Modifier.fillMaxSize()) {
        // Список категорий слева
        CategoryList(
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it },
            modifier = Modifier
                .width(200.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colors.surface)
        )

        // Разделитель списка категорий и экрана
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(2.dp),
            color = MaterialTheme.colors.primaryVariant
        )

        // Экран выбранной категории
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            selectedCategory.screen()
        }
    }
}

// Отображение списка категорий
@Composable
fun CategoryList(
    selectedCategory: Category,
    onCategorySelected: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items(Category.entries) { category ->
            CategoryItem(
                category = category,
                isSelected = category == selectedCategory,
                onCategorySelected = onCategorySelected
            )

            Divider(
                thickness = 1.dp,
                color = MaterialTheme.colors.primaryVariant
            )
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    isSelected: Boolean,
    onCategorySelected: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    // Цвета для выбранной и невыбранной категорий
    val backgroundColor = if (isSelected) {
        MaterialTheme.colors.primary
    } else {
        Color.Transparent
    }

    val textColor = if (isSelected) {
        MaterialTheme.colors.onPrimary // Белый текст на primary-цвете
    } else {
        MaterialTheme.colors.onSurface // Стандартный цвет текста
    }

    TextButton(
        onClick = { onCategorySelected(category) },
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = backgroundColor
        ),
        contentPadding = PaddingValues(0.dp),
        shape = RectangleShape
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), // Горизонтальные отступы только для текста
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = category.displayName,
                color = textColor,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            )
        }
    }
}