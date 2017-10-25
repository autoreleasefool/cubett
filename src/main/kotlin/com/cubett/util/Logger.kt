/**
 * Handles formatting output for stdout.
 *
 * Created by Joseph Roque on 2017-10-24.
 */
package com.cubett.util

/** Ansi escape character code. */
const val ANSI_ESCAPE = '\u001B'
/** Ansi clear screen character sequence. */
const val CLS = "$ANSI_ESCAPE[2J"
/** Ansi home character sequence. */
const val HOME = "$ANSI_ESCAPE[H"

/**
 * Singleton Logger.
 */
object Logger {

    /** Max line length for output. */
    var lineLength = 80

    /** Character to construct boxes from. */
    var boxCharacter = '%'

    /** True to enable debug mode logging. */
    var debugMode: Boolean = true

    /**
     * Clear all output.
     */
    fun clearScreen() {
        // FIXME: is there a better way to clear the console without just pushing it up?
        if (!debugMode) {
            print("$CLS$HOME")
        }
    }

    /**
     * Prints a line of *boxCharacter* characters.
     */
    fun printSeparator() {
        println("".padStart(lineLength, boxCharacter))
    }

    /**
     * Prints a message, in a nicely formatted box of '%' characters. Can exclude top or bottom separator.
     *
     * @param text          the text to print nicely in a box
     * @param includeTop    `true` to include the top separator, `false` to exclude. Defaults to `true`
     * @param includeBottom `true` to include the bottom separator, `false` to exclude. Defaults to `true`
     */
    fun printInBox(text: String, includeTop: Boolean = true, includeBottom: Boolean = true) {
        if (includeTop) {
            printSeparator()
        }

        val splitText = text.split("\n")
        if (splitText.size == 1) {
            var formattedText = text.padStart(((lineLength - 2) + text.length) / 2).padEnd(lineLength - 2)
            println("%c%s%c".format(boxCharacter, formattedText, boxCharacter))
        } else {
            for (subtext in splitText) {
                printInBox(subtext, false, false)
            }
        }

        if (includeBottom) {
            printSeparator()
        }
    }

    /**
     * Print a message if Logger is in debug mode.
     *
     * @param msg the message to print
     */
    fun debug(msg: String?) {
        if (debugMode && msg != null) println(msg)
    }

}