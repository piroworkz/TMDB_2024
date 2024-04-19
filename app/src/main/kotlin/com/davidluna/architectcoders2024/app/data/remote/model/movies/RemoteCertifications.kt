package com.davidluna.architectcoders2024.app.data.remote.model.movies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCertifications(
    @SerialName("AR")
    val argentina: List<RemoteCertificationDetail>,
    @SerialName("AU")
    val australia: List<RemoteCertificationDetail>,
    @SerialName("BG")
    val bulgaria: List<RemoteCertificationDetail>,
    @SerialName("BR")
    val brazil: List<RemoteCertificationDetail>,
    @SerialName("CA")
    val canada: List<RemoteCertificationDetail>,
    @SerialName("CA-QC")
    val canadaQuebec: List<RemoteCertificationDetail>,
    @SerialName("CH")
    val switzerland: List<RemoteCertificationDetail>,
    @SerialName("CZ")
    val czechRepublic: List<RemoteCertificationDetail>,
    @SerialName("DE")
    val germany: List<RemoteCertificationDetail>,
    @SerialName("DK")
    val denmark: List<RemoteCertificationDetail>,
    @SerialName("ES")
    val spain: List<RemoteCertificationDetail>,
    @SerialName("FI")
    val finland: List<RemoteCertificationDetail>,
    @SerialName("FR")
    val france: List<RemoteCertificationDetail>,
    @SerialName("GB")
    val unitedKingdom: List<RemoteCertificationDetail>,
    @SerialName("GR")
    val greece: List<RemoteCertificationDetail>,
    @SerialName("HK")
    val hongKong: List<RemoteCertificationDetail>,
    @SerialName("HU")
    val hungary: List<RemoteCertificationDetail>,
    @SerialName("ID")
    val indonesia: List<RemoteCertificationDetail>,
    @SerialName("IE")
    val ireland: List<RemoteCertificationDetail>,
    @SerialName("IL")
    val israel: List<RemoteCertificationDetail>,
    @SerialName("IN")
    val india: List<RemoteCertificationDetail>,
    @SerialName("IT")
    val italy: List<RemoteCertificationDetail>,
    @SerialName("JP")
    val japan: List<RemoteCertificationDetail>,
    @SerialName("KR")
    val southKorea: List<RemoteCertificationDetail>,
    @SerialName("LT")
    val lithuania: List<RemoteCertificationDetail>,
    @SerialName("LU")
    val luxembourg: List<RemoteCertificationDetail>,
    @SerialName("LV")
    val latvia: List<RemoteCertificationDetail>,
    @SerialName("MO")
    val macau: List<RemoteCertificationDetail>,
    @SerialName("MX")
    val mexico: List<RemoteCertificationDetail>,
    @SerialName("MY")
    val malaysia: List<RemoteCertificationDetail>,
    @SerialName("NL")
    val netherlands: List<RemoteCertificationDetail>,
    @SerialName("NO")
    val norway: List<RemoteCertificationDetail>,
    @SerialName("NZ")
    val newZealand: List<RemoteCertificationDetail>,
    @SerialName("PH")
    val philippines: List<RemoteCertificationDetail>,
    @SerialName("PR")
    val puertoRico: List<RemoteCertificationDetail>,
    @SerialName("PT")
    val portugal: List<RemoteCertificationDetail>,
    @SerialName("RU")
    val russia: List<RemoteCertificationDetail>,
    @SerialName("SE")
    val sweden: List<RemoteCertificationDetail>,
    @SerialName("SG")
    val singapore: List<RemoteCertificationDetail>,
    @SerialName("SK")
    val slovakia: List<RemoteCertificationDetail>,
    @SerialName("TH")
    val thailand: List<RemoteCertificationDetail>,
    @SerialName("TR")
    val turkey: List<RemoteCertificationDetail>,
    @SerialName("TW")
    val taiwan: List<RemoteCertificationDetail>,
    @SerialName("US")
    val unitedStates: List<RemoteCertificationDetail>,
    @SerialName("VI")
    val virginIslands: List<RemoteCertificationDetail>,
    @SerialName("ZA")
    val southAfrica: List<RemoteCertificationDetail>
)