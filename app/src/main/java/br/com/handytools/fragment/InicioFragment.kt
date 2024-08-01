package br.com.handytools.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.handytools.activity.R
import br.com.handytools.activity.databinding.FragmentInicioBinding

class InicioFragment : Fragment() {

    private val binding by lazy {
        FragmentInicioBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivArrowBlue.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                getString(R.string.texto_arrow),
                Toast.LENGTH_LONG
            ).show()
        }

        binding.lottieAnimation.setOnClickListener{
            binding.lottieAnimation.playAnimation()
        }
    }
}