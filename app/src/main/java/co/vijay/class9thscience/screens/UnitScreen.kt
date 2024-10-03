package co.vijay.class9thscience.screens

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import co.vijay.class9thscience.ui.theme.LightGreen
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UnitList(itemClicked:  (unitNumber : Int) -> Unit) {
    val dataList = listOf(
        ItemsData(1,"Matter – Its Nature and Behaviour", 23),
        ItemsData(2,"Organization in the Living World", 20),
        ItemsData(3,"Motion, Force, and Work", 27),
        ItemsData(4,"Our Environment", 6),
        ItemsData(5,"Food – Food Production", 4)
    )

    LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp), modifier = Modifier.fillMaxSize()) {
        items(dataList) {
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
            .padding(horizontal = 8.dp, vertical = 8.dp), // Horizontal and vertical padding for balance
        elevation = CardDefaults.cardElevation(12.dp), // Softer elevation for subtler depth
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF0F4FF)) // Slightly darker, more neutral background for the card
                .padding(16.dp) // More balanced padding inside the card
        ) {
            Text(
                text = "Unit: $unitNumber",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFF304FFE), // A stronger blue for contrast
            )
            Spacer(modifier = Modifier.height(4.dp)) // Adjust spacing between elements
            Text(
                text = unitName,
                style = MaterialTheme.typography.bodyLarge.copy(
                    lineHeight = 24.sp // Slightly more space between lines for readability
                ),
                color = Color(0xFF212121), // Darker color for unit name
            )
            Spacer(modifier = Modifier.height(6.dp)) // Extra space between unit name and marks text
            Text(
                text = "Marks: $unitMarks",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}

data class ItemsData(val unitNumber: Int, val unitName: String, val unitMarks: Int)