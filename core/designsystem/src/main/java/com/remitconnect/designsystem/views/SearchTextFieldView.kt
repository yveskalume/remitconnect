package com.remitconnect.designsystem.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.remitconnect.designsystem.theme.RemitConnectTheme
import com.remitconnect.designsystem.theme.labelGray
import com.remitconnect.designsystem.theme.surfaceGray

@Composable
fun SearchTextFieldView(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .height(40.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceGray),
        textStyle = MaterialTheme.typography.bodySmall,
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                ) {
                if (leadingIcon != null) {
                    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.labelGray) {
                        Box(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(16.dp)
                        ) {
                            leadingIcon()
                        }
                    }
                }
                if (placeholder != null && value.isEmpty()) {
                    CompositionLocalProvider(
                        LocalTextStyle provides MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.labelGray
                        )
                    ) {
                        placeholder()
                    }
                } else {
                    innerTextField()
                }
                if (trailingIcon != null) {
                   CompositionLocalProvider(
                       LocalContentColor provides MaterialTheme.colorScheme.labelGray
                   ) {
                       Box(
                           modifier = Modifier
                               .padding(start = 8.dp)
                               .size(16.dp)
                       ) {
                           trailingIcon()
                       }
                   }
                }
            }
        }
    )
//    TextField(
//        value = value,
//        onValueChange = onValueChange,
//        placeholder = if (placeholder == null) null else {
//            {
//                CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.bodySmall) {
//                    placeholder()
//                }
//            }
//        },
//        shape = MaterialTheme.shapes.small,
//        textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.labelGray),
//        colors = TextFieldDefaults.colors(
//            focusedIndicatorColor = Color.Transparent,
//            unfocusedIndicatorColor = Color.Transparent,
//            focusedContainerColor = MaterialTheme.colorScheme.surfaceGray,
//            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceGray,
//        ),
//        modifier = modifier.height(40.dp),
//        leadingIcon = if (leadingIcon != null) {
//            {
//                Box(modifier = Modifier.size(16.dp)) {
//                    leadingIcon()
//                }
//            }
//        } else null,
//        trailingIcon = trailingIcon
//    )
}

@Preview(showBackground = true)
@Composable
private fun TextFieldPreview() {
    RemitConnectTheme {
        SearchTextFieldView(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
            }
        )
    }
}