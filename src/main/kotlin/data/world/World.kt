package com.hprandi.lot.world

import data.world.Square

class World {
    private val map: List<List<Square>> = createMap()

    private fun createMap() = List(size) { createRow() }

    private fun createRow() = List(size) { Square() }

    fun getSquare(x: Int, y: Int): Square = map[y][x]

    companion object {
        val size = 20
    }
}
