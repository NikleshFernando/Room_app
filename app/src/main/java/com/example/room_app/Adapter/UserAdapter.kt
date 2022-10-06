package com.example.room_app.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.room_app.Fragments.HomeFragmentDirections
import com.example.room_app.Model.User
import com.example.room_app.Model.UserDatabase
import com.example.room_app.databinding.UserItemsBinding
import kotlinx.android.synthetic.main.user_items.view.*

class UserAdapter(private val users:List<User>):RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(val binding: UserItemsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=UserItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user=users[position]
        holder.binding.firstname.text=user.first_name
        holder.binding.lastname.text=user.last_name
        holder.binding.userId.text=user.id.toString()
        holder.binding.userAge.text=user.age.toString()

        holder.binding.layoutclick.setOnClickListener {
            val action=HomeFragmentDirections.actionHomeFragmentToUpdateFragment(user)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}