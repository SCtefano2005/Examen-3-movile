package com.villalva.stefano.laboratoriocalificado03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.villalva.stefano.laboratoriocalificado03.databinding.ItemProfesorBinding

class ProfesorAdapter(
    private val profesores: List<Profesor>,
    private val onClick: (Profesor) -> Unit,
    private val onLongClick: (Profesor) -> Unit
) : RecyclerView.Adapter<ProfesorAdapter.ProfesorViewHolder>() {

    inner class ProfesorViewHolder(private val binding: ItemProfesorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(profesor: Profesor) {
            binding.textViewName.text = profesor.name
            binding.textViewSurname.text = profesor.last_name

            Glide.with(binding.imageView.context)
                .load(profesor.image_url)
                .into(binding.imageView)

            binding.root.setOnClickListener { onClick(profesor) }
            binding.root.setOnLongClickListener {
                onLongClick(profesor)
                true
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfesorViewHolder {
        val binding = ItemProfesorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProfesorViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ProfesorViewHolder, position: Int) {
        holder.bind(profesores[position])
    }
    override fun getItemCount(): Int = profesores.size
}