package co.vijay.class9thscience.ui.settings

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import co.vijay.class9thscience.R

@Composable
fun TermsOfServiceScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextWithIconStart(text = stringResource(id = R.string.terms_of_service_title), navController)
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = R.string.terms_of_service_content),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
        )
        LinkEmail(text = stringResource(id = R.string.email))
    }
}
