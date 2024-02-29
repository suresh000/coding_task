package com.techjet.codingtask.view.list

import androidx.lifecycle.viewModelScope
import com.bajrangbali.stories.base.BaseViewModel
import kotlinx.coroutines.launch

class ListViewModel(val mRepository: ListRepository) : BaseViewModel() {

    init {
        callApi()
    }

    private fun callApi() {
        viewModelScope.launch {
            mRepository.callApi("cat", 1)
        }
    }
}