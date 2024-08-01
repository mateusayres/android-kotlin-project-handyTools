package br.com.handytools.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.handytools.activity.databinding.FragmentConversorComprimentoBinding

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

        listaUnidades = arrayListOf("Selecione", "Quilômetro", "Hectômetro", "Decâmetro", "Metro", "Decímetro", "Centímetro", "Milímetro")
        val arrayAdapter = ArrayAdapter(
            binding.root.context,
            android.R.layout.simple_list_item_1,
            listaUnidades
        )

        binding.run {
            spUnidadeInicial.adapter = arrayAdapter
            spUnidadeFinal.adapter = arrayAdapter

            btnCalcular.setOnClickListener {
                val inicio = spUnidadeInicial.selectedItemPosition
                val final = spUnidadeFinal.selectedItemPosition

                if (inicio == 0 || final == 0) {
                    Toast.makeText(binding.root.context, "Por favor, selecione as unidades.", Toast.LENGTH_SHORT).show()
                } else {
                    val valor = tieNumero.text.toString().toDoubleOrNull()
                    if (valor == null) {
                        Toast.makeText(binding.root.context, "Por favor, insira um valor válido.", Toast.LENGTH_SHORT).show()
                    } else {
                        val resultado = converterUnidades(valor, inicio, final)
                        tvAguardando.text = "Resultado: $resultado"
                    }
                }
            }

            btnLimpar.setOnClickListener {
                tieNumero.text = null
                spUnidadeInicial.setSelection(0)
                spUnidadeFinal.setSelection(0)
                tvAguardando.text = "Resultado"
            }
        }
    }

    private fun converterUnidades(valor: Double, unidadeInicial: Int, unidadeFinal: Int): Double {
        val fatorConversaoParaMetros = arrayOf(
            1.0, // Selecionado, fator é 1 para não alterar valor (não usado)
            1000.0, // Quilômetro para metro
            100.0, // Hectômetro para metro
            10.0, // Decâmetro para metro
            1.0, // Metro para metro
            0.1, // Decímetro para metro
            0.01, // Centímetro para metro
            0.001 // Milímetro para metro
        )

        // Convertendo a unidade inicial para metros
        val valorEmMetros = valor * fatorConversaoParaMetros[unidadeInicial]

        // Convertendo de metros para a unidade final
        val fatorConversaoDeMetros = arrayOf(
            1.0, // Selecionado, fator é 1 para não alterar valor (não usado)
            0.001, // Metro para quilômetro
            0.01, // Metro para hectômetro
            0.1, // Metro para decâmetro
            1.0, // Metro para metro
            10.0, // Metro para decímetro
            100.0, // Metro para centímetro
            1000.0 // Metro para milímetro
        )

        return valorEmMetros * fatorConversaoDeMetros[unidadeFinal]
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // Implementação do que fazer ao selecionar um item, se necessário
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // Implementação do que fazer ao não selecionar nada, se necessário
    }
}
