package com.hprandi.lot.ui

import com.hprandi.lot.world.World
import data.world.Thing
import javafx.application.Application
import javafx.geometry.Rectangle2D
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Pane
import javafx.stage.Screen
import javafx.stage.Stage
import javafx.stage.StageStyle
import ui.WorldDrawer

class GameApplication : Application() {

    private val world = World()
    private lateinit var stage: Stage
    private lateinit var scene: Scene
    private val worldDrawer = WorldDrawer()

    private var barbarianX = 10
    private var barbarianY = 10

    override fun start(primaryStage: Stage) {
        stage = primaryStage
        val squareSize = calculateSquareSize()
        val sceneSize = World.size * squareSize

        world.getSquare(barbarianX, barbarianY).things.add(Thing.Barbarian)

        scene = Scene(Pane(), sceneSize, sceneSize)
        scene.setOnKeyPressed { moveBarbarian(it) }
        stage.setup(scene)
        updateUi()
    }

    private fun moveBarbarian(it: KeyEvent) {
        world.getSquare(barbarianX, barbarianY).things.removeLast()

        when (it.code) {
            KeyCode.UP -> barbarianY--
            KeyCode.DOWN -> barbarianY++
            KeyCode.LEFT -> barbarianX--
            KeyCode.RIGHT -> barbarianX++
            else -> {}
        }
        world.getSquare(barbarianX, barbarianY).things.add(Thing.Barbarian)
        updateUi()
    }

    private fun updateUi() {
        scene.root = worldDrawer.draw(world, calculateSquareSize())
    }

    private fun calculateSquareSize(): Double {
        val screenBounds: Rectangle2D = Screen.getPrimary().visualBounds
        val squareSize = screenBounds.height * 0.05
        return squareSize
    }

    private fun Stage.setup(scene: Scene) {
        this.scene = scene
        title = "Legend of Taelin"
        isFullScreen = false
        isResizable = false
        initStyle(StageStyle.UNDECORATED)
        show()
    }


}

fun main() {
    Application.launch(GameApplication::class.java)
}