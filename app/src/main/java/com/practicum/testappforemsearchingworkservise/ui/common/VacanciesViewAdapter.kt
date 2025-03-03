package com.practicum.testappforemsearchingworkservise.ui.common

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicum.domain.models.Vacancy
import com.practicum.testappforemsearchingworkservise.R
import com.practicum.testappforemsearchingworkservise.databinding.VacancyPreviewBinding
import java.text.SimpleDateFormat
import java.util.Locale

class VacanciesViewAdapter(
    private val clickListener: OnClickListener,
) :
    RecyclerView.Adapter<VacanciesViewAdapter.ViewHolder>() {
    private var vacancies = mutableListOf<Vacancy>()

    fun setList(list: List<Vacancy>) {
        this.vacancies.clear()
        this.vacancies.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            VacancyPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding = binding,
            clickListener = clickListener,
        )
    }

    override fun getItemCount(): Int = vacancies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(vacancies[position])
    }

    class ViewHolder(
        private val binding: VacancyPreviewBinding,
        private val clickListener: OnClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(vacancy: Vacancy) {
            binding.countOfLooking.text = makeHuman(vacancy.lookingNumber)
            binding.city.text = vacancy.address.town
            binding.profession.text = vacancy.title
            binding.experience.text = vacancy.experience.previewText
            binding.publicationDate.text = "Опубликовано ${makeHumanDate(vacancy.publishedDate)}"
            binding.company.text = vacancy.company
            Log.d("isFavourite", vacancy.title + " " + vacancy.isFavorite)
            binding.like.setImageResource(if (vacancy.isFavorite) R.drawable.ic_heart_full else R.drawable.ic_heart_empty)
            binding.like.setOnClickListener {
                clickListener.onLikeClick(vacancy)
            }
        }
        private fun makeHuman(count: Int): String {
            return when {
                count % 10 == 1 && count % 100 != 11 -> "Сейчас просматривает $count человек"
                count % 10 in 2..4 && count % 100 !in 12..14 -> "Сейчас просматривает $count человека"
                else -> "Сейчас просматривает $count человек"
            }
        }

        private fun makeHumanDate(date: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale("ru"))
            val outputFormat = SimpleDateFormat("dd MMMM", Locale("ru"))

            return try {
                val parsedDate = inputFormat.parse(date)
                outputFormat.format(parsedDate!!)
            } catch (e: Exception) {
                "Неверный формат даты"
            }
        }
    }

    fun interface OnClickListener {
        fun onLikeClick(vacancy: Vacancy)
    }


}