package com.example.room_app.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room_app.Adapter.UserAdapter
import com.example.room_app.R
import com.example.room_app.UserViewmodel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {
    lateinit var items:RecyclerView
    lateinit var btn:FloatingActionButton
    lateinit var viewmodel:UserViewmodel
    lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)
        items=view.findViewById(R.id.recycler_items)
        btn=view.findViewById(R.id.add_user)

        btn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
        viewmodel=ViewModelProvider(this)[UserViewmodel::class.java]

        items.setHasFixedSize(true)
        items.layoutManager=LinearLayoutManager(requireContext())
        viewmodel.readUsers.observe(viewLifecycleOwner, Observer { users->
            adapter=UserAdapter(users)
            items.adapter=adapter
            adapter.notifyDataSetChanged()
        })

        return view
    }

}