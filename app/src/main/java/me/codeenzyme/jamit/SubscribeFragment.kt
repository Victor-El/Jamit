package me.codeenzyme.jamit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.flutterwave.raveandroid.RaveUiManager
import me.codeenzyme.jamit.databinding.FragmentSubscribeBinding
import me.codeenzyme.jamit.databinding.FragmentSuccessBinding
import java.util.*


class SubscribeFragment: Fragment(R.layout.fragment_subscribe) {

    private var viewBinding: FragmentSubscribeBinding? = null

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentSubscribeBinding.bind(view)

        viewBinding?.let {
            it.subscribeBtn.setOnClickListener {
                findNavController().navigate(SubscribeFragmentDirections.actionSubscribeFragmentToPaymentFragment())
            }


            if (viewModel.subscriptionState) {
                it.subscribeBtn.isEnabled = false
                it.subscriptionText.text = "Already subscribed"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

}