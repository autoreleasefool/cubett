/**
 * Views are used to render application state and handle user input.
 *
 * Created by Joseph Roque on 2017-10-24.
 */
package com.cubett.view

/**
 * Interface for declaring methods to display views in the application.
 */
interface View {

    /**
     * Renders the view based on its current state.
     */
    abstract fun render()

    /**
     * Handles input to the view.
     *
     * @param input user input
     * @return `true` if the input was handled, `false` otherwise
     */
    abstract fun handleInput(input: String?): Boolean

}