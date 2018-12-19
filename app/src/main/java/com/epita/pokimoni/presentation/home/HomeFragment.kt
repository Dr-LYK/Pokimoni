package com.epita.pokimoni.presentation.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.epita.pokimoni.R

class HomeFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var captureButton: Button
    private lateinit var pokedexButton: Button

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        captureButton = view.findViewById(R.id.fragment_home_button_capture)
        pokedexButton = view.findViewById(R.id.fragment_home_button_pokedex)

        captureButton.setOnClickListener(this@HomeFragment)
        pokedexButton.setOnClickListener(this@HomeFragment)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.fragment_home_button_capture -> {
                    view?.findNavController()?.navigate(R.id.captureFragment)
                }

                R.id.fragment_home_button_pokedex -> {
                    view?.findNavController()?.navigate(R.id.pokedexFragment)
                }
            }
        }
    }
}
