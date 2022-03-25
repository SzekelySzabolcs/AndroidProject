package com.example.project.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.project.Modell
import com.example.project.R
import com.example.project.databinding.FragmentTimelineBinding


class Timeline : Fragment() {
    private lateinit var binding: FragmentTimelineBinding
    val viewmodel: Modell by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTimelineBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.include.profile.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragmentContainerView,Profile.newInstance(viewmodel.name))
                ?.commit()
        }
    }


    companion object {

        fun newInstance() = Timeline()
    }

}