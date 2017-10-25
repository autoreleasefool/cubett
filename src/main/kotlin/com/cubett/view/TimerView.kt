/**
 * Timer-based view for rendering the cube timer, recent solves, and basic statistics.
 *
 * Created by Joseph Roque on 2017-10-24.
 */
package com.cubett.view

import kotlinx.coroutines.experimental.*
import com.cubett.Cubett
import com.cubett.util.Logger

/**
 * Render a timer, some recent solves, and basic statistics. Handle input for the timer.
 */
class TimerView : View {

    /** Total amount of inspection time. */
    private var totalInspectionTime: Int = 15000

    /** Start time of the inspection. */
    private var inspectionStartTime: Long = -1

    /** Start time of the current solve. */
    private var solveStartTime: Long = -1

    /** True if the user is inspecting, false otherwise. */
    private var isInspecting: Boolean = false

    /** True if the solve has begun, false otherwise. */
    private var solveStarted: Boolean = false

    /** Time taken for the most recent solve. */
    private var lastSolveTime: Int = -1

    /** Render loop for timer. */
    private var renderLoop: Job? = null

    override fun makeActive() {

    }

    override fun makeInactive() {

    }

    override fun render() {
        renderTimer()
        if (lastSolveTime > 0) {
            println("Last solve: $lastSolveTime")
        }
    }

    private fun renderTimer() {
        var timer: Array<String> = arrayOf("","","","","","","","","")

        var displayTime: Int = 0
        if (solveStarted) {
            if (isInspecting) {
                displayTime = (System.currentTimeMillis() - inspectionStartTime).toInt()
            } else {
                displayTime = (System.currentTimeMillis() - solveStartTime).toInt()
            }
        }

        val initialTimeLength = java.lang.Math.log10(displayTime.toDouble()).toInt()
        var timeLength = initialTimeLength

        while (displayTime > 10) {
            val digit = (displayTime / java.lang.Math.pow(10.0, timeLength.toDouble()).toInt())
            appendDigitToTimer(timer, getTimerDigit(digit), timeLength == 2)

            displayTime = displayTime.rem(java.lang.Math.pow(10.0, timeLength.toDouble()).toInt())
            timeLength = java.lang.Math.log10(displayTime.toDouble()).toInt()
        }

        while (timer[0].length / 10 < initialTimeLength || timer[0].length / 10 < 3) {
            appendDigitToTimer(
                timer,
                getTimerDigit(0),
                timer[0].length / 10 == 1 || initialTimeLength - timer[0].length / 10 == 2)
        }

        for (line in timer) {
            println(line)
        }
    }

    override fun handleInput(input: String?): Boolean {
        when (input) {
            null, "" -> updateSolveState()
            else -> {
                Logger.debug("`$input` not handled in TimerView")
                return false
            }
        }

        return true
    }

    private fun updateSolveState() {
        val toggleTime = System.currentTimeMillis()

        if (solveStarted) {
            if (isInspecting) {
                solveStartTime = toggleTime
                isInspecting = false
            } else {
                lastSolveTime = (toggleTime - solveStartTime).toInt()
                stopRenderLoop()
            }
        } else {
            if (totalInspectionTime > 0) {
                inspectionStartTime = toggleTime
                isInspecting = true
            }

            solveStarted = true
            startRenderLoop()
        }
    }

    private fun startRenderLoop() {
        renderLoop = launch (CommonPool) {
            while (true) {
                Cubett.rerender()
                delay(2)
            }
        }
    }

    private fun stopRenderLoop() {
        val activeRenderLoop = renderLoop
        activeRenderLoop?.cancel()
    }

    private fun appendDigitToTimer(timer: Array<String>, digit: Array<String>, prependDecimal: Boolean) {
        if (prependDecimal) {
            for (i in 0..timer.size - 2) {
                timer[i] += "  "
            }
            timer[timer.size - 1] += "* "
        }

        for (i in digit.indices) {
            timer[i] += digit[i]
        }
    }

    private fun getTimerDigit(digit: Int): Array<String> {
        when (digit) {
            0 -> return arrayOf("********* ",
                                "**      * ",
                                "* *     * ",
                                "*  *    * ",
                                "*   *   * ",
                                "*    *  * ",
                                "*     * * ",
                                "*      ** ",
                                "********* ")
            1 -> return arrayOf("  ***     ",
                                "    *     ",
                                "    *     ",
                                "    *     ",
                                "    *     ",
                                "    *     ",
                                "    *     ",
                                "    *     ",
                                "********* ")
            2 -> return arrayOf("********* ",
                                "        * ",
                                "        * ",
                                "        * ",
                                "********* ",
                                "*         ",
                                "*         ",
                                "*         ",
                                "********* ")
            3 -> return arrayOf("********* ",
                                "        * ",
                                "        * ",
                                "        * ",
                                "********* ",
                                "        * ",
                                "        * ",
                                "        * ",
                                "********* ")
            4 -> return arrayOf("*       * ",
                                "*       * ",
                                "*       * ",
                                "*       * ",
                                "********* ",
                                "        * ",
                                "        * ",
                                "        * ",
                                "        * ")
            5 -> return arrayOf("********* ",
                                "*         ",
                                "*         ",
                                "*         ",
                                "********* ",
                                "        * ",
                                "        * ",
                                "        * ",
                                "********* ")
            6 -> return arrayOf("********* ",
                                "*         ",
                                "*         ",
                                "*         ",
                                "********* ",
                                "*       * ",
                                "*       * ",
                                "*       * ",
                                "********* ")
            7 -> return arrayOf("********* ",
                                "        * ",
                                "        * ",
                                "        * ",
                                "        * ",
                                "        * ",
                                "        * ",
                                "        * ",
                                "        * ")
            8 -> return arrayOf("********* ",
                                "*       * ",
                                "*       * ",
                                "*       * ",
                                "********* ",
                                "*       * ",
                                "*       * ",
                                "*       * ",
                                "********* ")
            9 -> return arrayOf("********* ",
                                "*       * ",
                                "*       * ",
                                "*       * ",
                                "********* ",
                                "        * ",
                                "        * ",
                                "        * ",
                                "********* ")
            else -> throw IllegalArgumentException("$digit must be single digit and >= 0")
        }
    }

}
