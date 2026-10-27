package com.example.myshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myshop.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    private val args: CheckoutFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // Menampilkan nama produk
        binding.txtProductName.text = args.productName

        // Membuka halaman Address ketika kolom alamat diklik
        binding.edtAddress.setOnClickListener {
            findNavController().navigate(
                R.id.addressFragment
            )
        }

        // Menerima alamat yang dipilih dari AddressFragment
        findNavController().currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<String>("address")
            ?.observe(viewLifecycleOwner) { result ->
                binding.edtAddress.setText(result)
            }

        // Aksi tombol Selesai
        binding.btnDone.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
