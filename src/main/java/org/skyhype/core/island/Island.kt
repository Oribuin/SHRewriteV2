package org.skyhype.core.island

import java.util.*

data class Island(var owner: UUID, var settings: Settings) {

    enum class Settings {
        EXPLOSIONS,
    }
}
