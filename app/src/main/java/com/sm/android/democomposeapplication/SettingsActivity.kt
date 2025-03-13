package com.sm.android.democomposeapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sm.android.democomposeapplication.ui.theme.DemoComposeApplicationTheme
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext


class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*        setContent {
            DemoComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }*/

        setContent {
            SettingsScreen()
        }
    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    DemoComposeApplicationTheme {
        Greeting("Android")
    }
}*/




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    var isDarkTheme by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings", fontWeight = FontWeight.Bold) })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            RemoveAdsCard()
            SettingsList(isDarkTheme) { isDarkTheme = it }
        }
    }
}

@Composable
fun RemoveAdsCard() {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Remove Ads", fontWeight = FontWeight.Bold, color = Color.White)
            Text("Go Pro For Ads Free Experience.", fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                Toast.makeText(context, "Premium Click", Toast.LENGTH_SHORT).show()
            /* Handle Go Premium Click */ }) {
                Text("GO Premium")
            }
        }
    }
}

@Composable
fun SettingsList(isDarkTheme: Boolean, onThemeToggle: (Boolean) -> Unit) {
    val settingsOptions = listOf(
        "Dark Theme" to R.drawable.ic_dark_theme,
        "Battery Info" to R.drawable.ic_battery,
        "Device Info" to R.drawable.ic_device,
        "Languages" to R.drawable.ic_language,
        "Rate Us" to R.drawable.ic_rate,
        "Privacy Policy" to R.drawable.ic_privacy,
        "More Apps (AD)" to R.drawable.ic_apps,
        "Share App" to R.drawable.ic_share,
        "App Version" to R.drawable.ic_version
    )

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(settingsOptions) { (title, icon) ->
            SettingItem(title, icon, isDarkTheme, onThemeToggle)
            Divider()
        }
    }
}

@Composable
fun SettingItem(title: String, iconRes: Int, isDarkTheme: Boolean, onThemeToggle: (Boolean) -> Unit) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Toast.makeText(context, "$title clicked", Toast.LENGTH_SHORT).show()
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            tint = Color.Green,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, fontSize = 16.sp, modifier = Modifier.weight(1f))
        if (title == "Dark Theme") {
            Switch(checked = isDarkTheme, onCheckedChange = onThemeToggle)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen()
}
