package com.orteney.playground.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.orteney.playground.R
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        addNavigation("List with touch", R.id.action_menuFragment_to_simpleListFragment)
        addNavigation("List with flexbox", R.id.action_menuFragment_to_flexBoxListFragment)
        addNavigation("List with expandable", R.id.action_menuFragment_to_expandableListFragment)
        addNavigation("Bird", R.id.action_menuFragment_to_birdFragment)
        addNavigation("Expandable text", R.id.action_menuFragment_to_expandableTextFragment)
        addNavigation("Staggered Animations", R.id.action_menuFragment_to_staggeredAnimationFragment)
        addNavigation("Easy Image", R.id.action_menuFragment_to_easyImageFragment)
        addNavigation("Text Views Fonts", R.id.action_menuFragment_to_textViewsFragment)
        addNavigation("Keyframe Animation", R.id.action_menuFragment_to_keyframeAnimationFragment)
        addNavigation("Chips", R.id.action_menuFragment_to_chipsFragment)

        createButton("SnackBar").setOnClickListener {
            view?.let { Snackbar.make(it, "I'm snackbar :3", Snackbar.LENGTH_SHORT).show() }
        }
    }

    private fun addNavigation(text: String, id: Int) {
        createButton(text).setOnClickListener(Navigation.createNavigateOnClickListener(id))
    }

    private fun createButton(text: String): MaterialButton {
        val button = MaterialButton(requireContext(), null, R.attr.mainMenuButtonStyle)
        button.text = text

        containerLayout.addView(button)

        return button
    }
}