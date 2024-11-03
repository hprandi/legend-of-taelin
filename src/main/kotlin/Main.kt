package com.hprandi.lot

import javafx.application.Application
import javafx.geometry.Rectangle2D
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCode
import javafx.scene.layout.StackPane
import javafx.scene.layout.TilePane
import javafx.stage.Screen
import javafx.stage.Stage

class GameApplication : Application() {
    override fun start(primaryStage: Stage) {
        // Get the primary screen bounds
        val screenBounds: Rectangle2D = Screen.getPrimary().visualBounds

        // Load the image
        val grass = Image("file:resources/grass.png")
        val barbarian = Image("file:resources/barbarian.gif")
        // Create a TilePane to tile the grass image
        val tilePane = TilePane()
        tilePane.prefColumns = (screenBounds.width / grass.width).toInt()
        tilePane.prefRows = (screenBounds.height / grass.height).toInt()

        // Fill the TilePane with ImageViews of the grass image
        for (i in 0 until tilePane.prefColumns) {
            for (j in 0 until tilePane.prefRows) {
                val grassImageView = ImageView(grass)
                tilePane.children.add(grassImageView)
            }
        }

        // Create an ImageView for the barbarian image
        val barbarianImageView = ImageView(barbarian)

        // Create a StackPane to layer the TilePane and the barbarian ImageView
        val root = StackPane(tilePane, barbarianImageView)

        // Create the scene with full screen dimensions
        val scene = Scene(root, screenBounds.width, screenBounds.height)

        // Set up the stage
        primaryStage.scene = scene
        primaryStage.title = "Full Screen Game"
        primaryStage.isFullScreen = true
        primaryStage.show()

        // Set initial position of the barbarian image
        barbarianImageView.translateX = 0.0
        barbarianImageView.translateY = 0.0

        // Add key event handlers to move the barbarian image
        scene.setOnKeyPressed { event ->
            when (event.code) {
                KeyCode.UP -> barbarianImageView.translateY -= 10
                KeyCode.DOWN -> barbarianImageView.translateY += 10
                KeyCode.LEFT -> barbarianImageView.translateX -= 10
                KeyCode.RIGHT -> barbarianImageView.translateX += 10
                else -> {}
            }
        }
    }
}

fun main() {
    Application.launch(GameApplication::class.java)
}