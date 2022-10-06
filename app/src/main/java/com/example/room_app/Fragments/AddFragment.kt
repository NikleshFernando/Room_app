package com.example.room_app.Fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room_app.Model.User
import com.example.room_app.R
import com.example.room_app.UserViewmodel
import com.google.android.material.textfield.TextInputLayout

class AddFragment : Fragment() {
    lateinit var firstname:TextInputLayout
    lateinit var lastname:TextInputLayout
    lateinit var age:TextInputLayout
    lateinit var add:Button
    lateinit var viewmodel:UserViewmodel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add, container, false)

        firstname=view.findViewById(R.id.first_name)
        lastname=view.findViewById(R.id.last_name)
        age=view.findViewById(R.id.age)
        add=view.findViewById(R.id.btn_add)

        viewmodel=ViewModelProvider(this)[UserViewmodel::class.java]

        add.setOnClickListener {
            val first_name=firstname.editText?.text.toString()
            val last_name=lastname.editText?.text.toString()
            val age_txt=age.editText?.text.toString()

            if (TextUtils.isEmpty(first_name)){
                firstname.error="Field cannot be empty"
            }else if (TextUtils.isEmpty(last_name)){
                lastname.error="Field cannot be empty"
            }else if (TextUtils.isEmpty(age_txt)){
                age.error="Field cannot be empty"
            }else{
                addUser(first_name,last_name,age_txt)
            }
        }

        return view
    }

    private fun addUser(firstName: String, lastName: String, ageTxt: String) {
        val user=User(0,firstName,lastName,ageTxt.toInt())
        viewmodel.addUser(user)
        Toast.makeText(requireContext(),"Addition successful",Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_addFragment_to_homeFragment)
    }
}