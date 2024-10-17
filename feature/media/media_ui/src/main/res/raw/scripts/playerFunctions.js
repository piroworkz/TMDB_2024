
function seekTo(startSeconds) {
    player.seekTo(startSeconds, true);
}

function pauseVideo() {
    player.pauseVideo();
}

function playVideo() {
    player.playVideo();
}

function loadVideo(videoId, startSeconds) {
    player.loadVideoById(videoId, startSeconds);
    YouTubePlayerBridge.sendVideoId(videoId);
}

function cueVideo(videoId, startSeconds) {
    player.cueVideoById(videoId, startSeconds);
    YouTubePlayerBridge.sendVideoId(videoId);
}

function mute() {
    player.mute();
}

function unMute() {
    player.unMute();
}

function setVolume(volumePercent) {
    player.setVolume(volumePercent);
}

function setPlaybackRate(playbackRate) {
    player.setPlaybackRate(playbackRate);
}

function toggleFullscreen() {
    player.toggleFullscreen();
}

function nextVideo() {
    player.nextVideo();
}

function previousVideo() {
    player.previousVideo();
}

function playVideoAt(index) {
    player.playVideoAt(index);
}

function setLoop(loop) {
    player.setLoop(loop);
}

function setShuffle(shuffle) {
    player.setShuffle(shuffle);
}
