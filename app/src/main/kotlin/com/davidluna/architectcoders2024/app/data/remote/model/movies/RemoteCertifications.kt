package com.davidluna.architectcoders2024.app.data.remote.model.movies

import com.google.gson.annotations.SerializedName


data class RemoteCertifications(
    @SerializedName("AR")
    val argentina: List<RemoteCertificationDetail>,
    @SerializedName("AU")
    val australia: List<RemoteCertificationDetail>,
    @SerializedName("BG")
    val bulgaria: List<RemoteCertificationDetail>,
    @SerializedName("BR")
    val brazil: List<RemoteCertificationDetail>,
    @SerializedName("CA")
    val canada: List<RemoteCertificationDetail>,
    @SerializedName("CA-QC")
    val canadaQuebec: List<RemoteCertificationDetail>,
    @SerializedName("CH")
    val switzerland: List<RemoteCertificationDetail>,
    @SerializedName("CZ")
    val czechRepublic: List<RemoteCertificationDetail>,
    @SerializedName("DE")
    val germany: List<RemoteCertificationDetail>,
    @SerializedName("DK")
    val denmark: List<RemoteCertificationDetail>,
    @SerializedName("ES")
    val spain: List<RemoteCertificationDetail>,
    @SerializedName("FI")
    val finland: List<RemoteCertificationDetail>,
    @SerializedName("FR")
    val france: List<RemoteCertificationDetail>,
    @SerializedName("GB")
    val unitedKingdom: List<RemoteCertificationDetail>,
    @SerializedName("GR")
    val greece: List<RemoteCertificationDetail>,
    @SerializedName("HK")
    val hongKong: List<RemoteCertificationDetail>,
    @SerializedName("HU")
    val hungary: List<RemoteCertificationDetail>,
    @SerializedName("ID")
    val indonesia: List<RemoteCertificationDetail>,
    @SerializedName("IE")
    val ireland: List<RemoteCertificationDetail>,
    @SerializedName("IL")
    val israel: List<RemoteCertificationDetail>,
    @SerializedName("IN")
    val india: List<RemoteCertificationDetail>,
    @SerializedName("IT")
    val italy: List<RemoteCertificationDetail>,
    @SerializedName("JP")
    val japan: List<RemoteCertificationDetail>,
    @SerializedName("KR")
    val southKorea: List<RemoteCertificationDetail>,
    @SerializedName("LT")
    val lithuania: List<RemoteCertificationDetail>,
    @SerializedName("LU")
    val luxembourg: List<RemoteCertificationDetail>,
    @SerializedName("LV")
    val latvia: List<RemoteCertificationDetail>,
    @SerializedName("MO")
    val macau: List<RemoteCertificationDetail>,
    @SerializedName("MX")
    val mexico: List<RemoteCertificationDetail>,
    @SerializedName("MY")
    val malaysia: List<RemoteCertificationDetail>,
    @SerializedName("NL")
    val netherlands: List<RemoteCertificationDetail>,
    @SerializedName("NO")
    val norway: List<RemoteCertificationDetail>,
    @SerializedName("NZ")
    val newZealand: List<RemoteCertificationDetail>,
    @SerializedName("PH")
    val philippines: List<RemoteCertificationDetail>,
    @SerializedName("PR")
    val puertoRico: List<RemoteCertificationDetail>,
    @SerializedName("PT")
    val portugal: List<RemoteCertificationDetail>,
    @SerializedName("RU")
    val russia: List<RemoteCertificationDetail>,
    @SerializedName("SE")
    val sweden: List<RemoteCertificationDetail>,
    @SerializedName("SG")
    val singapore: List<RemoteCertificationDetail>,
    @SerializedName("SK")
    val slovakia: List<RemoteCertificationDetail>,
    @SerializedName("TH")
    val thailand: List<RemoteCertificationDetail>,
    @SerializedName("TR")
    val turkey: List<RemoteCertificationDetail>,
    @SerializedName("TW")
    val taiwan: List<RemoteCertificationDetail>,
    @SerializedName("US")
    val unitedStates: List<RemoteCertificationDetail>,
    @SerializedName("VI")
    val virginIslands: List<RemoteCertificationDetail>,
    @SerializedName("ZA")
    val southAfrica: List<RemoteCertificationDetail>
)