package com.epita.pokimoni.ui.capture

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.TextView
import android.widget.Toast
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.findNavController
import com.epita.pokimoni.R
import com.epita.pokimoni.model.PokemonItem

class CaptureFragment : Fragment(), View.OnClickListener {

    private lateinit var informationTextView: TextView
    private lateinit var buttonCancel: ImageView
    private lateinit var buttonCapture: Button
    //private lateinit var arFragment: ArFragment
    //private lateinit var pokemonRenderable: ModelRenderable
    private lateinit var viewModel: CaptureViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.epita.pokimoni.R.layout.fragment_capture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CaptureViewModel::class.java)

        informationTextView = view.findViewById(R.id.fragment_capture_information_step)
        buttonCancel = view.findViewById(R.id.fragment_capture_button_cancel)
        buttonCapture = view.findViewById(R.id.fragment_capture_button_capture)
        //arFragment = childFragmentManager.findFragmentById(R.id.fragment_capture_ar) as ArFragment

        /*viewModel.getFile().observe(this, Observer<String> { file ->
            ModelRenderable.builder()
                .setSource(activity, Uri.parse(file))
                .build()
                .thenAccept { renderable -> pokemonRenderable = renderable }
                .exceptionally {
                    val toast = Toast.makeText(activity, "Unable to load Pokémon renderable", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    null
                }

            if (arFragment.arSceneView.planeRenderer.isEnabled) {
                viewModel.setInformation(resources.getString(R.string.fragment_capture_information_step2))
            }

            arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
                if (::pokemonRenderable.isInitialized) {
                    viewModel.setInformation(resources.getString(R.string.fragment_capture_information_step3))
                    val anchor: Anchor = hitResult.createAnchor()
                    val anchorNode = AnchorNode(anchor)
                    anchorNode.setParent(arFragment.arSceneView.scene)

                    val pokemon = TransformableNode(arFragment.transformationSystem)
                    pokemon.setParent(anchorNode)
                    pokemon.renderable = pokemonRenderable
                    pokemon.select()

                    val handler = Handler()
                    val r = Runnable {
                        viewModel.setInformation(resources.getString(R.string.fragment_capture_information_step4))
                        val r = Runnable {
                            view.findNavController().navigate(com.epita.pokimoni.R.id.homeFragment)
                        }
                        handler.postDelayed(r, 2000)
                    }
                    handler.postDelayed(r, 5000)
                }
            }
        })*/

        viewModel.getInformation().observe(this, Observer<String> { information ->
            informationTextView.text = information
        })

        viewModel.getFile().observe(this, Observer<String>{ file ->
            if (file != null) {
                viewModel.getPokemon()
            }
        })

        viewModel.getPokemon().observe(this, Observer<PokemonItem> { pokemon ->
            if (pokemon != null) {
                buttonCapture.isClickable = true
            }
        })

        buttonCapture.setOnClickListener(this@CaptureFragment)
        buttonCancel.setOnClickListener(this@CaptureFragment)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.fragment_capture_button_capture -> {
                        viewModel.savePokemon()
                }

                R.id.fragment_capture_button_cancel -> {
                    view?.findNavController()?.navigate(com.epita.pokimoni.R.id.homeFragment)
                }
            }
        }
    }
}