package com.veprek.honza.rocketlaunch.system.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.veprek.honza.rocketlaunch.ui.theme.RocketLaunchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketListFragment : Fragment() {
    private val viewModel: RocketListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val rockets = viewModel.rockets.collectAsState()
                RocketLaunchTheme() {
                    RocketListScreen(
                        rockets = rockets.value.data,
                        state = rockets.value.state,
                        toDetailAction = {
                                id ->
                            val action =
                                RocketListFragmentDirections.actionRocketListFragmentToRocketDetailFragment(id)
                            findNavController().navigate(action)
                        }
                    )
                }
            }
        }
    }
}
