package co.vijay.class9thscience.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UnitList(itemClicked:  (unitNumber : Int) -> Unit) {

    val dataList = remember {
        mutableStateOf<List<ItemsData>>(emptyList())
    }
    LaunchedEffect(Unit) {
        dataList.value = listOf(
            ItemsData(1,"Matter – Its Nature and Behaviour", 23),
            ItemsData(2,"Organization in the Living World", 20),
            ItemsData(3,"Motion, Force, and Work", 27),
            ItemsData(4,"Our Environment", 6),
            ItemsData(5,"Food – Food Production", 4)
        )
    }

    LazyColumn(verticalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.fillMaxSize()) {
        items(dataList.value) {
            UnitItem(unitNumber = it.unitNumber, unitName = it.unitName, unitMarks = it.unitMarks, itemClicked)
        }
    }

}

@Composable
fun UnitItem( unitNumber: Int, unitName: String, unitMarks: Int, itemClicked : (unitNumber: Int) -> Unit) {
    Card(
        modifier = Modifier
            .clickable { itemClicked(unitNumber) }
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 14.dp), // Increased vertical padding for more spacing
        elevation = CardDefaults.cardElevation(12.dp),
        shape = RoundedCornerShape(12.dp), // Softer corners for a modern touch
        colors = CardDefaults.cardColors(containerColor = Color.White) // Ensure a clean white background for the card
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFF0F4FF),
                            Color(0xFFF7F9FF)
                        )
                    )
                )
                .padding(16.dp) // Balanced padding inside the card
        ) {
            Text(
                text = "Unit: $unitNumber",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    shadow = Shadow( // Move the shadow here inside TextStyle
                        color = Color.Gray.copy(alpha = 0.5f),
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    )
                ),
                color = Color(0xFF304FFE),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = unitName,
                style = MaterialTheme.typography.bodyLarge.copy(
                    lineHeight = 24.sp
                ),
                color = Color(0xFF212121)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Marks: $unitMarks",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

data class ItemsData(val unitNumber: Int, val unitName: String, val unitMarks: Int)