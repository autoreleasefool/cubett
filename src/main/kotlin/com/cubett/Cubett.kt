/**
 * Main Cube Terminal Timer application.
 *
 * Created by Joseph Roque on 2017-10-24.
 */
package com.cubett

import kotlinx.coroutines.experimental.*
import com.cubett.util.Logger
import com.cubett.view.TimerView
import com.cubett.view.View

/**
 * Singleton application object.
 */
object Cubett {

    /** Name of the app. */
    const val APP_TITLE = "Cubett"

    /** Description of the app. */
    val APP_DESCRIPTION = """
    Welcome to the Cube Terminal Timer

    Enter - Start/stop the timer, or enter other input
    S - Open the app settings
    V - View your solve times
    T - Change the active tags
    q, quit, exit - Exit
    """.trimIndent()

    /** Current, active view. */
    private var currentView: View = TimerView()

    /** Indicates if the app is running. */
    private var isRunning: Boolean = false

    /** Active coroutine for rendering. */
    private var renderDeferred: Deferred<Unit>? = null

    /**
     * Main function.
     *
     * @param args command line args
     */
    @JvmStatic
    fun main(args: Array<String>) {
        handleCommandLineArgs(args)
        Cubett.init()
    }

    /**
     * Process command line arguments.
     *
     * @param args command line args
     */
    private fun handleCommandLineArgs(args: Array<String>) {
        for (arg in args) {
            when (arg) {
                "--release" -> Logger.debugMode = false
            }
        }
    }

    /**
     * Initialize the app and start event/rendering loop.
     */
    fun init() {
        isRunning = true
        currentView.makeActive()

        renderDeferred = async (CommonPool) {
            render()
        }

        eventLoop()

        Logger.clearScreen()
        println("Thank you for using $APP_TITLE!")
    }

    /**
     * Rerender the view, when the last render completes.
     */
    fun rerender() {
        launch (CommonPool) {
            val activeRenderDeferred = renderDeferred
            activeRenderDeferred?.await()

            renderDeferred = async (CommonPool) {
                render()
            }
        }
    }

    /**
     * Render the current view.
     */
    suspend private fun render() {
        Logger.clearScreen()
        printHeader()
        currentView.render()

        // Clear the current render job
        renderDeferred = null
    }

    /**
     * Handle user input in a loop. If the current view does not handle the input, pass it to
     * generic input handling.
     */
    private fun eventLoop() {
        var input: String?
        var handled: Boolean

        while (isRunning) {
            input = readLine()
            handled = currentView.handleInput(input)
            if (!handled) {
                handleInput(input)
            }

            rerender()
        }
    }

    /**
     * Handle generic input sequences.
     *
     * @param input user input
     * @return `true` if the input was handled, `false` otherwise
     */
    private fun handleInput(input: String?): Boolean {
        when (input) {
            "q", "quit", "exit" -> isRunning = false
            else -> {
                Logger.debug("`$input` not handled in Main")
                return false
            }
        }

        return true
    }

    /**
     * Print a basic app header to the output.
     */
    private fun printHeader() {
        Logger.printInBox(APP_TITLE)
        Logger.printInBox(APP_DESCRIPTION, false)
    }

}
