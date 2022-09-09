package com.ashutosh.atm.viewmodel

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashutosh.atm.model.AtmModel
import com.ashutosh.atm.model.AtmTransactionModel
import com.ashutosh.atm.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val TAG = "MainViewModel"

    val atmLiveData: LiveData<List<AtmModel>>
        get() = mainRepository.atmLiveData


    val atmAllTranLiveData: LiveData<List<AtmTransactionModel>>
        get() = mainRepository.atmAllTranLiveData

    val atmLastTranLiveData: LiveData<List<AtmTransactionModel>>
        get() = mainRepository.atmLastTranLiveData

    private val initAtmModel = AtmModel(1,100000,100,100,100,10)

    fun insertInitData(){
        viewModelScope.launch {
            mainRepository.insertInitData(initAtmModel)
        }
        getData()
    }

    fun getData(){
        viewModelScope.launch {
            mainRepository.getAtmData()
        }
    }

    fun getAllTranData(){
        viewModelScope.launch {
            mainRepository.getAtmAllTranData()
        }
    }

    fun getLastTranData(){
        viewModelScope.launch {
            mainRepository.getAtmLastTranData()
        }
    }

    fun updateAtm(atmModel:AtmModel){
        viewModelScope.launch {
            mainRepository.updateAtmData(atmModel)
        }
    }


    fun addTransaction(amount:Int){

        val tranData = getTranData(amount)

        viewModelScope.launch {
            mainRepository.insertAtmTransaction(tranData)
        }

        getAllTranData()
        getLastTranData()

        val newAtmData = getUpdatedAtmData(tranData)
        updateAtm(newAtmData)

        getData()
    }

    fun getUpdatedAtmData(tranData: AtmTransactionModel):AtmModel{
        val atmData = atmLiveData.value?.get(0)
        atmData!!.amount -= tranData.amount
        atmData!!.notes_two_thousand -= tranData.notes_two_thousand
        atmData!!.notes_five_hun -= tranData.notes_five_hun
        atmData!!.notes_two_hun -= tranData.notes_two_hun
        atmData!!.notes_one_hun -= tranData.notes_one_hun
        return atmData!!
    }

    fun getTranData(amount: Int):AtmTransactionModel{

        val atmData = atmLiveData.value?.get(0)
        val twoThNotes = getNotes(amount,atmData!!.notes_two_thousand,0,2000)
        val fiveHunNotes = getNotes(amount,atmData.notes_five_hun,twoThNotes*2000,500)
        val twoHunNotes = getNotes(amount,atmData.notes_two_hun,twoThNotes*2000+fiveHunNotes*500,200)
        val oneHunNotes = getNotes(amount,atmData.notes_one_hun,twoThNotes*2000+fiveHunNotes*500+twoHunNotes*200,100)

        return AtmTransactionModel(amount,oneHunNotes,twoHunNotes,fiveHunNotes,twoThNotes,System.currentTimeMillis())
    }

    fun getNotes(amt: Int,availableNotes:Int,previousAmount:Int,noteValue:Int):Int{
        var notes = 0
        val amount = amt - previousAmount
        Log.d(TAG, "getNotes: noteValue- $noteValue previousAmount- $previousAmount amount- $amount")
        for (i in amount downTo 0 step 100)
        {
            if(i%noteValue == 0 && i/noteValue <= availableNotes){
                notes = i/noteValue
                break
            }
        }
        return notes
    }

//    fun getTwoThouNotes(amt: Int,availableNotes:Int):Int{
//        var notes = 0
//        val amount = amt
//        for (i in amount downTo 0 step 100)
//        {
//            if(i%2000 == 0 && i/2000 <= availableNotes){
//                notes = i/2000
//                break
//            }
//        }
//        return notes
//    }
//
//
//
//    fun getFiveHunNotes(amt: Int, availableNotes: Int, twoThNotesAmt: Int): Int {
//        var notes = 0
//        val amount = amt - twoThNotesAmt
//        if (amount >= 100) {
//            for (i in amount downTo 0 step 100) {
//                if (i % 500 == 0 && i / 500 <= availableNotes) {
//                    notes = i / 500
//                    break
//                }
//            }
//        }
//        return notes
//    }



    fun validate(amount: String):Pair<Boolean,String>{

        val atmData = atmLiveData.value?.get(0)
        val availableAmount = atmData!!.amount

        var result = Pair(true,"")

        if(TextUtils.isEmpty(amount))
        {
            result = Pair(false,"Enter valid amount")
        }
        else if(amount.toInt() < 100){
            result = Pair(false,"Amount should be greater than or equal to 100")
        }
        else if(amount.toInt() % 100 != 0){
            result = Pair(false,"Amount should be in multiple of 100")
        }
        else if(amount.toInt() > availableAmount){
            result = Pair(false,"Amount should not be greater than available balance")
        }

        return result
    }



}