package com.example.proyectofinalpm1.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalpm1.Empleado
import com.example.proyectofinalpm1.EncargoActivity
import com.example.proyectofinalpm1.EncargoAdapter
import com.example.proyectofinalpm1.MainActivity
import com.example.proyectofinalpm1.MenuActivity
import com.example.proyectofinalpm1.Nota
import com.example.proyectofinalpm1.OnItemClickListener
import com.example.proyectofinalpm1.R
import com.example.proyectofinalpm1.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(), OnItemClickListener{

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var btn_encargo: Button
    private lateinit var tv_wash1: TextView
    private lateinit var tv_wash2: TextView
    private lateinit var tv_wash3: TextView
    private lateinit var tv_heat1: TextView
    private lateinit var tv_heat2: TextView
    private lateinit var tv_heat3: TextView
    private lateinit var rv_encargo: RecyclerView
    private lateinit var encargoAdapter: EncargoAdapter


    private lateinit var et_search: EditText
    private lateinit var ib_search: ImageButton
    private lateinit var ib_reset: ImageButton

    private var washCount1: CountDownTimer? = null
    private var washCount2: CountDownTimer? = null
    private var washCount3: CountDownTimer? = null

    private var heatCount1: CountDownTimer? = null
    private var heatCount2: CountDownTimer? = null
    private var heatCount3: CountDownTimer? = null

    private var isTimerRunning = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        btn_encargo = root.findViewById(R.id.btn_encargo)
        tv_wash1 = root.findViewById(R.id.tv_wash1)
        tv_wash2 = root.findViewById(R.id.tv_wash2)
        tv_wash3 = root.findViewById(R.id.tv_wash3)
        tv_heat1 = root.findViewById(R.id.tv_heat1)
        tv_heat2 = root.findViewById(R.id.tv_heat2)
        tv_heat3 = root.findViewById(R.id.tv_heat3)
        rv_encargo = root.findViewById(R.id.rv_encargo)
        rv_encargo.layoutManager = LinearLayoutManager(requireContext())

        et_search = root.findViewById(R.id.et_buscar)
        ib_search = root.findViewById(R.id.ib_search)
        ib_reset = root.findViewById(R.id.ib_restart)

        btn_encargo.setOnClickListener{
            nuevoEncargo()
        }

        ib_search.setOnClickListener{
            buscar()
        }

        ib_reset.setOnClickListener{
            reset()
        }

        encargoAdapter = EncargoAdapter(MainActivity.notasEncargo, this)
        rv_encargo.adapter = encargoAdapter

        return root
    }

    private fun reset() {
        encargoAdapter = EncargoAdapter(MainActivity.notasEncargo, this)
        rv_encargo.adapter = encargoAdapter
    }

    private fun buscar() {
        val encargosFiltrados = MainActivity.notasEncargo.filter { it.nombreUsuaario.equals(et_search.text.toString()) }
        encargoAdapter = EncargoAdapter(encargosFiltrados.toMutableList(), this)
        rv_encargo.adapter = encargoAdapter
    }

    private fun nuevoEncargo() {
        val intent = Intent(requireContext(), EncargoActivity::class.java)
        //startActivity(intent)
        startActivityForResult(intent, 1)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        encargoAdapter = EncargoAdapter(MainActivity.notasEncargo, this)
        rv_encargo.adapter = encargoAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onButtonClick(nota: Nota, buttonId: Int) {
        when (buttonId) {
            1 -> showPopupForWash(nota)
            2 -> showPopupForHeat(nota)
            3 -> deleteNota(nota)
        }
    }

    override fun onButtonClick(nota: Empleado, buttonId: Int) {
        TODO("Not yet implemented")
    }

    private fun deleteNota(nota: Nota) {
        MainActivity.notasEncargo.remove(nota)
        encargoAdapter = EncargoAdapter(MainActivity.notasEncargo, this)
        rv_encargo.adapter = encargoAdapter
    }

    private fun showPopupForHeat(nota: Nota) {
        val popupView = layoutInflater.inflate(R.layout.heat_popup, null)

        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        popupWindow.isFocusable = true

        val btnCancelar = popupView.findViewById<Button>(R.id.btn_cancelar_heat_popup)
        btnCancelar.setOnClickListener {
            popupWindow.dismiss()
        }

        val ibHeat1 = popupView.findViewById<ImageButton>(R.id.ib_heat1)
        val ibHeat2 = popupView.findViewById<ImageButton>(R.id.ib_heat2)
        val ibHeat3 = popupView.findViewById<ImageButton>(R.id.ib_heat3)

        val tvHeat1 = binding.tvHeat1
        val tvHeat2 = binding.tvHeat2
        val tvHeat3 = binding.tvHeat3

        ibHeat1.setOnClickListener {
            startTimer(tvHeat1, 4)
            popupWindow.dismiss()
        }

        ibHeat2.setOnClickListener {
            startTimer(tvHeat2, 5)
            popupWindow.dismiss()
        }

        ibHeat3.setOnClickListener {
            startTimer(tvHeat3, 6)
            popupWindow.dismiss()
        }

        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)
    }

    private fun showPopupForWash(nota: Nota) {
        val popupView = layoutInflater.inflate(R.layout.wash_popup, null)

        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        popupWindow.isFocusable = true

        val btnCancelar = popupView.findViewById<Button>(R.id.btn_cancelar_wash_popup)
        btnCancelar.setOnClickListener {
            popupWindow.dismiss()
        }

        val ibWash1 = popupView.findViewById<ImageButton>(R.id.ib_wash1)
        val ibWash2 = popupView.findViewById<ImageButton>(R.id.ib_wash2)
        val ibWash3 = popupView.findViewById<ImageButton>(R.id.ib_wash3)

        val tvWash1 = binding.tvWash1
        val tvWash2 = binding.tvWash2
        val tvWash3 = binding.tvWash3

        ibWash1.setOnClickListener {
            startTimer(tvWash1, 1)
            popupWindow.dismiss()
        }

        ibWash2.setOnClickListener {
            startTimer(tvWash2, 2)
            popupWindow.dismiss()
        }

        ibWash3.setOnClickListener {
            startTimer(tvWash3, 3)
            popupWindow.dismiss()
        }

        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)
    }

    private fun startTimer(tvWash: TextView, id: Int) {
        val totalTime = 10000L

        if(id == 1)
        {
            washCount1 = object : CountDownTimer(totalTime, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val secondsRemaining = millisUntilFinished / 1000
                    tvWash.text = secondsRemaining.toString()
                }

                override fun onFinish() {
                    tvWash.text = "00:00"
                    isTimerRunning = false
                }
            }.start()
        }
        if(id == 2)
        {
            washCount2 = object : CountDownTimer(totalTime, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val secondsRemaining = millisUntilFinished / 1000
                    tvWash.text = secondsRemaining.toString()
                }

                override fun onFinish() {
                    tvWash.text = "00:00"
                    isTimerRunning = false
                }
            }.start()
        }
        if(id == 3)
        {
            washCount3 = object : CountDownTimer(totalTime, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val secondsRemaining = millisUntilFinished / 1000
                    tvWash.text = secondsRemaining.toString()
                }

                override fun onFinish() {
                    tvWash.text = "00:00"
                    isTimerRunning = false
                }
            }.start()
        }
        if(id == 4)
        {
            heatCount1 = object : CountDownTimer(totalTime, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val secondsRemaining = millisUntilFinished / 1000
                    tvWash.text = secondsRemaining.toString()
                }

                override fun onFinish() {
                    tvWash.text = "00:00"
                    isTimerRunning = false
                }
            }.start()
        }
        if(id == 5)
        {
            heatCount2 = object : CountDownTimer(totalTime, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val secondsRemaining = millisUntilFinished / 1000
                    tvWash.text = secondsRemaining.toString()
                }

                override fun onFinish() {
                    tvWash.text = "00:00"
                    isTimerRunning = false
                }
            }.start()
        }
        if(id == 6)
        {
            heatCount3 = object : CountDownTimer(totalTime, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val secondsRemaining = millisUntilFinished / 1000
                    tvWash.text = secondsRemaining.toString()
                }

                override fun onFinish() {
                    tvWash.text = "00:00"
                    isTimerRunning = false
                }
            }.start()
        }

    }

}