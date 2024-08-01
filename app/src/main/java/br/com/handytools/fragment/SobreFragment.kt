package br.com.handytools.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.handytools.activity.databinding.FragmentSobreBinding

class SobreFragment : Fragment() {
    private val binding by lazy {
        FragmentSobreBinding.inflate(layoutInflater)
    }

    private var controlAnimationState = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            lottieAnimation.setOnClickListener{
                if (controlAnimationState){
                    lottieAnimation.pauseAnimation()
                    controlAnimationState = false
                } else {
                    lottieAnimation.resumeAnimation()
                    controlAnimationState = true
                }
            }
        }
    }
}