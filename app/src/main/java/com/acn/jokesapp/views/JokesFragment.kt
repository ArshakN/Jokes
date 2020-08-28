package com.acn.jokesapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.acn.jokesapp.viewmodels.JokesViewModel
import com.acn.jokesapp.R
import com.acn.jokesapp.adapters.JokesAdapter
import com.acn.jokesapp.data.model.JokeItem
import kotlinx.android.synthetic.main.fragment_jokes.*

class JokesFragment : Fragment(R.layout.fragment_jokes) {
    private val jokesViewModel by navGraphViewModels<JokesViewModel>(R.id.nav_graph)
    private lateinit var jokesAdapter: JokesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnCLick()
        initRecyclerView()
        observe()
    }

    private fun initRecyclerView() {
        jokesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            jokesAdapter = JokesAdapter()
            adapter = jokesAdapter
        }
    }

    private fun observe() {
        jokesViewModel.jokes.observe(viewLifecycleOwner,
            Observer {
                if (it.joke != null) {
                    jokesAdapter.submitList(it.joke as List<JokeItem>)
                }
            })
        jokesViewModel.isError.observe(viewLifecycleOwner,
            Observer {
                errorMessage.visibility = if (it) View.VISIBLE else View.GONE
                jokesRecyclerView.visibility = if (it) View.GONE else View.VISIBLE
            })
    }

    private fun setOnCLick() {
        btnReload.setOnClickListener {
            countEditText.text.toString().run {
                if (this.isNotEmpty()) {
                    jokesViewModel.getJokes(this.toInt())
                }
            }
        }
    }
}