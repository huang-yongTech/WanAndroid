package com.hy.wanandroid.ui.viewmodel

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hy.wanandroid.ui.DataModelPreference
import com.hy.wanandroid.ui.data.DataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataStoreViewModel : ViewModel() {
    var dataModelFlow: Flow<DataModel>? = emptyFlow()

    fun getText(dataStore: DataStore<DataModelPreference>?) {
        viewModelScope.launch(Dispatchers.IO) {
            dataModelFlow = dataStore?.data?.map { pref ->
                DataModel(pref.name, pref.age)
            }
        }
    }

    fun updateText(dataStore: DataStore<DataModelPreference>?) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore?.updateData {
                it.toBuilder()
                    .setAge(it.age + 1)
                    .setName(it.name + "1")
                    .build()
            }
        }
    }

    companion object {
        private const val DATA_STORE_TEXT_KEY = "DataStoreTextKey"
    }
}