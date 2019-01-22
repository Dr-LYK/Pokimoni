package com.epita.pokimoni.ui.capture

import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast
import com.epita.pokimoni.MainActivity
import com.epita.pokimoni.R
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

private val TAG = MainActivity::class.java.simpleName
private const val MIN_OPENGL_VERSION = 3.0

class CaptureFragment : Fragment() {

    companion object {
        fun newInstance() = CaptureFragment()
    }

    private lateinit var arFragment: ArFragment
    private lateinit var pokemonRenderable: ModelRenderable
    private lateinit var viewModel: CaptureViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.epita.pokimoni.R.layout.fragment_capture, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CaptureViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arFragment = childFragmentManager.findFragmentById(R.id.fragment_capture_ar) as ArFragment

        ModelRenderable.builder()
            .setSource(activity, Uri.parse("001.sfb"))
            .build()
            .thenAccept { renderable -> pokemonRenderable = renderable }
            .exceptionally {
                val toast = Toast.makeText(activity, "Unable to load Pokémon renderable", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                null
            }

        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
            if (::pokemonRenderable.isInitialized) {
                val anchor: Anchor = hitResult.createAnchor()
                val anchorNode = AnchorNode(anchor)
                anchorNode.setParent(arFragment.arSceneView.scene)

                val pokemon = TransformableNode(arFragment.transformationSystem)
                pokemon.setParent(anchorNode)
                pokemon.renderable = pokemonRenderable
                pokemon.select()
            }
        }
    }
}