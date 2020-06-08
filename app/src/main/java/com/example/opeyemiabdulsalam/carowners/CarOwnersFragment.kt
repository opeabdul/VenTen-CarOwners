package com.example.opeyemiabdulsalam.carowners

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.opeyemiabdulsalam.R
import com.example.opeyemiabdulsalam.databinding.CarOwnersListBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CarOwnersFragment : Fragment() {

    private val viewModel: CarOwnerViewModel by lazy {
        ViewModelProviders.of(this).get(CarOwnerViewModel::class.java)
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = CarOwnersListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
