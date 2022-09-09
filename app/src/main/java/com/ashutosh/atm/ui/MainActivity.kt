package com.ashutosh.atm.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashutosh.atm.R
import com.ashutosh.atm.adapter.AtmAdapter
import com.ashutosh.atm.adapter.AtmTransactionAdapter
import com.ashutosh.atm.databinding.ActivityMainBinding
import com.ashutosh.atm.utils.Helper.Companion.hideKeyboard
import com.ashutosh.atm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding:ActivityMainBinding?=null
    private val binding get() = _binding!!

    private val mainViewModel:MainViewModel by viewModels()

    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainViewModel.getData()
        mainViewModel.getAllTranData()
        mainViewModel.getLastTranData()

        bindAtmObserver()
        bindAtmAllTranObserver()
        bindAtmLastTranObserver()

        binding.rvATM.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvTran.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvLastTran.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.btnWithdraw.setOnClickListener {
            val validate = mainViewModel.validate(binding.txtWithdrawAmount.text.toString())
            hideKeyboard(it)
            if(validate.first){
                mainViewModel.addTransaction(binding.txtWithdrawAmount.text.toString().toInt())
            }
            else
            {
                Toast.makeText(this, validate.second, Toast.LENGTH_SHORT).show()
            }
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun bindAtmObserver(){
        mainViewModel.atmLiveData.observe(this, Observer{ listAtm ->
            if(listAtm ==null || listAtm.isEmpty()){
                mainViewModel.insertInitData()
            }
            else
            {
                val adapter =  AtmAdapter(listAtm)
                binding.rvATM.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun bindAtmAllTranObserver(){
        mainViewModel.atmAllTranLiveData.observe(this, Observer{ listAllTran ->
            if(listAllTran != null){
                val adapter = AtmTransactionAdapter(listAllTran)
                binding.rvTran.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun bindAtmLastTranObserver(){
        mainViewModel.atmLastTranLiveData.observe(this, Observer{ listlastTran ->
            if(listlastTran != null){
                val adapter = AtmTransactionAdapter(listlastTran)
                binding.rvLastTran.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}