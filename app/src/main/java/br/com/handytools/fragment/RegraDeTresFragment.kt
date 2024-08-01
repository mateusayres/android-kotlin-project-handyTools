package br.com.handytools.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.handytools.activity.R
import br.com.handytools.activity.databinding.FragmentRegraDeTresBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.DecimalFormat

class RegraDeTresFragment : Fragment() {

    private val binding by lazy {
        FragmentRegraDeTresBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            btnCalcR3.setOnClickListener {

                try {
                    validarCampo(
                        tieValorA,
                        tilValorA,
                        getString(R.string.str_vazio)
                    )
                    validarCampo(
                        tieValorB,
                        tilValorB,
                        getString(R.string.str_vazio)
                    )
                    validarCampo(
                        tieValorC,
                        tilValorC,
                        getString(R.string.str_vazio)
                    )

                    val tieValorA = tieValorA.text.toString().toDouble()
                    val tieValorB = tieValorB.text.toString().toDouble()
                    val tieValorC = tieValorC.text.toString().toDouble()

                    val ResultadoR3 = calculoR3(tieValorA, tieValorB, tieValorC).toDouble()
                    val formato = DecimalFormat("#.##")
                    tieValorX.setText(formato.format(ResultadoR3).toString())
                } catch (_: Exception) {

                }
            }

            btnLimparR3.setOnClickListener {
                tieValorA.text = null
                tieValorB.text = null
                tieValorC.text = null
                tieValorX.text = null
            }
        }
    }

    private fun calculoR3(valorA: Double, valorB: Double, valorC: Double): Double {
        return (valorB * valorC) / valorA
    }

    private fun validarCampo(
        tie: TextInputEditText?,
        til: TextInputLayout,
        msgErro: String?,
    ) {
        if (tie?.text?.toString()?.isNotEmpty() == true) {
            til.error = null
        } else {
            til.error = msgErro
        }
    }
}