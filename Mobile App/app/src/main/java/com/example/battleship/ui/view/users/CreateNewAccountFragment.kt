package com.example.battleship.ui.view.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.battleship.R
import com.example.battleship.data.api.RetrofitInstance
import com.example.battleship.data.models.PlayerRegister
import com.example.battleship.ui.viewmodel.users.UserViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.runBlocking

class CreateNewAccountFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        userViewModel.createUser("test", "", "")
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_create_new_account, container, false)



        var new_acc_button = view.findViewById<Button>(R.id.new_acc_button)

        new_acc_button.setOnClickListener {
            runBlocking {
//                val call = RetrofitInstance.api.getTodos()
//                Log.d("BODY!", call.body().toString())
                var email = view.findViewById<TextInputEditText>(R.id.login_emailTetxInputEditText).text
                var name = view.findViewById<TextInputEditText>(R.id.new_acc_nameTextInputEditText).text
                var password = view.findViewById<TextInputEditText>(R.id.login_passwordTextInputEditText).text

                var emailAddressValidation = userViewModel.validateEmailAddress(email.toString())
                var passSecurityValidation = userViewModel.validatePassSecurity(password.toString())
                var namePresenceValidation = name.toString().isNotEmpty()

                if (emailAddressValidation && passSecurityValidation && namePresenceValidation){
                    val register_response = RetrofitInstance.api.register(PlayerRegister(name.toString(), email.toString(), password.toString()))

                    if (register_response.isSuccessful){
                        Toast.makeText(
                            activity,
                            "La cuenta ha sido creada con exito!",
                            Toast.LENGTH_SHORT
                        ).show();
                        Navigation.findNavController(it).navigate(R.id.action_createNewAccountFragment_to_userLoginFragment)
                    }

                }else if (!passSecurityValidation) {
                    Toast.makeText(
                        activity,
                        "Recuerda que se requieren 6 o mas caracteres para la contraseña!",
                        Toast.LENGTH_SHORT
                    ).show();
                }
                else if (!emailAddressValidation) {
                    Toast.makeText(
                        activity,
                        "Correo invalido!",
                        Toast.LENGTH_SHORT
                    ).show();
                }
                else if (!namePresenceValidation) {
                    Toast.makeText(
                        activity,
                        "El nombre es requerido!",
                        Toast.LENGTH_SHORT
                    ).show();
                }
            }
        }

        return view
    }
}