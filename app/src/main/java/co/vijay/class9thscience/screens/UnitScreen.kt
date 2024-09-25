package co.vijay.class9thscience.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UnitList(modifier: Modifier = Modifier, itemClicked: (unitNumber : Int) -> Unit) {
    val dataList = listOf(
        ItemsData(1,"Matter – Its Nature and Behaviour", 23),
        ItemsData(2,"Organization in the Living World", 20),
        ItemsData(3,"Motion, Force, and Work", 27),
        ItemsData(4,"Our Environment", 6),
        ItemsData(5,"Food – Food Production", 4)
    )

    LazyColumn {
        items(dataList) {
            UnitItem(unitNumber = it.unitNumber, unitName = it.unitName, unitMarks = it.unitMarks, itemClicked)
        }
    }

}

@Composable
fun UnitItem( unitNumber: Int, unitName: String, unitMarks: Int, itemClicked : (unitNumber: Int) -> Unit) {
    Column(modifier = Modifier.padding(0.dp, 8.dp).clickable { itemClicked(unitNumber) }) {
        Text(text = "Unit : $unitNumber (Marks : $unitMarks)",  style =  MaterialTheme.typography.titleLarge)
        Text(text = unitName, style = MaterialTheme.typography.bodyLarge)
    }
}

data class ItemsData(val unitNumber: Int, val unitName: String, val unitMarks: Int)