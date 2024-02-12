package com.multimedia.aes.gestnet_ssl.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.multimedia.aes.gestnet_ssl.R
import com.multimedia.aes.gestnet_ssl.SharedPreferences.GestorSharedPreferences
import com.multimedia.aes.gestnet_ssl.dao.ParteDAO
import com.multimedia.aes.gestnet_ssl.dao.UsuarioDAO
import com.multimedia.aes.gestnet_ssl.entidades.Parte
import com.multimedia.aes.gestnet_ssl.entidades.Usuario
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TabFragment2_datosSiniestro.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabFragment2_datosSiniestro : Fragment() {

    private val REQUEST_CODE_SPEECH_INPUT = 1

    lateinit var vista: View
    lateinit var txtTipoIntervencionTab2: TextView
    lateinit var txtProfesional: TextView
    lateinit var txtTecnicoAsig: TextView
    lateinit var txtFechaVisitatab2: TextView
    lateinit var txtHorariotab2: TextView
    lateinit var txtTipoHorario: TextView
    lateinit var txtDescripcionServicio: EditText
    lateinit var btnSpeechTab2: Button
    lateinit var btnNoPuede: Button
    lateinit var btnAnularServicio: Button
    lateinit var parte: Parte
    lateinit var usuario: Usuario
    lateinit var spinnerTipoVitro: Spinner
    lateinit var spinnerEstados: Spinner
    lateinit var aseguradoCocinar: RadioGroup
    lateinit var funcionaFuego: RadioGroup
    lateinit var otrosDanos: RadioGroup
    lateinit var noCubre: RadioGroup

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var jsonObject = GestorSharedPreferences.getJsonParte(
            GestorSharedPreferences.getSharedPreferencesParte(
                context
            )
        )
        vista = inflater.inflate(R.layout.fragment_tab_fragment2_datos_siniestro, container, false)
        txtTipoIntervencionTab2 = vista.findViewById(R.id.txtTipoIntervencionTab2)
        txtTecnicoAsig = vista.findViewById(R.id.txtTecnicoAsig)
        txtProfesional = vista.findViewById(R.id.txtProfesional)
        spinnerTipoVitro = vista.findViewById(R.id.spinnerTipoVitro)
        spinnerEstados = vista.findViewById(R.id.spinnerEstados)
        txtFechaVisitatab2 = vista.findViewById(R.id.txtFechaVisitatab2)
        txtHorariotab2 = vista.findViewById(R.id.txtHorariotab2)
        txtTipoHorario = vista.findViewById(R.id.txtTipoHorario)
        btnSpeechTab2 = vista.findViewById(R.id.btnSpeechTab2)
        btnNoPuede = vista.findViewById(R.id.btnNoPuede)
        btnAnularServicio = vista.findViewById(R.id.btnAnularServicio)
        txtDescripcionServicio = vista.findViewById(R.id.txtDescripcionServicio)
        aseguradoCocinar = vista.findViewById(R.id.aseguradoCocinar)
        funcionaFuego = vista.findViewById(R.id.funcionaFuego)
        otrosDanos = vista.findViewById(R.id.otrosDanos)
        noCubre = vista.findViewById(R.id.noCubre)


        parte = ParteDAO.buscarPartePorId(context, jsonObject.getInt("id"))
        usuario = UsuarioDAO.buscarUsuario(context)
        txtTipoIntervencionTab2.text = "Tipo de intervención: " + parte.tipo
        txtTecnicoAsig.text = "Técnico asignado: " + usuario.nombreUsuario
        txtProfesional.text = "Profesional: " + parte.externo
        txtFechaVisitatab2.text = "Cita Concertada: " + parte.fecha_visita
        var franja = ""
        if (parte.franja_horaria.equals("0"))
            franja = "Todo el día"
        else if (parte.horario.equals("1"))
            franja = "Mañana"
        else if (parte.horario.equals("2"))
            franja = "Tarde"
        txtHorariotab2.text = "Tipo Franja: $franja"
        txtTipoHorario.text = "Franja Horaria: " + parte.horario

        var tipoVitro = arrayOf("Radiante", "Inducción", "Mixta")
        spinnerTipoVitro.adapter = context?.let { ArrayAdapter<String>(it, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,tipoVitro.asList()) }
        spinnerTipoVitro.setSelection(parte.vitro_caracteristicas)
        spinnerTipoVitro.isEnabled = false
        // TODO: Obtener los estados mediante json

        if(parte.contrato_endesa.equals("false"))
            parte.contrato_endesa = "Sin estado"
        var estados = arrayOf(parte.contrato_endesa)
        spinnerEstados.adapter = context?.let { ArrayAdapter<String>(it, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, estados.asList()) }
        spinnerEstados.isEnabled = false

        btnSpeechTab2.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault()
            )
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "¡Habla!")

            try {
                startActivityForResult(
                    intent,
                    REQUEST_CODE_SPEECH_INPUT
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        btnNoPuede.setOnClickListener {
            val builder1 = AlertDialog.Builder(context)
            builder1.setMessage(Html.fromHtml("¿Estas seguro de querer comunicar a Jelpin de que" +
                    " no se puede efectuar la visita esta semana?", Html.FROM_HTML_MODE_LEGACY))
            builder1.setCancelable(true)
            builder1.setPositiveButton(
                "Aceptar"
            ) { dialog, id ->
                dialog.cancel()
            }
            builder1.setNegativeButton("Cancelar") { dialog, id -> dialog.cancel() }

            val alert11 = builder1.create()
            alert11.setCanceledOnTouchOutside(false)
            alert11.show()
        }

        btnAnularServicio.setOnClickListener {
            val builder1 = AlertDialog.Builder(context)
            builder1.setMessage(Html.fromHtml("¿Estas seguro de querer anular el servicio " +
                    "porque el cliente se ha comprado una vitro nueva?", Html.FROM_HTML_MODE_LEGACY))
            builder1.setCancelable(true)
            builder1.setPositiveButton(
                "Aceptar"
            ) { dialog, id ->
                dialog.cancel()
            }
            builder1.setNegativeButton("Cancelar") { dialog, id -> dialog.cancel() }

            val alert11 = builder1.create()
            alert11.setCanceledOnTouchOutside(false)
            alert11.show()
        }

        if(parte.isAsegurado_puede_cocina)
            aseguradoCocinar.check(R.id.radioButton)
        else
            aseguradoCocinar.check(R.id.radioButton2)

        if (parte.isFunciona_fuego)
            funcionaFuego.check(R.id.radioButton3)
        else
            funcionaFuego.check(R.id.radioButton4)

        if (parte.isOtros_danos)
            otrosDanos.check(R.id.radioButton5)
        else
            otrosDanos.check(R.id.radioButton6)

        if (parte.isAsegurado_informado_no_cobertura)
            noCubre.check(R.id.radioButton7)
        else
            noCubre.check(R.id.radioButton8)

        return vista
    }

    /*@SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == Activity.RESULT_OK){
            val result = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

            var msg: String = ""
            if (!txtDescripcionServicio.text.equals(""))
                msg += txtDescripcionServicio.text.toString() + "\n"
            txtDescripcionServicio.setText(msg + (result?.get(0) ?: ""))
        }
    }*/
}