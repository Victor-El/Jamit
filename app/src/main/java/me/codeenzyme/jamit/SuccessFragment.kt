package me.codeenzyme.jamit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import me.codeenzyme.jamit.databinding.FragmentSuccessBinding

class SuccessFragment: Fragment(R.layout.fragment_success) {

    private var viewBinding: FragmentSuccessBinding? = null

    private val arg by navArgs<SuccessFragmentArgs>()

    val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentSuccessBinding.bind(view)

        viewBinding?.let {
            it.payerNameText.text = arg.trx.data.customerFullName
            it.subscriptionText.text = arg.trx.data.vbvrespmessage

            it.continueBtn.setOnClickListener {
                viewModel.subscriptionState = true
                findNavController().navigate(
                    SuccessFragmentDirections.actionSuccessFragmentToSubscribeFragment()
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

}