package com.techjet.codingtask.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.techjet.codingtask.R
import com.techjet.codingtask.base.BaseFragment
import com.techjet.codingtask.databinding.FragmentListBinding


class ListFragment : BaseFragment() {

    private lateinit var mBinding: FragmentListBinding
    private lateinit var mVm: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        val repository = ListRepository()
        val factory = ListViewModelFactory(repository)
        mVm = ViewModelProvider(this, factory)[ListViewModel::class.java]
        mBinding.vm = mVm
        mBinding.repository = mVm.mRepository

        return mBinding.root
    }

}