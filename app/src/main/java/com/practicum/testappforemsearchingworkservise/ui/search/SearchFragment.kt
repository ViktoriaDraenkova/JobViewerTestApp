package com.practicum.testappforemsearchingworkservise.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practicum.testappforemsearchingworkservise.databinding.SearchFragmentBinding
import com.practicum.testappforemsearchingworkservise.presentation.search.SearchViewModel
import com.practicum.testappforemsearchingworkservise.ui.common.InfoViewAdapter
import com.practicum.testappforemsearchingworkservise.ui.common.VacanciesViewAdapter
import com.practicum.testappforemsearchingworkservise.ui.videos.VacancyScreenState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private var _binding: SearchFragmentBinding? = null
    private val viewModel: SearchViewModel by viewModel()

    private val binding get() = _binding!!
    private var vacanciesViewAdapter: VacanciesViewAdapter? = null
    private var infoViewAdapter: InfoViewAdapter? = null

    private lateinit var recyclerViewVacancies: RecyclerView
    private lateinit var recyclerViewInfo: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewVacancies = binding.recyclerVacancies
        recyclerViewInfo = binding.useBar

        vacanciesViewAdapter = VacanciesViewAdapter {
            viewModel.switchFavourite(it)
        }

        infoViewAdapter = InfoViewAdapter {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(it.link)
            }
            startActivity(intent)
        }

        viewModel.getStateLiveData().observe(viewLifecycleOwner) { state ->
            when (state) {
                is VacancyScreenState.Content -> {
                    vacanciesViewAdapter!!.setList(state.vacancyResponse.vacancies)
                    infoViewAdapter!!.setList(state.vacancyResponse.offers)
                    binding.vacancyCount.text = makeVacanciesString(state.vacancyResponse.vacancies.size)
                }

                is VacancyScreenState.Loading -> {
                    // TODO
                }

                is VacancyScreenState.Error -> {
                    // TODO
                }
            }
        }
        binding.recyclerVacancies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = vacanciesViewAdapter
        }

        binding.useBar.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = infoViewAdapter
        }

        recyclerViewVacancies.adapter = vacanciesViewAdapter
        recyclerViewInfo.adapter = infoViewAdapter

        viewModel.getVacancies()
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