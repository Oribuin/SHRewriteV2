package org.skyhype.core.island

import java.util.*

data class Member(val uuid: UUID) {
    var personalIslands = mutableListOf<Island>()
    var friendIslands = mutableListOf<Island>()
}
