package com.weatherapp.feature.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.weatherapp.domain.model.Day
import com.weatherapp.feature.details.databinding.ItemHoursBinding

class DailyAdapter : ListAdapter<Day, DailyAdapter.HoursViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_hours, parent, false)
        val binding = ItemHoursBinding.bind(view)
        return HoursViewHolder(binding)    }

    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) =
        holder.bind(getItem(position))

    class HoursViewHolder(
        private val binding: ItemHoursBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(day: Day) {
            with(binding) {
                hour.text = day.datetime
                temp.text = day.temp.toString()
                conditions.text = day.conditions
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Day>() {
        override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean {
            return oldItem.datetime == newItem.datetime
        }

        override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean {
            return oldItem == newItem
        }
    }
}