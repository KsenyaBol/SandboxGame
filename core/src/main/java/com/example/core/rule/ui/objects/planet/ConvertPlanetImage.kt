package com.example.core.rule.ui.objects.planet


class ConvertPlanetImage {
    var planetString = CommandImage.PLANET9

    fun pLanetImage(planetImage: CommandImage) {

        planetString = planetImage

    }

    enum class CommandImage{
        PLANET1, PLANET2, PLANET3, PLANET4, PLANET5,
        PLANET6, PLANET7, PLANET8,PLANET9, PLANET10,
    }

}