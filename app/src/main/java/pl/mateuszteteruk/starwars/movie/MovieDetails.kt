package pl.mateuszteteruk.starwars.movie

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun MovieDetails(
    movie: String,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
) {

    MovieContent(
        movieTitle = movie,
        player = viewModel.player,
    )
}

@Composable
private fun MovieContent(
    movieTitle: String,
    player: Player,
) {
    val config = LocalConfiguration.current
    val orientation = remember(config) { config.orientation }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(
                if (orientation == 1) {
                    Modifier.verticalScroll(rememberScrollState())
                } else {
                    Modifier
                }
            ),
    ) {

        var lifecycle by remember {
            mutableStateOf(Lifecycle.Event.ON_CREATE)
        }
        val lifecycleOwner = LocalLifecycleOwner.current

        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                lifecycle = event
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }

        Box(modifier = Modifier) {
            AndroidView(
                factory = { context ->
                    PlayerView(context).also {
                        it.player = player
                    }
                },
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxWidth()
                    .aspectRatio(matchHeightConstraintsFirst = true, ratio = 16 / 9f),
                update = {
                    when (lifecycle) {
                        Lifecycle.Event.ON_PAUSE -> {
                            it.onPause()
                            it.player?.pause()
                        }
                        Lifecycle.Event.ON_RESUME -> {
                            it.onResume()
                        }
                        else -> Unit
                    }
                }
            )

            val context = LocalContext.current
            val activity = context as Activity
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    activity.requestedOrientation =
                        if (orientation == 1) {
                            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                        } else {
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }
                },
                content = {
                    Text(text = "Switch orientation")
                },
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = movieTitle
        )
        (1..10).forEach {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        listOf(
                            Color.Blue,
                            Color.Red,
                            Color.Gray,
                            Color.Green,
                            Color.Cyan,
                            Color.Magenta,
                        ).random()
                    )
                    .height(56.dp)
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                text = "Random text:$it"
            )
        }
    }
}
