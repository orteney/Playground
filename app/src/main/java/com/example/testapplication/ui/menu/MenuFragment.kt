package com.example.testapplication.ui.menu

import android.os.Bundle
import android.support.design.button.MaterialButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.testapplication.R
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
    }

    private fun addNavigation(text: String, id: Int) {
        val view = (layoutInflater.inflate(R.layout.view_menu_botton, null) as MaterialButton)
            .apply {
                this.text = text
                setOnClickListener(Navigation.createNavigateOnClickListener(id))
            }

        containerLayout.addView(view)
    }
}