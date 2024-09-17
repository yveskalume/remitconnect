package com.remitconnect.designsystem.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.remitconnect.designsystem.resources.Drawable
import com.remitconnect.designsystem.theme.RemitConnectTheme
import com.remitconnect.designsystem.theme.labelGray

@Composable
fun TransactionView(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            color = Color(0xFFEAF6FC),
            shape = MaterialTheme.shapes.small
        ) {
            Icon(
                painter = painterResource(Drawable.arrowUpRight),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(12.dp)
                    .size(16.dp)
            )
        }
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(
                text = "Sent to",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.labelGray
            )
            Text(
                text = "Ralph Edwards",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "150€",
            style = MaterialTheme.typography.bodySmall
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun TransactionPreview() {
    RemitConnectTheme {
        TransactionView()
    }
}