package data.world

sealed interface Thing {
    val image: String

    data object Grass : Thing {
        override val image: String = "file:resources/grass.png"
    }

    data object Barbarian : Thing {
        override val image: String = "file:resources/barbarian.gif"
    }
}

