package ru.iyshcherbakov.userguider.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.iyshcherbakov.userguider.databinding.FragmentMainBinding

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = UserAdapter { user ->
            // Обработка клика (переход на детали)
        }
        mBinding.rcView.adapter = adapter
        mBinding.rcView.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.users.collect { users ->
                adapter.submitList(users)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.errors.collect { error ->
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loadUsers()
    }
}