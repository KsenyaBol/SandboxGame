package com.example.core.rule.ui.objects.planet

import com.example.core.rule.ui.objects.space.Space

data class PlanetObject (

    override var planetX: Int,
    override var planetY: Int,
    override var planetImage: Space.PlanetImage,
    override var planetInfect: Int,
    override var planetSatiety: Int,
    override var age: Int

    ): Planet