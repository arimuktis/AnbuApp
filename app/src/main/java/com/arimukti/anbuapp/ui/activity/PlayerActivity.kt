package com.arimukti.anbuapp.ui.activity


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.arimukti.anbuapp.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.analytics.AnalyticsCollector
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultAllocator
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.util.Clock
import com.google.android.exoplayer2.util.Log
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.custom_exo_player_view.*


class PlayerActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityPlayerBinding.inflate(layoutInflater)
    }
    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L
    private val playbackStateListener: Player.EventListener = playbackStateListener()
    private var flag: Boolean = false
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //View Binding
        setContentView(binding.root)
        initializePlayer()
//        requestedOrientation =
//            ActivityInfo.SCREEN_ORIENTATION_NOSENSOR or ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        close.setOnClickListener { onBackPressed() }
//        binding.video
        // remove title
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        binding.videoView.bt_fullscren.setOnClickListener {
//            if (flag) {
//                bt_fullscren.setImageDrawable(resources.getDrawable(R.drawable.exo_ic_fullscreen_enter))
//                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//                flag = false
//            } else {
//                bt_fullscren.setImageDrawable(resources.getDrawable(R.drawable.exo_ic_fullscreen_exit))
//                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//                flag = true
//            }
//        }
//        binding.apply {

//
//            videoView.player = player
//
//            videoView.setControlDispatcher(DefaultControlDispatcher(10000, 10000))
//            videoView.controllerShowTimeoutMs = 3000
//            videoView.player = player
//            videoView.hideController()
//        }

    }

    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
//            initializePlayer()
        }
    }

    public override fun onResume() {
        super.onResume()
        hideSystemUi()
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer()
        }
    }

    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.videoView.isVisible = false
        hideSystemUi()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
//            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    private fun releasePlayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentWindowIndex
            playWhenReady = this.playWhenReady
            removeListener(playbackStateListener)
            release()
        }
        player = null
    }


    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        binding.videoView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

//    private fun initializePlayer() {
////        val url = intent.getStringExtra("URL")
//        val url = "https://i.imgur.com/7bMqysJ.mp4"
//        //Load Control
//        val allocator = DefaultAllocator(true, C.DEFAULT_BUFFER_SEGMENT_SIZE)
//        val loadControl = DefaultLoadControl()
//
//        //Band Width Meter
//        val bandwidthMeter = DefaultBandwidthMeter.Builder(this).build()
//        val renderersFactory = DefaultRenderersFactory(this)
//        val clock = Clock.DEFAULT
//        val analyticsCollector = AnalyticsCollector(clock)
//        //Track Selector
//        val trackSelector = DefaultTrackSelector(this).apply {
//            setParameters(buildUponParameters().setMaxVideoSizeSd())
//        }
//
//
//        //data source factory
//        val factory = DefaultHttpDataSourceFactory("exoplayer_video")
//
//        //extractors factory
//        val extractorsFactory = DefaultExtractorsFactory()
//
//        //media source
////        val mediaItem: MediaItem = MediaItem.fromUri(url)
////        val mediaSource: MediaSource =
////            ExtractorsFactory { }(url, factory, extractorsFactory, null, null)
//
//
//        //simple exo player
//        val player = SimpleExoPlayer.Builder(this, renderersFactory, extractorsFactory)
//            .setTrackSelector(trackSelector)
//            .setBandwidthMeter(bandwidthMeter)
//            .setLoadControl(loadControl)
//            .setAnalyticsCollector(analyticsCollector)
//            .setHandleAudioBecomingNoisy(true)
//            .setSeekParameters(SeekParameters.CLOSEST_SYNC)
//            .build()
//            .also { exoPlayer ->
//                binding.videoView.player = exoPlayer
//                val mediaItem = MediaItem.fromUri(url)
//                exoPlayer.setMediaItem(mediaItem)
//                exoPlayer.playWhenReady = playWhenReady
//                exoPlayer.seekTo(currentWindow, playbackPosition)
//                exoPlayer.addListener(playbackStateListener)
//                exoPlayer.prepare()
//            }
//
//
//    }

    private fun initializePlayer() {
        val url = "https://i.imgur.com/7bMqysJ.mp4"
        val url2 = "https://www2184.ff-05.com/token=-vrApU_a9rEecLhlOau9Bw/1627551669/180.248.0.0/171/2/32/c1bfa1c0695ad8885fde9bc82fb8c322-720p.mp4"
        //Load Control
        val allocator = DefaultAllocator(true, C.DEFAULT_BUFFER_SEGMENT_SIZE)
        val loadControl = DefaultLoadControl()

        //Band Width Meter
        val bandwidthMeter = DefaultBandwidthMeter.Builder(this).build()
        val renderersFactory = DefaultRenderersFactory(this)
        val clock = Clock.DEFAULT
        val analyticsCollector = AnalyticsCollector(clock)
        //Track Selector
        val trackSelector = DefaultTrackSelector(this).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }
        player = SimpleExoPlayer.Builder(this)
            .setTrackSelector(trackSelector)
            .setBandwidthMeter(bandwidthMeter)
            .setLoadControl(loadControl)
            .setSeekParameters(SeekParameters.CLOSEST_SYNC)
            .setAnalyticsCollector(analyticsCollector)
            .build()

            .also { exoPlayer ->
                binding.videoView.player = exoPlayer
                val mediaItem = MediaItem.fromUri(url)
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentWindow, playbackPosition)

                val secondMediaItem = url2?.let { MediaItem.fromUri(it) }
                secondMediaItem?.let { exoPlayer.addMediaItem(it) }
                exoPlayer.addListener(playbackStateListener)
                exoPlayer.prepare()
            }


    }

    companion object {
        private const val TAG = "PlayerActivity"
    }

    private fun playbackStateListener() = object : Player.EventListener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            val stateString: String = when (playbackState) {
                ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"
                ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -"
                ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -"
                ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -"
                else -> "UNKNOWN_STATE             -"
            }

            val stateVisible: Unit = when (playbackState) {
                ExoPlayer.STATE_BUFFERING -> binding.progressBar.isVisible = true
                ExoPlayer.STATE_READY -> binding.progressBar.isVisible = false
                else -> binding.progressBar.isVisible = false
            }
            Log.d(TAG, "changed state to $stateString")
        }
    }
}