package com.chernybro.wb52.presentation.hero_list_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chernybro.wb52.databinding.ItemHeroBinding
import com.chernybro.wb52.domain.models.HeroItem
import com.squareup.picasso.Picasso

interface HeroClickHandler {
    fun onItemClick(item: HeroItem)
}

class HeroesListAdapter : PagingDataAdapter<HeroItem ,HeroesListAdapter.HeroListViewHolder>(DIFF_CALLBACK) {
    private var heroClickHandler: HeroClickHandler? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroListViewHolder {
        val binding = ItemHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroListViewHolder(binding, heroClickHandler = heroClickHandler)
    }

    override fun onBindViewHolder(holder: HeroListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(item = it) }
    }

    fun attachClickHandler(heroClickHandler: HeroClickHandler) {
        this.heroClickHandler = heroClickHandler
    }

    class HeroListViewHolder(
        private val itemHeroBinding: ItemHeroBinding,
        private val heroClickHandler: HeroClickHandler?,
    ) : RecyclerView.ViewHolder(itemHeroBinding.root) {

        fun bind(item: HeroItem) {
            itemHeroBinding.apply {
                tvHeroName.text = item.name
                Picasso.get().load(item.image).into(ivHeroImage)
                this.root.setOnClickListener{ heroClickHandler?.onItemClick(item) }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HeroItem>() {
            override fun areItemsTheSame(oldItem: HeroItem, newItem: HeroItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: HeroItem, newItem: HeroItem): Boolean =
                oldItem.image == newItem.image
                        && oldItem.name == newItem.name

        }
    }
}