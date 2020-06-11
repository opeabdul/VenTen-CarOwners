package com.example.opeyemiabdulsalam.carowners

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.opeyemiabdulsalam.R
import com.example.opeyemiabdulsalam.databinding.CarOwnersListBinding
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CarOwnersFragment : Fragment() {

    private val args: CarOwnersFragmentArgs by navArgs()

    private val viewModel by viewModels<CarOwnerViewModel> {
        CarOwnerViewModelFactory(requireActivity().application, args.filterValue)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = CarOwnersListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val adapter = CarOwnerAdapter()
        binding.carOwnersList.adapter = adapter

        viewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if(it == true){
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.no_csv_file_supplied)
                    , Snackbar.LENGTH_LONG).show()
                viewModel.doneShowingSnackbar()
            }
        })
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().onBackPressedDispatcher
            .addCallback(this, object :OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    findNavController().navigateUp()
                }
            })
    }
}
