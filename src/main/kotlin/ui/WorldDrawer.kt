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
                val stackPane = StackPane()

                square.things.forEach {
                    val image = imageRepository.get(it)
                    val imageView = image.draw(squareSize)
                    stackPane.children.add(imageView)
                }

                stackPane.children.add(drawBorder(squareSize))

                tilePane.children.add(stackPane)
            }
        }

        return tilePane
    }

    private fun drawBorder(squareSize: Double) =
        Rectangle(squareSize, squareSize).apply {
            stroke = Color.BLACK
            strokeWidth = 2.0
            fill = Color.TRANSPARENT
        }
}

class ImageRepository {
    private val images: MutableMap<Thing, Image> = mutableMapOf()

    fun get(thing: Thing): Image =
        images.getOrPut(thing) { Image(thing.image) }
}

private fun Image.draw(squareSize: Double) =
    ImageView(this).apply {
        fitWidth = squareSize
        fitHeight = squareSize
    }