package com.techjet.codingtask.view.main

import androidx.databinding.ObservableField
import com.bajrangbali.stories.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    @JvmField
    val title = ObservableField<String>()

}