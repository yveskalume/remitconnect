package com.remitconnect.designsystem.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.remitconnect.designsystem.theme.RemitConnectTheme
import com.remitconnect.designsystem.theme.labelGray
import com.remitconnect.designsystem.theme.surfaceGray

@Composable
fun ContactItemView(
    name: String,
    mobileWallet: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .then(modifier)
            .fillMaxWidth()

            .padding(vertical = 18.5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(40.dp),
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colorScheme.surfaceGray
        ) {

        }
        Column {
            Text(text = name, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = mobileWallet,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.labelGray
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Rounded.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactItemPreview() {
    RemitConnectTheme {
        Column {
            ContactItemView(
                name = "John Doe",
                mobileWallet = "Wave",
                modifier = Modifier.padding(horizontal = 24.dp),
                onClick = {}
            )
        }
    }
}