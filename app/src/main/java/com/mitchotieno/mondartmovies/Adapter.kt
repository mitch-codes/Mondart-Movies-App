package com.mitchotieno.mondartmovies

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso


class Adapter(private val context: Context, private val list: List<Item>) : RecyclerView.Adapter<Adapter.ViewHolder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view, list, context)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        Glide.with(holder.imageView.context).load(item.img).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).into(holder.imageView)
        //Picasso.get().load(item.img).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).into(holder.imageView)
        //holder.imageView.setImageResource(R.drawable.ic_launcher_background)
        holder.textView1.setText(item.mTitle)
        holder.textView.setText(item.mDesc)

        /**
         * holder.itemView.setOnClickListener {
            Toast.makeText(context,"you clicked" + position.toString(), Toast.LENGTH_LONG).show()

        }
        **/

    }
    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View, list: List<Item>, context: Context) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView1: TextView = itemView.findViewById(R.id.textView2)
        val textView: TextView = itemView.findViewById(R.id.textView)

        init {
                itemView.setOnClickListener {
                    val position = absoluteAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val intent = Intent(context, MovieActivity::class.java)
                        var myMovTitle = list[position].mTitle
                        var myMovImg = list[position].img
                        var myMovDesc = list[position].mSum
                        intent.putExtra("myMovTitle", myMovTitle)
                        intent.putExtra("myMovImg", myMovImg)
                        intent.putExtra("myMovDesc", myMovDesc)
                        context.startActivity(intent)
                    }
                }
        }


        //val movHold: ConstraintLayout = itemView.findViewById(R.id.movHold)



    }
}

