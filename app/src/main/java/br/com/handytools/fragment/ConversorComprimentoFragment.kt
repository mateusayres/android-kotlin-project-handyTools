package br.com.handytools.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.handytools.activity.R
import br.com.handytools.activity.databinding.FragmentConversorComprimentoBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ConversorComprimentoFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val binding by lazy {
        FragmentConversorComprimentoBinding.inflate(layoutInflater)
    }

    private lateinit var listaUnidades: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listaUnidades = arrayListOf(
            "Selecione",
            "Quilômetro",
            "Hectômetro",
            "Decâmetro",
            "Metro",
            "Decímetro",
            "Centímetro",
            "Milímetro"
        )
        val arrayAdapter = ArrayAdapter(
            binding.root.context,
            android.R.layout.simple_list_item_1,
            listaUnidades
        )

        binding.run {
            spUnidadeInicial.adapter = arrayAdapter
            spUnidadeFinal.adapter = arrayAdapter

            btnCalcular.setOnClickListener {
                try {
                    val unidadeInicial = spUnidadeInicial.selectedItemPosition
                    val unidadeFinal = spUnidadeFinal.selectedItemPosition
                    val textResultado = validaSpinnerText(unidadeFinal)

                    validarCampo(
                        tieNumero,
                        tilNumero,
                        getString(R.string.msg_campo_obrigatorio)
                    )
                    validaSpinner(
                        unidadeInicial,
                        getString(R.string.str_unidadesVazias),
                        tvAguardando
                    )

                    val valor = tieNumero.text.toString().toDouble()
                    val resultado = converterUnidades(valor, unidadeInicial, unidadeFinal)
                    tvAguardando.text = "$resultado $textResultado"
                } catch (_: Exception) {
                }
            }

            btnLimpar.setOnClickListener {
                tieNumero.text = null
                spUnidadeInicial.setSelection(0)
                spUnidadeFinal.setSelection(0)
                tvAguardando.text = "Resultado"
            }

            lottieAnimation.setOnClickListener {
                lottieAnimation.playAnimation()
            }
        }
    }

    private fun validaSpinner(
        spIndex: Int,
        msgErro: String,
        tvAguardando: TextView
    ) {
        if (spIndex == 0) {
            tvAguardando.text = msgErro
        }
    }

    private fun validaSpinnerText(spIndex: Int): String? {
        val textoResultado: String? =
            when (spIndex) {
                1 -> "km"
                2 -> "hm"
                3 -> "dam"
                4 -> "m"
                5 -> "dm"
                6 -> "cm"
                7 -> "mm"
                else -> {
                    null
                }
            }
        return textoResultado
    }

    private fun validarCampo(
        tie: TextInputEditText?,
        til: TextInputLayout,
        msgErro: String,
    ) {
        if (tie?.text?.toString()?.isNotEmpty() == true) {
            til.error = null
        } else {
            til.error = msgErro
        }
    }

    private fun converterUnidades(valor: Double, unidadeInicial: Int, unidadeFinal: Int): Double {
        val fatorConversaoParaMetros = arrayOf(1.0, 1000.0, 100.0, 10.0, 1.0, 0.1, 0.01, 0.001)
        val valorEmMetros = valor * fatorConversaoParaMetros[unidadeInicial] //Converte a unidade inicial para metros.
        val fatorConversaoDeMetros = arrayOf(1.0, 0.001, 0.01, 0.1, 1.0, 10.0, 100.0, 1000.0)
        return valorEmMetros * fatorConversaoDeMetros[unidadeFinal] //Converte de metros para a unidade final.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}
