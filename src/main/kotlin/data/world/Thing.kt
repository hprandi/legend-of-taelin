package data.world

sealed interface Thing {
    val image: String

    object Grass : Thing {
        override val image: String = "file:resources/grass.png"
    }
}

