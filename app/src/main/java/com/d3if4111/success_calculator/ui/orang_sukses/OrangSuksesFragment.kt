package com.d3if4111.success_calculator.ui.orang_sukses

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.d3if4111.success_calculator.R
import com.d3if4111.success_calculator.databinding.FragmentOrangSuksesBinding
import com.d3if4111.success_calculator.model.OrangSukses
import com.d3if4111.success_calculator.network.OrangSuksesApiService
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrangSuksesFragment : Fragment() {

    private lateinit var binding: FragmentOrangSuksesBinding
    private val viewModel: OrangSuksesViewModel by lazy {
        ViewModelProvider(this).get(OrangSuksesViewModel::class.java)
    }
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: OrangSuksesAdapter
    lateinit var orangSuksesList: List<OrangSukses>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOrangSuksesBinding.inflate(layoutInflater,
            container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerview
        recyclerAdapter = OrangSuksesAdapter(requireContext())
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 1)


        orangSuksesList = ArrayList<OrangSukses>()
        val apiInterface = OrangSuksesApiService.create().getOrangSukses()

        apiInterface.enqueue(object : Callback<List<OrangSukses>> {
            override fun onResponse(
                call: Call<List<OrangSukses>>,
                response: Response<List<OrangSukses>>
            ) {
                if(response.body()?.isEmpty() == true) { // if data null
                    binding.recyclerview.visibility = View.GONE
                    binding.emptyView.visibility = View.VISIBLE

                    binding.progressBar.visibility = View.GONE
                } else { // data not null
                    binding.emptyView.visibility = View.GONE

                    orangSuksesList = response.body()!!
                    Log.d("TAG", "Response = $orangSuksesList")
                    recyclerAdapter.setOrangSuksesList(requireContext(), orangSuksesList)
                    binding.progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<OrangSukses>>, t: Throwable) {
                val snackbar = Snackbar.make(view, getString(R.string.koneksi_error),
                    Snackbar.LENGTH_LONG).setAction("OK", null)
                val snackbarView = snackbar.view
                val textView =
                    snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                textView.setTextColor(Color.BLACK)
                snackbar.show()
            }
        })
        viewModel.scheduleUpdater(requireActivity().application)
    }
}