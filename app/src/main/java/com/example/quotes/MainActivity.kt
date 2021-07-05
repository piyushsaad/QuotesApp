package com.example.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotes.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    
    lateinit var aDapter:Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = QuoteDatabase.getDatabase(applicationContext).quoteDao()
        val repository = QuoteRepository(dao)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        aDapter= Adapter()

        recycle.adapter=aDapter
        recycle.layoutManager=LinearLayoutManager(this)
        //recycle.setHasFixedSize(true)
        mainViewModel.getQuotes()?.observe(this, Observer {
            if(it!=null)
              aDapter.setQuotesList(it)
        })




        binding.button.setOnClickListener {
            val quote =Quote(0,binding.editText.text.toString(),binding.edit2.text.toString())
            mainViewModel.insertQuote(quote)
        }
    }
}