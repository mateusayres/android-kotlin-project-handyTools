package br.com.handytools.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import br.com.handytools.activity.R
import br.com.handytools.activity.databinding.FragmentImcBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.DecimalFormat

class ImcFragment : Fragment() {

    private val binding by lazy {
        FragmentImcBinding.inflate(layoutInflater)
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
            btnCalc.setOnClickListener {
                try {
                    validarCampo(
                        tiePeso,
                        tilPeso,
                        getString(R.string.msg_campo_peso),
                        tvIMC,
                        tvResultado
                    )
                    validarCampo(
                        tieAltura,
                        tilAltura,
                        getString(R.string.msg_campo_altura),
                        tvIMC,
                        tvResultado
                    )

                    val imc = calcularIMC(
                        tiePeso.text.toString().toDouble(),
                        tieAltura.text.toString().toDouble()
                    )
                    val resultado = classificarIMC(imc)
                    val formato = DecimalFormat("#.##")
                    tvResultado.text = resultado.toString()
                    tvIMC.text = "IMC: " + formato.format(imc).toString()
                } catch (_: Exception) {

                }

                btnLimpar.setOnClickListener {
                    tiePeso.text = null
                    tieAltura.text = null
                    tvIMC.setText(R.string.str_imcResult)
                    tvResultado.setText(R.string.str_imcResultText)
                }
            }
        }
    }

    private fun validarCampo(
        tie: TextInputEditText?,
        til: TextInputLayout,
        msgErro: String?,
        tvResultado: TextView,
        tvIMC: TextView
    ) {
        if (tie?.text?.toString()?.isNotEmpty() == true) {
            til.error = null
        } else {
            til.error = msgErro
            binding.tvResultado.setText(R.string.str_imcResultText)
            binding.tvIMC.setText(R.string.str_imcResult)
        }
    }

    private fun calcularIMC(peso: Double, altura: Double): Double {
        return peso / (altura * altura)
    }

    private fun classificarIMC(imc: Double): String {
        return when {
            imc < 16 -> "Magreza Grave"
            imc < 16.9 -> "Magreza Moderada"
            imc < 18.5 -> "Magreza Leve"
            imc < 24.9 -> "Peso Ideal"
            imc < 29.9 -> "Sobrepeso"
            imc < 34.9 -> "Sobrepeso Grau I"
            imc < 39.9 -> "Sobrepeso Grau II ou Severa"
            else -> "Obesidade Grau III ou Mórbida"
        }
    }
}
