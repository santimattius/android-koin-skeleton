package com.santimattius.basic.skeleton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.santimattius.basic.skeleton.ui.component.AppBar
import com.santimattius.basic.skeleton.ui.component.BasicSkeletonContainer
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicSkeletonContainer {
                MainRoute()
            }
        }
    }
}

@Composable
fun MainRoute(
    viewModel: MainViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MainScreen(
        state = state,
        onMainAction = viewModel::sayHello,
    )
}

@Composable
fun MainScreen(
    state: MainUiState,
    onMainAction: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(id = R.string.app_name),
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onMainAction) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Message,
                    contentDescription = stringResource(R.string.text_desc_main_action)
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                Text(text = state.message)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BasicSkeletonContainer {
        MainScreen(
            state = MainUiState(isLoading = false, message = "Hello Android"),
            onMainAction = {},
        )
    }
}