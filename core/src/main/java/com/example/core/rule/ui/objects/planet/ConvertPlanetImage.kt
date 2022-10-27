package com.example.core.rule.ui.objects.planet

import com.example.core.R

class ConvertPlanetImage {
    var planetIm = R.drawable.planet_1
    var planetString = CommandImage.PLANET9

    fun pLanetImage(planetImage: CommandImage) {
        planetString = planetImage

        if (planetImage == CommandImage.PLANET1) {
            planetIm = R.drawable.planet_1
        }
        if (planetImage == CommandImage.PLANET2) {
            planetIm = R.drawable.planet_2
        }
        if (planetImage == CommandImage.PLANET3) {
            planetIm = R.drawable.planet_3
        }
        if (planetImage == CommandImage.PLANET4) {
            planetIm = R.drawable.planet_4
        }
        if (planetImage == CommandImage.PLANET5) {
            planetIm = R.drawable.planet_5
        }
        if (planetImage == CommandImage.PLANET6) {
            planetIm = R.drawable.planet_6
        }
        if (planetImage == CommandImage.PLANET7) {
            planetIm = R.drawable.planet_7
        }
        if (planetImage == CommandImage.PLANET8) {
            planetIm = R.drawable.planet_8
        }
        if (planetImage == CommandImage.PLANET9) {
            planetIm = R.drawable.planet_9
        }
        if (planetImage == CommandImage.PLANET10) {
            planetIm = R.drawable.planet_10
        }

    }

    enum class CommandImage{
        PLANET1, PLANET2, PLANET3, PLANET4, PLANET5,
        PLANET6, PLANET7, PLANET8,PLANET9, PLANET10,
    }

}