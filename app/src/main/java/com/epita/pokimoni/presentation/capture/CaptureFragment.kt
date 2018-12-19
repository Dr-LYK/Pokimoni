package com.epita.pokimoni.presentation.capture

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.epita.pokimoni.R
import com.google.ar.sceneform.ux.ArFragment

class CaptureFragment : ArFragment() {

    companion object {
        fun newInstance() = CaptureFragment()
    }

    private lateinit var viewModel: CaptureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_capture, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CaptureViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
