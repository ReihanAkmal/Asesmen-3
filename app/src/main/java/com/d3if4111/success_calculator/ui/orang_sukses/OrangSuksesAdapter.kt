package com.d3if4111.success_calculator.ui.orang_sukses

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.d3if4111.success_calculator.PicassoClient
import com.d3if4111.success_calculator.R
import com.d3if4111.success_calculator.model.OrangSukses

class OrangSuksesAdapter(var context: Context): RecyclerView.Adapter<OrangSuksesAdapter.MyViewHolder>() {

    var orangSuksesList: List<OrangSukses> = listOf()
    var orangSuksesListFiltered: List<OrangSukses> = listOf()

    fun setOrangSuksesList(context: Context, orangSuksesList: List<OrangSukses>) {
        this.context = context
        if (orangSuksesList == null) {
            this.orangSuksesList = orangSuksesList
            this.orangSuksesListFiltered = orangSuksesList
            notifyItemChanged(0, orangSuksesListFiltered.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return this@OrangSuksesAdapter.orangSuksesList.size
                }

                override fun getNewListSize(): Int {
                    return orangSuksesList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return this@OrangSuksesAdapter.orangSuksesList.get(oldItemPosition)
                        .name === orangSuksesList[newItemPosition].name
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    val newName: OrangSukses = this@OrangSuksesAdapter.orangSuksesList.get(oldItemPosition)
                    val oldName: OrangSukses = orangSuksesList[newItemPosition]
                    return newName.name === oldName.name
                }
            })
            this.orangSuksesList = orangSuksesList
            this.orangSuksesListFiltered = orangSuksesList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_orang_sukses, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrangSuksesAdapter.MyViewHolder, position: Int) {
        holder.name!!.text = orangSuksesListFiltered.get(position).name

        val orangSukses: OrangSukses = orangSuksesListFiltered.get(position)

        val images: String = orangSukses.image
        val name: String = orangSukses.name
        val company: String = orangSukses.company

        // Bind
        holder.image!!.setImageURI((Uri.parse(images)))
        holder.name!!.text = name
        holder.company!!.text = company

        PicassoClient.downloadImage(context, orangSuksesListFiltered.get(position).image, holder.image)
    }

    interface ItemClickListener {
        fun onItemClick(pos: Int)
    }

    inner class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!),
        View.OnClickListener {

        private lateinit var itemClickListener: ItemClickListener

        var image: ImageView? = null
        var name: TextView? = null
        var company: TextView? = null

        init {
            image = itemView!!.findViewById<View>(R.id.thumbnail) as ImageView
            name = itemView.findViewById<View>(R.id.name) as TextView
            company = itemView.findViewById<View>(R.id.company) as TextView
        }

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }
    }

    override fun getItemCount(): Int {
        return orangSuksesListFiltered.size
    }
}