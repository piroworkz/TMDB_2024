var UNSTARTED = "UNSTARTED";
var ENDED = "ENDED";
var PLAYING = "PLAYING";
var PAUSED = "PAUSED";
var BUFFERING = "BUFFERING";
var CUED = "CUED";

var YouTubePlayerBridge = window.YouTubePlayerBridge;
var player;
var timerId;

function onYouTubeIframeAPIReady(injectedPlayerVars) {
    YouTubePlayerBridge.sendYouTubeIFrameAPIReady();

    player = new YT.Player('youTubePlayerDOM', {
        height: '100%',
        width: '100%',
        events: {
            onReady: function (event) {
                YouTubePlayerBridge.sendReady()
            },
            onStateChange: function (event) {
                sendPlayerStateChange(event.data)
            },
            onPlaybackQualityChange: function (event) {
                YouTubePlayerBridge.sendPlaybackQualityChange(event.data)
            },
            onPlaybackRateChange: function (event) {
                YouTubePlayerBridge.sendPlaybackRateChange(event.data)
            },
            onError: function (error) {
                YouTubePlayerBridge.sendError(error.data)
            },
            onApiChange: function (event) {
                YouTubePlayerBridge.sendApiChange()
            }
        },
        playerVars: injectedPlayerVars
    })
}

function sendPlayerStateChange(playerState) {
    clearTimeout(timerId);

    switch (playerState) {
        case YT.PlayerState.INIT:
            initializePlayer(playerState.playerVars)
            return;

        case YT.PlayerState.UNSTARTED:
            sendStateChange(UNSTARTED);
            sendVideoIdFromPlaylistIfAvailable(player);
            return;

        case YT.PlayerState.ENDED:
            sendStateChange(ENDED);
            return;

        case YT.PlayerState.PLAYING:
            sendStateChange(PLAYING);
            startSendCurrentTimeInterval();
            sendVideoData(player);
            return;

        case YT.PlayerState.PAUSED:
            sendStateChange(PAUSED);
            return;

        case YT.PlayerState.BUFFERING:
            sendStateChange(BUFFERING);
            return;

        case YT.PlayerState.CUED:
            sendStateChange(CUED);
            return;
    }

    function sendVideoData(player) {
        var videoDuration = player.getDuration();
        YouTubePlayerBridge.sendVideoDuration(videoDuration);
    }

    function sendVideoIdFromPlaylistIfAvailable(player) {
        var playlist = player.getPlaylist();
        if (typeof playlist !== 'undefined' && Array.isArray(playlist) && playlist.length > 0) {
            var index = player.getPlaylistIndex();
            var videoId = playlist[index];
            YouTubePlayerBridge.sendVideoId(videoId);
        }
    }

    function sendStateChange(newState) {
        YouTubePlayerBridge.sendStateChange(newState)
    }

    function startSendCurrentTimeInterval() {
        timerId = setInterval(function () {
            YouTubePlayerBridge.sendVideoCurrentTime(player.getCurrentTime());
            YouTubePlayerBridge.sendVideoLoadedFraction(player.getVideoLoadedFraction());
        }, 100);
    }
}
