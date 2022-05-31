package com.chernybro.wb52.presentation.hero_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chernybro.wb52.R
import com.chernybro.wb52.databinding.FragmentHeroDetailsBinding
import com.chernybro.wb52.presentation.BaseFragment
import com.chernybro.wb52.presentation.hero_list_screen.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HeroDetailsFragment : Fragment(), BaseFragment {

    private var _binding: FragmentHeroDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val KEY_HERO_ID = "KEY_HERO_ID"
        const val KEY_HERO_NAME = "KEY_HERO_NAME"
    }

    private val vm: HeroDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHeroDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        arguments?.getInt(KEY_HERO_ID)?.let { vm.getHeroDetails(it) }
        (activity as MainActivity).setToolbarTitle(
            arguments?.getString(
                KEY_HERO_NAME,
                getFragmentTitle()
            ) ?: getFragmentTitle()
        )

        vm.heroDetails.observe(viewLifecycleOwner) { hero ->
            binding.apply {
//                heroName.text = hero.name
//                banner.load(hero.imageUrl)

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getFragmentTitle(): String {
        return getString(R.string.hero_name)
    }
}