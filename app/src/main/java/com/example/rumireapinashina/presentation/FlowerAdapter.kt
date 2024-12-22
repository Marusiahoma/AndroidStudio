package com.example.rumireapinashina

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Flowers
import kotlin.collections.MutableList

class FlowerAdapter(
    private val flowers: MutableList<Flowers>
) : RecyclerView.Adapter<FlowerAdapter.FlowersViewHolder>() {

    class FlowersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvFlowerTitle: TextView = itemView.findViewById(R.id.tvFlowerTitle)
        private val tvFlowerDescription: TextView = itemView.findViewById(R.id.tvFlowerDescription)

        fun bind(flowers: Flowers) {
            tvFlowerTitle.text = flowers.title
            tvFlowerDescription.text = flowers.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowersViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_flowers, parent, false)
        return FlowersViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlowersViewHolder, position: Int) {
        val flowers = flowers[position]
        holder.bind(flowers)
    }

    override fun getItemCount(): Int = flowers.size

    fun updateFlowers(newFlowers: List<Flowers>) {
        flowers.clear()
        flowers.addAll(newFlowers)
        notifyDataSetChanged()
    }

    fun addFlower(flowers: Flowers) {
        this.flowers.add(flowers)
        notifyItemInserted(this.flowers.size - 1)
    }
}
