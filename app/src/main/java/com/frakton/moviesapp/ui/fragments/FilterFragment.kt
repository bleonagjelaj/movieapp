package com.frakton.moviesapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.frakton.moviesapp.R
import com.frakton.moviesapp.databinding.FragmentFilterBinding
import com.frakton.moviesapp.domain.enums.SortFiltersEnum
import com.frakton.moviesapp.domain.models.MovieFiltersModel
import com.frakton.moviesapp.ui.adapters.GenresFiltersRecyclerAdapter
import com.frakton.moviesapp.ui.viewmodels.FilterMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class FilterFragment : Fragment() {
    private lateinit var binding: FragmentFilterBinding
    private val viewModel: FilterMoviesViewModel by viewModels()
    private lateinit var sortByList: List<String>
    private val yearsList = arrayListOf<String>()
    private lateinit var genresAdapter: GenresFiltersRecyclerAdapter

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
        setSpinners()
        setupGenresFiltersRecyclerView()
        setClickListeners()
        observeViewModel()
        viewModel.getFilters()
    }

    private fun observeViewModel() {
        viewModel.filtersData.observe(this.viewLifecycleOwner) { movieFilters ->
            setFiltersValues(
                sortByValue = getSortFilterName(movieFilters.sortBy),
                yearValue = movieFilters.filterByYear,
                ordering = movieFilters.ordering,
                genresValue = movieFilters.filterByGenres
            )
        }
    }

    private fun getSortFilterName(filterId: String?) =
        SortFiltersEnum.getSortFilterNameFromId(
            filterId = filterId,
            context = requireContext()
        )

    private fun setFiltersValues(
        sortByValue: String?,
        yearValue: String?,
        ordering: String?,
        genresValue: List<Int>?
    ) {
        val sortByValuePosition = sortByList.indexOfFirst { it == sortByValue }
        Log.d("belonatag", "setFiltersValues: $sortByValue, $sortByValuePosition")
        if (sortByValuePosition != -1) {
            binding.sortBySpinner.setSelection(sortByValuePosition)
        }
        val filterByYearValuePosition = yearsList.indexOfFirst { it == yearValue }
        binding.filterByYearSpinner.setSelection(
            if (filterByYearValuePosition != -1) filterByYearValuePosition else yearsList.lastIndex
        )
        if (ordering == getString(R.string.desc)) {
            toggleOrderingIcon()
        }
        genresValue?.let { genresAdapter.updateStatus(it) }
    }

    private fun setupGenresFiltersRecyclerView() {
        genresAdapter = GenresFiltersRecyclerAdapter()
        binding.genresRecyclerView.adapter = genresAdapter
        binding.genresRecyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
    }

    private fun setSpinners() {
        setSortBySpinner()
        setFilterByYearSpinner()
    }

    private fun setSortBySpinner() {
        sortByList = SortFiltersEnum.getSortFilters(requireContext())
        val sortSpinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            sortByList
        )
        with(binding.sortBySpinner) {
            adapter = sortSpinnerArrayAdapter
            setSelection(0)
        }
    }

    private fun setFilterByYearSpinner() {
        for (year in 1900..Calendar.getInstance().get(Calendar.YEAR)) {
            yearsList.add(year.toString())
        }
        yearsList.add(getString(R.string.all_years))
        val yearsSpinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            yearsList
        )
        with(binding.filterByYearSpinner) {
            adapter = yearsSpinnerArrayAdapter
            setSelection(yearsList.lastIndex)
        }
    }

    private fun setClickListeners() {
        binding.orderingIcon.setOnClickListener {
            toggleOrderingIcon()
        }

        binding.root.setOnClickListener {
            viewModel.updateFilters(
                MovieFiltersModel(
                    sortBy = getSortFilterId(),
                    ordering = getOrderingAbbr(),
                    filterByYear = binding.filterByYearSpinner.selectedItem.toString(),
                    filterByGenres = genresAdapter.getCheckedGenres()
                )
            )
            findNavController().navigateUp()
        }

        binding.filterElementsContainer.setOnClickListener {
            //do nothing here
        }

        binding.clearAllButton.setOnClickListener {
            viewModel.updateFilters(
                MovieFiltersModel(
                    sortBy = SortFiltersEnum.POPULARITY.filterId,
                    ordering = getString(R.string.desc),
                    filterByYear = null,
                    filterByGenres = null
                )
            )
            resetFilters()
        }
    }

    private fun getSortFilterId() = SortFiltersEnum.getSortFilterIdByName(
        filterName = binding.sortBySpinner.selectedItem.toString(),
        context = requireContext()
    )


    private fun resetFilters() {
        binding.filterByYearSpinner.setSelection(yearsList.lastIndex)
        binding.sortBySpinner.setSelection(0)
        genresAdapter.clearAllCheckmarks()
        if (binding.orderingIcon.text == getString(R.string.ascending)) {
            toggleOrderingIcon()
        }
    }

    private fun getOrderingAbbr() =
        if (binding.orderingIcon.text == getString(R.string.ascending)) getString(R.string.asc)
        else getString(R.string.desc)

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