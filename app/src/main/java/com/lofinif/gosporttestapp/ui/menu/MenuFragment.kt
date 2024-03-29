package com.lofinif.gosporttestapp.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.lofinif.gosporttestapp.data.menu.dto.Category
import com.lofinif.gosporttestapp.databinding.FragmentMenuBinding
import com.lofinif.gosporttestapp.ui.BaseMapper
import com.lofinif.gosporttestapp.ui.menu.adapter.AdsPostersAdapter
import com.lofinif.gosporttestapp.ui.menu.adapter.CategoryAdapter
import com.lofinif.gosporttestapp.ui.menu.adapter.FoodDetailsAdapter
import com.lofinif.gosporttestapp.ui.menu.adapter.OnCategoryClickListener
import com.lofinif.gosporttestapp.ui.menu.model.CategoryModel
import com.lofinif.gosporttestapp.ui.menu.state.MenuCategoryScreenState
import com.lofinif.gosporttestapp.ui.menu.state.MenuFoodScreenState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MenuFragment : Fragment(), OnCategoryClickListener {

    private val viewModel: MenuViewModel by viewModels()
    private var binding: FragmentMenuBinding? = null

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var adsPostersAdapter: AdsPostersAdapter
    private lateinit var foodDetailsAdapter: FoodDetailsAdapter

    @Inject
    lateinit var mapper: BaseMapper<Category, CategoryModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        adsPostersAdapter = AdsPostersAdapter(requireContext())
        foodDetailsAdapter = FoodDetailsAdapter(requireContext())
        categoryAdapter = CategoryAdapter(this)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.categoryContent?.categoriesRecyclerView?.adapter = categoryAdapter
        binding?.adsPostersRecyclerView?.adapter = adsPostersAdapter
        binding?.menuFoodContent?.foodRecyclerView?.adapter = foodDetailsAdapter

        binding?.categoryContent?.categoriesRecyclerView?.itemAnimator = null
        binding?.adsPostersRecyclerView?.itemAnimator = null
        binding?.menuFoodContent?.foodRecyclerView?.itemAnimator = null

        observeViewModel()

        viewModel.fetchCategory()

        binding?.menuError?.tryAgain?.setOnClickListener {
            viewModel.tryAgain()
        }
        binding?.categoryError?.tryAgain?.setOnClickListener {
            viewModel.fetchCategory()
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.screenStateFoodLiveData.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is MenuFoodScreenState.Loaded -> {
                        foodDetailsAdapter.submitList(state.model)
                        binding?.menuFoodContent?.foodRecyclerView?.runWhenReady {
                            binding?.menuFoodContent?.root?.isVisible = true
                            binding?.menuError?.root?.isVisible = false
                            binding?.menuLoading?.root?.isVisible = false
                        }
                    }

                    is MenuFoodScreenState.Error -> {
                        binding?.menuError?.root?.isVisible = true
                        binding?.menuFoodContent?.root?.visibility = View.INVISIBLE
                        binding?.menuLoading?.root?.isVisible = false
                    }

                    is MenuFoodScreenState.Loading -> {
                        binding?.menuError?.root?.isVisible = false
                        binding?.menuFoodContent?.root?.visibility = View.INVISIBLE
                        binding?.menuLoading?.root?.isVisible = true
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.postersFlow.collectLatest {
                adsPostersAdapter.submitList(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.screenStateCategoryLiveData.observe(viewLifecycleOwner) { state ->
                binding?.categoryLoading?.root?.isVisible = state is MenuCategoryScreenState.Loading
                binding?.categoryError?.root?.isVisible = state is MenuCategoryScreenState.Error

                if (state is MenuCategoryScreenState.Loaded) {
                    categoryAdapter.submitList(state.model)
                    binding?.menuFoodContent?.root?.isVisible = true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCategoryClick(category: CategoryModel) {
        viewModel.fetchFood(category)
    }
}

private fun RecyclerView.runWhenReady(action: () -> Unit) {
    val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            action()
            viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
    }
    viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
}
