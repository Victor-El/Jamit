package me.codeenzyme.jamit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.flutterwave.raveandroid.RaveUiManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.Email
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import me.codeenzyme.jamit.databinding.FragmentPaymentBinding
import java.util.*
import android.widget.Toast

import com.flutterwave.raveandroid.RavePayActivity

import com.flutterwave.raveandroid.rave_java_commons.RaveConstants

import android.content.Intent
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import me.codeenzyme.jamit.models.RaveResponse


class PaymentFragment: Fragment(R.layout.fragment_payment), Validator.ValidationListener {

    private var viewBinding: FragmentPaymentBinding? = null

    @NotEmpty(trim = true)
    private var firstNameEditText: TextInputEditText? = null

    @NotEmpty(trim = true)
    private var lastNameEditText: TextInputEditText? = null

    @Email
    private var emailEditText: TextInputEditText? = null

    private lateinit var validator: Validator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        validator = Validator(this)
        validator.setValidationListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentPaymentBinding.bind(view)

        viewBinding?.let {
            it.toolbar.setupWithNavController(findNavController())

            it.continueBtn.setOnClickListener {
                validator.validate()
            }

            firstNameEditText = it.firstNameTextInputEditText
            lastNameEditText = it.lastNameTextInputEditText
            emailEditText = it.emailTextInputEditText
        }
    }


    private fun showRaveUI(firstName: String, lastName: String, email: String) {
        RaveUiManager(this).setAmount(500.0)
            .setCurrency("NGN")
            .setEmail(email)
            .setfName(firstName)
            .setlName(lastName)
            .setNarration("Subscribe to Jamit")
            .setPublicKey(PUBLIC_KEY)
            .setEncryptionKey(ENCRYPTION_KEY)
            .setTxRef(UUID.randomUUID().toString())
            .setPhoneNumber("", true)
            //.acceptAccountPayments(true)
            .acceptCardPayments(true)
            .acceptMpesaPayments(false)
            .acceptAchPayments(false)
            .acceptGHMobileMoneyPayments(false)
            .acceptUgMobileMoneyPayments(false)
            .acceptZmMobileMoneyPayments(false)
            .acceptRwfMobileMoneyPayments(false)
            .acceptSaBankPayments(false)
            .acceptUkPayments(false)
            //.acceptBankTransferPayments(true)
            //.acceptUssdPayments(true)
            //.acceptBarterPayments(true)
            .acceptFrancMobileMoneyPayments(false, "NG")
            .allowSaveCardFeature(true)
            .onStagingEnv(true)
            .setMeta(emptyList())
            .withTheme(R.style.CustomRaveTheme)
            .isPreAuth(false)
            .shouldDisplayFee(true)
            .initialize();
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    override fun onValidationSucceeded() {
        viewBinding?.let {
            showRaveUI(
                it.firstNameTextInputEditText.text.toString().trim(),
                it.lastNameTextInputEditText.text.toString().trim(),
                it.emailTextInputEditText.text.toString().trim(),
            )
        }
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        for (err in errors!!) {
            val view = err.view as TextInputEditText
            view.error = err.getCollatedErrorMessage(requireContext())
            //(view.parent as TextInputLayout).error = err.getCollatedErrorMessage(requireContext())
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            val message = data.getStringExtra("response")
            val parsedData = Gson().fromJson<RaveResponse>(message, RaveResponse::class.java)
            when (resultCode) {
                RavePayActivity.RESULT_SUCCESS -> {
                    Toast.makeText(requireContext(), "SUCCESS", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(
                        PaymentFragmentDirections.actionPaymentFragmentToSuccessFragment(parsedData)
                    )
                }
                RavePayActivity.RESULT_ERROR -> {
                    val snackbar = Snackbar.make(
                        viewBinding!!.root,
                        parsedData.data.vbvrespmessage,
                        Snackbar.LENGTH_INDEFINITE
                    )
                    snackbar.setAction("Dismiss") {
                        snackbar.dismiss()
                    }
                    snackbar.show()
                }
                RavePayActivity.RESULT_CANCELLED -> {
                    Toast.makeText(requireContext(), "CANCELLED", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}