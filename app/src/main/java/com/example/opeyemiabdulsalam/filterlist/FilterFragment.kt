package com.example.opeyemiabdulsalam.filterlist

import android.Manifest
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.opeyemiabdulsalam.R
import com.example.opeyemiabdulsalam.databinding.FilterListBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FilterFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: FilterViewModel by lazy {
        ViewModelProviders.of(this).get(FilterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FilterListBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.refreshFromServer.setOnRefreshListener(this)

        val adapter = FilterAdapter(FilterClickListener { filter ->
            Dexter.withActivity(requireActivity())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        val action =
                            FilterFragmentDirections.actionFirstFragmentToSecondFragment(filter)
                        findNavController().navigate(action)
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permission: PermissionRequest?,
                        token: PermissionToken?
                    ) {
                        AlertDialog.Builder(requireContext())
                            .setMessage("Storage access is needed so the file to be filtered " +
                                    " be accessed. Kindly click on allow.")
                            .setPositiveButton("Ok", null)
                            .show()
                        token?.continuePermissionRequest()
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {

                    }

                })
                .check()
        })

        binding.carFilterList.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onRefresh(){
        viewModel.getFilters()
    }

}
