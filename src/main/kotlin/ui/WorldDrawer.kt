package ui

import com.hprandi.lot.world.World
import data.world.Thing
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane
import javafx.scene.layout.TilePane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class WorldDrawer {
    private val imageRepository = ImageRepository()

    fun draw(world: World, squareSize: Double): TilePane {
        val tilePane = TilePane()

        repeat(World.size) { x ->
            repeat(World.size) { y ->
                val square = world.getSquare(x, y)

                val firstThing = square.things.first()
                val image = imageRepository.get(firstThing)
                val grassImageView = ImageView(image).apply {
                    fitWidth = squareSize
                    fitHeight = squareSize
                }

                val border = Rectangle(squareSize, squareSize).apply {
                    stroke = Color.BLACK
                    strokeWidth = 2.0
                    fill = Color.TRANSPARENT
                }

                tilePane.children.add(StackPane(grassImageView, border))
            }
        }

        return tilePane
    }
}

class ImageRepository {
    private val images: MutableMap<Thing, Image> = mutableMapOf()

    fun get(thing: Thing): Image =
        images.getOrPut(thing) { Image(thing.image) }
}