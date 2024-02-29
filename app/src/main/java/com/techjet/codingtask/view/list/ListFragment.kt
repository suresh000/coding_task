package com.techjet.codingtask.view.list

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.techjet.codingtask.R
import com.techjet.codingtask.base.BaseFragment
import com.techjet.codingtask.databinding.FragmentListBinding


class ListFragment : BaseFragment() {

    private lateinit var mBinding: FragmentListBinding
    private lateinit var mVm: ListViewModel
    private var gridLayoutManager: GridLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridLayoutManager = GridLayoutManager(requireContext(), resources.getInteger(R.integer.grid_column_count))
        mBinding.recyclerView.run {
            layoutManager = gridLayoutManager
        }

        val repository = ListRepository(requireContext())
        val factory = ListViewModelFactory(repository)
        mVm = ViewModelProvider(this, factory)[ListViewModel::class.java]
        mBinding.vm = mVm
        mBinding.repository = mVm.mRepository
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        gridLayoutManager?.spanCount = resources.getInteger(R.integer.grid_column_count)
    }

}