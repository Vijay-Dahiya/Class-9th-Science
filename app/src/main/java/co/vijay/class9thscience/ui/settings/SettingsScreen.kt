package co.vijay.class9thscience.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import co.vijay.class9thscience.R
import co.vijay.class9thscience.utils.Routes

@Composable
fun SettingsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TextWithIconStart(text = stringResource(id = R.string.settings_tittle), navController)

        SettingsOptionCard(stringResource(id = R.string.privacy_policy_title)) {
            navController.navigate(Routes.PRIVACY_POLICY)
        }
        SettingsOptionCard(stringResource(id = R.string.about_us_title)) {
            navController.navigate(Routes.ABOUT_US)
        }
        SettingsOptionCard(stringResource(id = R.string.terms_of_service_title)) {
            navController.navigate(Routes.TERMS_OF_SERVICES)
        }
        SettingsOptionCard(stringResource(id = R.string.contact_us_title)) {
            navController.navigate(Routes.CONTACT_US)
        }
        SettingsOptionCard(stringResource(id = R.string.ad_disclosure_title)) {
            navController.navigate(Routes.AD_DISCLOSURE)
        }
    }
}

@Composable
fun SettingsOptionCard(optionText: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = optionText,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
        }
    }
}