/**
 * Timer-based view for rendering the cube timer, recent solves, and basic statistics.
 *
 * Created by Joseph Roque on 2017-10-24.
 */
package com.cubett.view

import com.cubett.util.Logger

/**
 * Render a timer, some recent solves, and basic statistics. Handle input for the timer.
 */
class TimerView : View {

    /** Total amount of inspection time. */
    private var startingInspectionTime: Int = 15000

    /** Time to display for inspection timer. */
    private var inspectionTime: Int = 15000

    /** Current time to display on timer */
    private var currentTime: Int = 0

    override fun makeActive() {

    }

    override fun makeInactive() {

    }

    override fun render() {
        renderTimer()
    }

    private fun renderTimer() {
        var timer: Array<String> = arrayOf("","","","","","","","","")
        var displayTime = if (inspectionTime > 0) inspectionTime else currentTime
        val initialTimeLength = java.lang.Math.log10(displayTime.toDouble()).toInt()
        var timeLength = initialTimeLength

        while (displayTime > 10) {
            val digit = (displayTime / java.lang.Math.pow(10.0, timeLength.toDouble()).toInt())
            appendDigitToTimer(timer, getTimerDigit(digit), timeLength == 2)

            displayTime = displayTime.rem(java.lang.Math.pow(10.0, timeLength.toDouble()).toInt())
            timeLength = java.lang.Math.log10(displayTime.toDouble()).toInt()
        }

        while (timer[0].length / 10 < initialTimeLength) {
            appendDigitToTimer(timer, getTimerDigit(0), initialTimeLength - timer[0].length / 10 == 2)
        }

        for (line in timer) {
            println(line)
        }
    }

    override fun handleInput(input: String?): Boolean {
        Logger.debug("`$input` not handled in TimerView")
        return false
    }

    private fun appendDigitToTimer(timer: Array<String>, digit: Array<String>, prependDecimal: Boolean) {
        if (prependDecimal) {
            for (i in 0..timer.size - 2) {
                timer[i] += "   "
            }
            timer[timer.size - 1] += " * "
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
