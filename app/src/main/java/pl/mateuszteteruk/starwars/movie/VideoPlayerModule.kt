package pl.mateuszteteruk.starwars.movie

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object VideoPlayerModule {

    @Provides
    @ViewModelScoped
    fun providesPlayer(@ApplicationContext context: Context): Player =
        ExoPlayer.Builder(context)
            .build()
            .also {
                val url = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
                it.addMediaItem(MediaItem.fromUri(Uri.parse(url)))
            }

}
