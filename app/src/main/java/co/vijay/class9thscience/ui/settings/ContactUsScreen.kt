package co.vijay.class9thscience.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import co.vijay.class9thscience.R

@Composable
fun ContactUsScreen(navController: NavHostController) {
    val email = stringResource(id = R.string.email)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextWithIconStart(text = "Contact Us",navController)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = """
                We'd love to hear from you! For questions, suggestions, or support, 
                please email us at: .
            """.trimIndent(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
        )
        LinkEmail(text = email)
    }
}
