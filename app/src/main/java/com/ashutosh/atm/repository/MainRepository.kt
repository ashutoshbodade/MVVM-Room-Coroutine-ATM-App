package com.ashutosh.atm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ashutosh.atm.data.AtmDao
import com.ashutosh.atm.data.AtmTransactionsDao
import com.ashutosh.atm.model.AtmModel
import com.ashutosh.atm.model.AtmTransactionModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor ( private val atmDao: AtmDao,private val atmTransactionsDao: AtmTransactionsDao){


    private val _atmLiveData = MutableLiveData<List<AtmModel>>()
    val atmLiveData: LiveData<List<AtmModel>>
        get() = _atmLiveData


    private val _atmAllTranLiveData = MutableLiveData<List<AtmTransactionModel>>()
    val atmAllTranLiveData: LiveData<List<AtmTransactionModel>>
        get() = _atmAllTranLiveData

    private val _atmlastTranLiveData = MutableLiveData<List<AtmTransactionModel>>()
    val atmLastTranLiveData: LiveData<List<AtmTransactionModel>>
        get() = _atmlastTranLiveData

    suspend fun insertInitData(atmModel: AtmModel) {
        atmDao.insertAtm(atmModel)
    }

    suspend fun getAtmData(){
        _atmLiveData.postValue(atmDao.getAtmData())
    }

    suspend fun insertAtmTransaction(atmTransactionModel: AtmTransactionModel) {
        atmTransactionsDao.insertAtmTran(atmTransactionModel)
    }

    suspend fun getAtmAllTranData(){
        _atmAllTranLiveData.postValue(atmTransactionsDao.getAllAtmTranData())
    }

    suspend fun getAtmLastTranData(){
        _atmlastTranLiveData.postValue(atmTransactionsDao.getLastAtmTranData())
    }

    suspend fun updateAtmData(atmModel: AtmModel) {
        atmDao.updateAtm(atmModel)
    }


}