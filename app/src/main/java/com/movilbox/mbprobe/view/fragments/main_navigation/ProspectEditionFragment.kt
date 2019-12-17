package com.movilbox.mbprobe.view.fragments.main_navigation

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.movilbox.mbprobe.R
import com.movilbox.mbprobe.model.persistence.entities.ProspectsEntity
import com.movilbox.mbprobe.viewmodels.MainViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_prospect_edition.*
import java.lang.IndexOutOfBoundsException
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ProspectEditionFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var vm: MainViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val argsproofs: ProspectEditionFragmentArgs by navArgs()

    private lateinit var entity: ProspectsEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_prospect_edition, container, false)
        vm = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        val updateButton = view.findViewById<Button>(R.id.btn_update)
        updateButton.setOnClickListener {

            val prospectsEntity = ProspectsEntity(
                id = entity.id,
                name = et_nombreEdit.text.toString(),
                surname = et_apellidosEdit.text.toString(),
                id_card = et_cedulaEdit.text.toString().toLong(),
                phone = et_phoneEdit.text.toString().toLong()
            )

            vm.updateProspect(listOf(prospectsEntity))
        }

        return view
    }

    override fun onStart() {
        super.onStart()

        val prospectId = argsproofs.id
        vm.requestProspectsByIdRoom(listOf(prospectId))

        vm.mediatorLiveData.observe(viewLifecycleOwner, Observer { data ->

            try {

                if(data[0] is ProspectsEntity) {

                    entity = data[0] as ProspectsEntity

                    et_nombreEdit.setText(entity.name)
                    et_apellidosEdit.setText(entity.surname)
                    et_cedulaEdit.setText(entity.id_card.toString())
                    et_phoneEdit.setText(entity.phone.toString())

                }else if(data[0] is Boolean){
                    findNavController().popBackStack()
                }

            }catch (e: IndexOutOfBoundsException){}

        })

    }

}
