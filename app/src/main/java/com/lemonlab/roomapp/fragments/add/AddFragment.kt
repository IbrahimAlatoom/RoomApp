package com.lemonlab.roomapp.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lemonlab.roomapp.R
import com.lemonlab.roomapp.data.User
import com.lemonlab.roomapp.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {
    private lateinit var userViewModel : UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        add_btn.setOnClickListener{
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val firstName = addFirstName.text.toString()
        val lastName = addLastName.text.toString()
        val age = addAge.text

        if (inputCheck(firstName,lastName,age)){
//            Create User Object
            val user = User(0,firstName,lastName,Integer.parseInt(age.toString()))
//            Add Data to Database
            userViewModel.addUser(user)
            Toast.makeText(requireContext(),"Successfully added!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please Fill Out All Fields",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(first:String,last:String,age:Editable): Boolean {
        return !(TextUtils.isEmpty(first)&&TextUtils.isEmpty(last)&&age.isEmpty())
    }

}