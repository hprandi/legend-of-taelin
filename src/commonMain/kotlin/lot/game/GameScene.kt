package lot.game

import korlibs.event.*
import korlibs.image.atlas.*
import korlibs.image.format.*
import korlibs.io.file.std.*
import korlibs.korge.scene.*
import korlibs.korge.view.*
import korlibs.korge.view.animation.*
import korlibs.time.*

class GameScene : Scene() {
    val atlas = MutableAtlasUnit(2048, 2048)
    lateinit var characters: ImageDataContainer
    lateinit var character: ImageDataView

    override suspend fun SContainer.sceneMain() {
        scale(2.0)
//        val char = image(resourcesVfs["char.png"].readBitmap()) {
//            anchor(.5, .5)
//            scale(1)
//            position(256, 256)
//        }

        characters = resourcesVfs["vampire.ase"].readImageDataContainer(ASE.toProps(), atlas = atlas)
        character = imageDataView(characters["vampire"], "down", playing = false, smoothing = false) {
            xy(256, 256)
        }

        stage!!.controlWithKeyboard(character)
    }

        private fun Stage.controlWithKeyboard(
            char: ImageDataView,
            up: Key = Key.UP,
            right: Key = Key.RIGHT,
            down: Key = Key.DOWN,
            left: Key = Key.LEFT,
        ) {
            addUpdater { dt ->
                val speed = 2.0 * (dt / 16.0.milliseconds)
                var dx = 0.0
                var dy = 0.0

                val pressingLeft = keys[left]
                val pressingRight = keys[right]
                val pressingUp = keys[up]
                val pressingDown = keys[down]
                if (pressingLeft) char.x -= 1.0
                if (pressingRight) char.x += 1.0
                if (pressingUp) char.y -= 1.0
                if (pressingDown) char.y += 1.0

                char.animation = when {
                    pressingLeft -> "left"
                    pressingRight -> "right"
                    pressingUp -> "up"
                    pressingDown -> "down"
                    else -> char.animation
                }
                if (pressingLeft || pressingRight || pressingUp || pressingDown) {
                    char.play()
                } else {
                    char.stop()
                    char.rewind()
                }
            }
        }
    }
