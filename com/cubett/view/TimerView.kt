/**
 * Timer-based view for rendering the cube timer, recent solves, and basic statistics.
 *
 * Created by Joseph Roque on 2017-10-24.
 */
package com.cubett.view

import com.cubett.util.Logger

class TimerView : View {

    override fun render() {
        println("Render: cool")
    }

    override fun handleInput(input: String?): Boolean {
        Logger.debug("`$input` not handled in TimerView")
        return false
    }

}
