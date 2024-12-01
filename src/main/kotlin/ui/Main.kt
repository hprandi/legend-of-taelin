package com.hprandi.lot.ui

import com.hprandi.lot.world.World
import javafx.application.Application
import javafx.geometry.Rectangle2D
import javafx.scene.Scene
import javafx.stage.Screen
import javafx.stage.Stage
import javafx.stage.StageStyle
import ui.WorldDrawer

class GameApplication : Application() {

    private val world = World()

    override fun start(primaryStage: Stage) {
        val screenBounds: Rectangle2D = Screen.getPrimary().visualBounds
        val squareSize = screenBounds.height * 0.05
        val sceneSize = World.size * squareSize
        val worldDrawer = WorldDrawer()
        val root = worldDrawer.draw(world, squareSize)
        val scene = Scene(root, sceneSize, sceneSize)
        setupStage(primaryStage, scene)
    }

    private fun setupStage(primaryStage: Stage, scene: Scene) {
        primaryStage.scene = scene
        primaryStage.title = "Legend of Taelin"
        primaryStage.isFullScreen = false
        primaryStage.isResizable = false
        primaryStage.initStyle(StageStyle.UNDECORATED)
        primaryStage.show()
    }


}

fun main() {
    Application.launch(GameApplication::class.java)
}