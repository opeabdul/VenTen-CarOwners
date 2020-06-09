package com.example.opeyemiabdulsalam.carowners

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.opeyemiabdulsalam.R
import com.example.opeyemiabdulsalam.data.CarOwner
import com.example.opeyemiabdulsalam.databinding.CarOwnersListBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset

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
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
