package com.example.coco.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coco.DB.entity.InterestCoinEntity
import com.example.coco.R
import com.example.coco.databinding.FragmentCoinListBinding
import com.example.coco.view.adapter.CoinListRVAdapter
import timber.log.Timber


class CoinListFragment : Fragment() {

	private var _binding : FragmentCoinListBinding? = null
	private val binding get() = _binding!!
	private val selectedList = ArrayList<InterestCoinEntity>()
	private val unselectedList = ArrayList<InterestCoinEntity>()

	private val viewModel : MainViewModel by activityViewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment

		_binding = FragmentCoinListBinding.inflate(inflater, container, false)
		val view = binding.root

		return view
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel.getAllInterestCoinData()
		viewModel.selectedCoinList.observe(viewLifecycleOwner, Observer {

			selectedList.clear()
			unselectedList.clear()

			for(item in it){
				if(item.selected){
					selectedList.add(item)
				}else{
					unselectedList.add(item)
				}
			}

			setSelectedListRV()

		})
	}

	private fun setSelectedListRV(){
		val selectedRVAdatper = CoinListRVAdapter(requireContext(), selectedList)
		binding.selectedCoinRV.adapter = selectedRVAdatper
		binding.selectedCoinRV.layoutManager = LinearLayoutManager(requireContext())

		selectedRVAdatper.itemClick = object : CoinListRVAdapter.ItemClick{
			override fun onClick(view: View, position: Int) {
				viewModel.updateInterestCoinData(selectedList[position])
			}
		}

		val unselectedRVAdatper = CoinListRVAdapter(requireContext(), unselectedList)
		binding.unSelectedCoinRV.adapter = unselectedRVAdatper
		binding.unSelectedCoinRV.layoutManager = LinearLayoutManager(requireContext())

		unselectedRVAdatper.itemClick = object : CoinListRVAdapter.ItemClick{
			override fun onClick(view: View, position: Int) {
				viewModel.updateInterestCoinData(unselectedList[position])
			}
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}

}