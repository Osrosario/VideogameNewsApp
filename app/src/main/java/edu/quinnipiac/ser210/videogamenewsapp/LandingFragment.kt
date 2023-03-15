package edu.quinnipiac.ser210.videogamenewsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.quinnipiac.ser210.videogamenewsapp.databinding.FragmentLandingBinding

class LandingFragment : Fragment()
{
    private var _binding: FragmentLandingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        _binding = FragmentLandingBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.exploreButton.setOnClickListener {
            TODO()
        }

        return view
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}