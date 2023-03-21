package com.frakton.moviesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.frakton.moviesapp.R
import com.frakton.moviesapp.databinding.FragmentFilterBinding
import com.frakton.moviesapp.domain.enums.SortFiltersEnum
import com.frakton.moviesapp.domain.models.MovieFiltersModel
import com.frakton.moviesapp.ui.adapters.GenresFiltersRecyclerAdapter
import com.frakton.moviesapp.ui.viewmodels.MoviesViewModel
import com.frakton.moviesapp.util.toggleOrderingIcon
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterFragment : Fragment() {
    private lateinit var binding: FragmentFilterBinding
    private val viewModel: MoviesViewModel by activityViewModels()
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
        viewModel.getFilters(requireContext())
    }

    private fun observeViewModel() {
        viewModel.sortByData.observe(requireActivity()) { sortByList ->
            setSortBySpinner(sortByList)
        }

        viewModel.yearsData.observe(requireActivity()) { yearsList ->
            setFilterByYearSpinner(yearsList)
        }

        viewModel.sortBySpinnerSelection.observe(requireActivity()) { selectionPosition ->
            setSortBySpinnerSelection(selectionPosition)
        }

        viewModel.filterByYearSpinnerSelection.observe(requireActivity()) { selectionPosition ->
            setFilterByYearSpinnerSelection(selectionPosition)
        }

        viewModel.shouldToggleOrdering.observe(requireActivity()) { orderingValue ->
            if (orderingValue != getOrderingAbbr()) {
                binding.orderingIcon.toggleOrderingIcon(requireContext())
            }
        }

        viewModel.genresData.observe(requireActivity()) { genresList ->
            genresAdapter.updateStatus(genresList)
        }
    }

    private fun setSortBySpinnerSelection(selectionPosition: Int) =
        binding.sortBySpinner.setSelection(selectionPosition)

    private fun setFilterByYearSpinnerSelection(selectionPosition: Int) =
        binding.filterByYearSpinner.setSelection(selectionPosition)

    private fun setupGenresFiltersRecyclerView() {
        genresAdapter = GenresFiltersRecyclerAdapter()
        with(binding.genresRecyclerView) {
            adapter = genresAdapter
            layoutManager = GridLayoutManager(requireContext(), 4)
        }
    }

    private fun setSpinners() {
        with(requireContext()) {
            viewModel.setSortByList(this)
            viewModel.setFilterByYearList(this)
        }
    }

    private fun setSortBySpinner(sortByList: List<String>) {
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

    private fun setFilterByYearSpinner(yearsList: List<String>) {
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
        with(binding) {
            with(orderingIcon) {
                setOnClickListener {
                    toggleOrderingIcon(requireContext())
                }
            }

            root.setOnClickListener {
                viewModel.updateFilters(
                    MovieFiltersModel(
                        sortBy = getSortFilterId(),
                        ordering = getOrderingAbbr(),
                        filterByYear = filterByYearSpinner.selectedItem.toString(),
                        filterByGenres = genresAdapter.getCheckedGenres()
                    )
                )
                findNavController().navigateUp()
            }

            filterElementsContainer.setOnClickListener {
                //do nothing here
            }

            clearAllButton.setOnClickListener {
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
    }

    private fun getSortFilterId() = SortFiltersEnum.getSortFilterIdByName(
        filterName = binding.sortBySpinner.selectedItem.toString(),
        context = requireContext()
    )

    private fun resetFilters() {
        with(binding) {
            filterByYearSpinner.setSelection(viewModel.getYearsListDefaultValue())
            sortBySpinner.setSelection(0)
            genresAdapter.clearAllCheckmarks()
            with(orderingIcon) {
                if (text == getString(R.string.ascending)) {
                    toggleOrderingIcon(requireContext())
                }
            }
        }
    }

    private fun getOrderingAbbr() =
        if (binding.orderingIcon.text == getString(R.string.ascending)) getString(R.string.asc)
        else getString(R.string.desc)
}