package com.example.projectrickandmorty.ui.episodes

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectrickandmorty.R
import com.example.projectrickandmorty.ui.characters.CharactersAdapter
import com.example.projectrickandmorty.ui.characters.CharactersViewModel
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_characters.*
import kotlinx.android.synthetic.main.fragment_episodes.*

class EpisodesFragment : Fragment() {

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: EpisodesAdapter
    lateinit var dialog: AlertDialog
    var mIsLoading = false
    var mIsLastPage = false
    var mCurrentPage = 1
    var pageSize = 20
    private lateinit var episodesViewModel: EpisodesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        dialog = SpotsDialog.Builder().setCancelable(true).setContext(context).build()
        dialog.show()



        episodesViewModel =
                ViewModelProviders.of(this).get(EpisodesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_episodes, container, false)

        return root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerEpisodesList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        recyclerEpisodesList.layoutManager = layoutManager

        adapter = EpisodesAdapter(requireActivity().baseContext, mutableListOf())

        recyclerEpisodesList.adapter = adapter

        episodesViewModel.episodesList.observe(viewLifecycleOwner, Observer {

            Log.d("TAG", "I'M OBSEVING")
            adapter.addItems(it)

            adapter.notifyDataSetChanged()

            mIsLoading = false

            dialog.dismiss()
        })

        episodesViewModel.pagesCount.observe(viewLifecycleOwner, Observer {
            mIsLastPage = mCurrentPage == episodesViewModel.pagesCount.value

        })

        recyclerEpisodesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy);
                // number of visible items
                var visibleItemCount = layoutManager.getChildCount();
                // number of items in layout
                var totalItemCount = layoutManager.getItemCount();
                // the position of first visible item
                var firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                var isNotLoadingAndNotLastPage = !mIsLoading && !mIsLastPage;
                // flag if number of visible items is at the last
                var isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount;
                // validate non negative values
                var isValidFirstItem = firstVisibleItemPosition >= 0;
                // validate total items are more than possible visible items
                var totalIsMoreThanVisible = totalItemCount >= pageSize;
                // flag to know whether to load more
                var shouldLoadMore = isValidFirstItem && isAtLastItem && totalIsMoreThanVisible && isNotLoadingAndNotLastPage
                Log.d("TAG", "visibleItemCount = " + visibleItemCount + " totalItemCount= " + totalItemCount + " firstVisibleItemPosition " + firstVisibleItemPosition + "  ")

                if (shouldLoadMore) {
                    Log.d("TAG", "LOADING")
                    loadNew()


                }
            }
        }
        )



    }

    private fun loadNew() {

        mIsLoading = true
        mCurrentPage = mCurrentPage + 1
        episodesViewModel.load(mCurrentPage)

    }




}