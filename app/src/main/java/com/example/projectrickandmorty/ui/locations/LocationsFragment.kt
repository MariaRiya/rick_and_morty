package com.example.projectrickandmorty.ui.locations

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
import com.example.projectrickandmorty.ui.episodes.EpisodesAdapter
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_episodes.*
import kotlinx.android.synthetic.main.fragment_locations.*

class LocationsFragment : Fragment() {

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: LocationsAdapter
    lateinit var dialog: AlertDialog
    var mIsLoading = false
    var mIsLastPage = false
    var mCurrentPage = 1
    var pageSize = 20
    private lateinit var locationsViewModel: LocationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        dialog = SpotsDialog.Builder().setCancelable(true).setContext(context).build()
        dialog.show()

        locationsViewModel =
                ViewModelProviders.of(this).get(LocationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_locations, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerLocationsList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        recyclerLocationsList.layoutManager = layoutManager

        adapter = LocationsAdapter(requireActivity().baseContext, mutableListOf())

        recyclerLocationsList.adapter = adapter

        locationsViewModel.locationsList.observe(viewLifecycleOwner, Observer {

            Log.d("TAG", "I'M OBSEVING")
            adapter.addItems(it)

            adapter.notifyDataSetChanged()

            mIsLoading = false

            dialog.dismiss()
        })

        locationsViewModel.pagesCount.observe(viewLifecycleOwner, Observer {
            mIsLastPage = mCurrentPage == locationsViewModel.pagesCount.value

        })

        recyclerLocationsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

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
        locationsViewModel.load(mCurrentPage)

    }


}