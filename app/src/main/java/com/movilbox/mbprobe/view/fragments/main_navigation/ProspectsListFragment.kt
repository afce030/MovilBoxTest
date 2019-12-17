package com.movilbox.mbprobe.view.fragments.main_navigation

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.movilbox.mbprobe.R
import com.movilbox.mbprobe.view.adapters.ProspectsAdapter
import com.movilbox.mbprobe.viewmodels.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ProspectsListFragment : DaggerFragment() {

    private var currentPage: Int = 1

    private lateinit var recyclerView: RecyclerView
    private lateinit var prospectsAdapter: ProspectsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var vm: MainViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {

            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_prospects_list, container, false)
        vm = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        recyclerView = view.findViewById(R.id.rv_prospects)

        val token = sharedPreferences.getString("authToken", "")
        vm.requestProspectsRetrofit(token!!, currentPage)
        currentPage++

        vm.requestAllProspectsRoom()
        vm.prospectsArray.observe(viewLifecycleOwner, Observer { data ->

            setupAdapter(view)
            prospectsAdapter.addItems(data)

        })

        return view
    }

    private fun setupAdapter(view: View){
        prospectsAdapter = ProspectsAdapter(context!!, view.findNavController())
        val linearLayout = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = prospectsAdapter
        recyclerView.layoutManager = linearLayout
    }

}
