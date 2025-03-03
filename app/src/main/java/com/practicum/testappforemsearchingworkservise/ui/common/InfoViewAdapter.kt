package com.practicum.testappforemsearchingworkservise.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicum.domain.models.Offer
import com.practicum.domain.models.Vacancy
import com.practicum.testappforemsearchingworkservise.R
import com.practicum.testappforemsearchingworkservise.databinding.InfoCardsBinding
import com.practicum.testappforemsearchingworkservise.databinding.VacancyPreviewBinding

class InfoViewAdapter(
    private val clickListener: OnClickListener,
) :
    RecyclerView.Adapter<InfoViewAdapter.ViewHolder>() {
    private var infos = mutableListOf<Offer>()

    fun setList(list: List<Offer>) {
        this.infos.clear()
        this.infos.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            InfoCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding = binding,
            clickListener = clickListener,
        )
    }

    override fun getItemCount(): Int = infos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(infos[position])
    }

    class ViewHolder(
        private val binding: InfoCardsBinding,
        private val clickListener: OnClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(info: Offer) {
            binding.text.text = info.title

            val drawable = getIconById(info.id)
            if (drawable != null) {
                binding.icon.setImageResource(drawable)
            } else {
                binding.iconRound.visibility = View.INVISIBLE
            }
            binding.btnGreen.text = if (info.button != null) {
                info.button!!.text
            } else ""

            binding.infoCard.setOnClickListener { clickListener.onClick(info) }
        }

        private fun getIconById(id: String?): Int? {
            return when (id) {
                "near_vacancies" -> R.drawable.ic_location
                "level_up_resume" -> R.drawable.star
                "temporary_job" -> R.drawable.note
                else -> null
            }
        }
    }

    fun interface OnClickListener {
        fun onClick(offer: Offer)
    }
}