package com.movilbox.mbprobe.view.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.movilbox.mbprobe.R
import com.movilbox.mbprobe.model.persistence.entities.ProspectsEntity
import com.movilbox.mbprobe.view.fragments.main_navigation.ProspectsListFragmentDirections
import kotlinx.android.synthetic.main.prospect_item.view.*

class ProspectsAdapter(
    private val context: Context,
    private val navController: NavController
) : RecyclerView.Adapter<ProspectsAdapter.ProspectsViewHolder>() {

    private var prospectsList = mutableListOf<ProspectsEntity>()

    fun addItems(prospects: List<ProspectsEntity>){
        this.prospectsList.addAll( prospects )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProspectsViewHolder {
        return ProspectsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.prospect_item, parent, false)
        )
    }

    override fun getItemCount() = prospectsList.size

    override fun onBindViewHolder(holder: ProspectsViewHolder, position: Int) {

        val item = holder.itemView

        item.txt_nombre.text = prospectsList[position].name
        item.txt_apellido.text = prospectsList[position].surname
        item.txt_cedula.text = prospectsList[position].id_card.toString()
        item.txt_telefono.text = prospectsList[position].phone.toString()

        when(prospectsList[position].statusCd){
            0 -> {
                item.icon_state.setImageResource(R.drawable.pending_24dp)
            }
            1 -> {
                item.icon_state.setImageResource(R.drawable.approved_24dp)
            }
            2 -> {
                item.icon_state.setImageResource(R.drawable.accepted_24dp)
            }
            3 -> {
                item.icon_state.setImageResource(R.drawable.rejected_24dp)
            }
            4 -> {
                item.icon_state.setImageResource(R.drawable.disabled_24dp)
            }
        }

        val action = ProspectsListFragmentDirections.actionProspectsListFragmentToProspectEditionFragment(
            prospectsList[position].id!!
        )

        item.setOnClickListener {
            navController.navigate(action)
        }

    }

    inner class ProspectsViewHolder(view: View): RecyclerView.ViewHolder(view)

}