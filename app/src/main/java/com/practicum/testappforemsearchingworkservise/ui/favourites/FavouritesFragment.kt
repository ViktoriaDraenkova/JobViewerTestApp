package com.practicum.testappforemsearchingworkservise.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practicum.testappforemsearchingworkservise.databinding.FavouritesFragmentBinding
import com.practicum.testappforemsearchingworkservise.presentation.favourites.FavouritesViewModel
import com.practicum.testappforemsearchingworkservise.ui.common.VacanciesViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesFragment : Fragment() {
    private var _binding: FavouritesFragmentBinding? = null
    private val viewModel: FavouritesViewModel by viewModel()

    private val binding get() = _binding!!
    private var vacanciesViewAdapter: VacanciesViewAdapter? = null
    private lateinit var recyclerViewVacancies: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavouritesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewVacancies = binding.recyclerVacancies
        vacanciesViewAdapter = VacanciesViewAdapter {
            viewModel.deleteFromFavourites(it)
        }
        binding.recyclerVacancies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = vacanciesViewAdapter
        }

        recyclerViewVacancies.adapter = vacanciesViewAdapter

        viewModel.getStateLiveData().observe(viewLifecycleOwner) {
            vacanciesViewAdapter!!.setList(it)
            binding.vacancyCount.text = makeVacanciesString(it.size)
        }

        viewModel.getFavourites()
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    private fun makeVacanciesString(count: Int): String {
        return when {
            count % 10 == 1 && count % 100 != 11 -> "$count вакансия"
            count % 10 in 2..4 && count % 100 !in 12..14 -> "$count вакансии"
            else -> "$count вакансий"
        }
    }
}