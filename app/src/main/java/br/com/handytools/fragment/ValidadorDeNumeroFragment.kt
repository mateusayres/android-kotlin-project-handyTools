package br.com.handytools.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import br.com.handytools.activity.R
import br.com.handytools.activity.databinding.FragmentValidadorDeNumeroBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ValidadorDeNumeroFragment : Fragment() {

    private val binding by lazy {
        FragmentValidadorDeNumeroBinding.inflate(layoutInflater)
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
            btnResposta.setOnClickListener {

                try {
                    validarCampo(
                        tieNumero,
                        tilNumero,
                        getString(R.string.msg_campo_obrigatorio),
                        tvAguardando
                    )
                    val resultado = calcularImparPar(tieNumero.text?.toString()?.toInt())
                    tvAguardando.text = resultado.toString()

                } catch (_: Exception) {

                }
            }

            //Botão Limpar
            btnLimparVN.setOnClickListener {
                tieNumero.text = null
                tvAguardando.text = "Aguardando"
            }

            lottieAnimation.setOnClickListener{
                lottieAnimation.playAnimation()
            }
        }
    }

    private fun calcularImparPar(numero: Int?): String {
        return if ((numero?.rem(2)) == 0) "Seu número é PAR" else "Seu número é ÍMPAR"
    }

    private fun validarCampo(
        tie: TextInputEditText?,
        til: TextInputLayout,
        msgErro: String,
        tvAguardando: TextView,
    ) {
        if (tie?.text?.toString()?.isNotEmpty() == true) {
            til.error = null
        } else {
            til.error = msgErro
            binding.tvAguardando.setText("Aguardando")
        }
    }
}