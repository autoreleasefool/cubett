/**
 * Main Cube Terminal Timer application.
 *
 * Created by Joseph Roque on 2017-10-24.
 */
package com.cubett

import com.cubett.util.Logger

var APP_TITLE = "Cubett"
var APP_DESCRIPTION = """
Welcome to the Cube Terminal Timer

Spacebar - Start/stop the timer
S - Open the app settings
V - View your solve times
T - Change the active tags
""".trimIndent()

fun main(args: Array<String>) {
    printHeader()
}

fun printHeader() {
    Logger.clearScreen()
    Logger.printInBox(APP_TITLE)
    Logger.printInBox(APP_DESCRIPTION, false)
}
