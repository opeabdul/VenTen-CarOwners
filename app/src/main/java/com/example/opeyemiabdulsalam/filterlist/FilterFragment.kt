package com.example.opeyemiabdulsalam.filterlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.opeyemiabdulsalam.R
import com.example.opeyemiabdulsalam.databinding.FilterListBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FilterFragment : Fragment() {

    private val viewModel: FilterViewModel by lazy {
        ViewModelProviders.of(this).get(FilterViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FilterListBinding.inflate(inflater)

        binding.setLifecycleOwner (this)

        binding.viewModel = viewModel

        val adapter = FilterAdapter(FilterClickListener { filter ->
            val action = FilterFragmentDirections.actionFirstFragmentToSecondFragment(filter)
            findNavController().navigate(action)
        })

        binding.carFilterList.adapter =  adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
