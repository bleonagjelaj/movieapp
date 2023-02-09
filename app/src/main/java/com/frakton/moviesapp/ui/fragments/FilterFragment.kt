package com.frakton.moviesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.frakton.moviesapp.R
import com.frakton.moviesapp.databinding.FragmentFilterBinding
import com.frakton.moviesapp.ui.adapters.GenresFiltersRecyclerAdapter


class FilterFragment : Fragment() {
    private lateinit var binding: FragmentFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(layoutInflater, container, false)
        val transitionInflater = androidx.transition.TransitionInflater.from(requireContext())
        enterTransition = transitionInflater.inflateTransition(R.transition.slide_right)
        exitTransition = transitionInflater.inflateTransition(R.transition.slide_right)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        setSpinners()
        setupGenresFiltersRecyclerView()
    }

    private fun setupGenresFiltersRecyclerView() {
        val genresAdapter = GenresFiltersRecyclerAdapter()
        binding.genresRecyclerView.adapter = genresAdapter
        binding.genresRecyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
    }

    private fun setSpinners() {
        setSortBySpinner()
        setFilterByYearSpinner()
    }

    private fun setSortBySpinner() {
        val sortByList = listOf("Popularity")
        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            sortByList
        )
        binding.sortBySpinner.adapter = spinnerArrayAdapter
        binding.sortBySpinner.setSelection(0)
    }

    private fun setFilterByYearSpinner() {
        //TODO("Not yet implemented")
    }

    private fun setClickListeners() {
        binding.orderingIcon.setOnClickListener {
            toggleOrderingIcon()
        }

        binding.root.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        binding.filterElementsContainer.setOnClickListener {
            //do nothing here
        }
    }

    private fun toggleOrderingIcon() {
        if (binding.orderingIcon.text == getString(R.string.ascending)) {
            binding.orderingIcon.text = getString(R.string.descending)
            binding.orderingIcon.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                0,
                R.drawable.ic_descending
            )
        } else {
            binding.orderingIcon.text = getString(R.string.ascending)
            binding.orderingIcon.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                0,
                R.drawable.ic_ascending
            )
        }
    }
}