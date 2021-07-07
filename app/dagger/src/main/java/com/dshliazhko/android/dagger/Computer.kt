package com.dshliazhko.android.dagger


class Processor {
    override fun toString() = "AB2D"
}
class MotherBoard {
    override fun toString() = "X7 37"
}
class RAM {
    override fun toString() = "16GB"
}
data class Computer (
    val processor: Processor,
    val motherBoard : MotherBoard,
    val ram: RAM,
)