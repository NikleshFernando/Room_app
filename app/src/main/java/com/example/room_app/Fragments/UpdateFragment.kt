package com.example.room_app.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room_app.Model.User
import com.example.room_app.R
import com.example.room_app.UserViewmodel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {
    lateinit var update:Button
    lateinit var viewmodel:UserViewmodel

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update, container, false)

        viewmodel=ViewModelProvider(this)[UserViewmodel::class.java]

        view.first_name_update.editText?.setText(args.currentuser.first_name)
        view.last_name_update.editText?.setText(args.currentuser.last_name)
        view.age_update.editText?.setText(args.currentuser.age.toString())

        update=view.findViewById(R.id.btn_update)

        update.setOnClickListener {
            val first_name=first_name_update.editText?.text.toString()
            val last_name=last_name_update.editText?.text.toString()
            val age=age_update.editText?.text.toString()

            if (TextUtils.isEmpty(first_name)){
                first_name_update.error="Field cannot be empty"
            }else if (TextUtils.isEmpty(last_name)){
                last_name_update.error="Field cannot be empty"
            }else if (TextUtils.isEmpty(age)){
                age_update.error="Field cannot be empty"
            }else{
                updateUser(first_name,last_name,age.toInt())
            }
        }
        setHasOptionsMenu(true)

        return view
    }

    private fun updateUser(firstName: String, lastName: String, age: Int) {
        val user=User(args.currentuser.id,firstName,lastName,age)
        viewmodel.updateUser(user)
        Toast.makeText(requireContext(),"Update successful",Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.bar_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val alert=AlertDialog.Builder(requireContext())
        alert.setPositiveButton("Yes"){_,_->
            viewmodel.deleteUser(args.currentuser)
            Toast.makeText(requireContext(),"Successfully deleted ${args.currentuser.first_name}",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }
        alert.setNegativeButton("No"){_,_->

        }
        alert.setTitle("Delete ${args.currentuser.first_name}")
        alert.setMessage("Are you sure you want to delete ${args.currentuser.first_name}?")
        alert.create().show()
    }
}