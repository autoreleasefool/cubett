/**
 * Main Cube Terminal Timer application.
 *
 * Created by Joseph Roque on 2017-10-24.
 */
package com.cubett

import kotlin.system.exitProcess
import com.cubett.util.Logger
import com.cubett.view.TimerView
import com.cubett.view.View

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

/**
 * Main function.
 *
 * @param args command line args
 */
fun main(args: Array<String>) {
    handleCommandLineArgs(args)

    var currentView: View = TimerView()
    var input: String?
    var handled: Boolean

    while (true) {
        Logger.clearScreen()
        printHeader()
        currentView.render()

        input = readLine()
        handled = handleInput(input)
        if (!handled) {
            currentView.handleInput(input)
        }
    }
}

/**
 * Handle generic input sequences.
 *
 * @param input user input
 * @return `true` if the input was handled, `false` otherwise
 */
fun handleInput(input: String?): Boolean {
    when (input) {
        "q", "quit", "exit" -> exitProcess(0)
        else -> {
            Logger.debug("`$input` not handled in Main")
            return false
        }
    }
}

/**
 * Print a basic app header to the output.
 */
fun printHeader() {
    Logger.printInBox(APP_TITLE)
    Logger.printInBox(APP_DESCRIPTION, false)
}

/**
 * Process command line arguments.
 *
 * @param args command line args
 */
fun handleCommandLineArgs(args: Array<String>) {
    for (arg in args) {
        when (arg) {
            "--release" -> Logger.verbose = false
        }
    }
}
