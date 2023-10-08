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

    fun updateText(name: String, age: Int, dataStore: DataStore<DataModelPreference>?) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore?.updateData {
                it.toBuilder()
                    .setAge(if (it.age > 0) it.age + 1 else age)
                    .setName(name)
                    .build()
            }
        }
    }
}