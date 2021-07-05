package com.example.quotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todolist.view.*

class Adapter:RecyclerView.Adapter<Adapter.TodoViewHolder>() {
    inner class TodoViewHolder(item: View):RecyclerView.ViewHolder(item)
    var todo= mutableListOf<Quote>()
    fun setQuotesList(todo: List<Quote>) {
        this.todo=todo.toMutableList()
        notifyDataSetChanged()
    }



    //RecyclerView calls this method whenever it needs to create a new ViewHolder.
    // The method creates and initializes the ViewHolder and its associated View,
    // but does not fill in the view's contents—the ViewHolder has not yet been bound to specific data.

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.todolist,parent,false)
        return TodoViewHolder(view)
    } // false cause we dont want to attach view to root which is parent



    // RecyclerView calls this method to associate a ViewHolder with data.
    // The method fetches the appropriate data and uses the data to fill in the view holder's layout.
    // For example, if the RecyclerView dislays a list of names, the method might find
    // the appropriate name in the list and fill in the view holder's TextView widget.

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemView.apply {
          textView.text=todo[position].id.toString()
          textView4.text=todo[position].text
          textView2.text="~By "+todo[position].author
        }

    }
    //RecyclerView calls this method to get the size of the data set.
    // For example, in an address book app, this might be the total number of addresses.
    // RecyclerView uses this to determine when there are no more items that can be displayed.
    override fun getItemCount(): Int {
        return todo.size
    }

}