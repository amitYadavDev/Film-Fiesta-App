package com.filmfiesta.themovieshow.ui.about.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.filmfiesta.themovieshow.R
import com.filmfiesta.themovieshow.databinding.FragmentAboutBinding
import com.filmfiesta.themovieshow.ui.about.adapter.AboutApplicationAdapter
import com.filmfiesta.themovieshow.ui.about.adapter.AboutAuthorAdapter
import com.filmfiesta.themovieshow.ui.about.viewmodel.AboutViewModel
import com.filmfiesta.themovieshow.utils.showSuccessToastyIcon
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AboutViewModel by viewModels()
    private var authorAdapter: AboutAuthorAdapter? = null
    private var applicationAdapter: AboutApplicationAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar()
        setAuthorData()
        setApplicationData()
    }

    private fun setToolbar() {
        binding.toolbarAbout.tvToolbar.text = getString(R.string.icAbout)
    }

    private fun setAuthorData() {
        authorAdapter = AboutAuthorAdapter { position ->
            when (position) {
                0 -> showToastyBasedOnType(LINKEDIN)
                1 -> showToastyBasedOnType(GITHUB)
                2 -> showToastyBasedOnType(EMAIL)
            }
        }

        binding.rvAuthorAbout.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = authorAdapter
        }

        authorAdapter?.submitList(viewModel.getAboutAuthor(requireContext()))
    }

    private fun setApplicationData() {
        applicationAdapter = AboutApplicationAdapter { position ->
                when (position) {
                    0 -> {}
                    1, 2 -> showToastyBasedOnType(GOOGLE_PLAY)
                    3 -> showToastyBasedOnType(EMAIL)
                }
            }

        binding.rvApplicationAbout.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = applicationAdapter
        }

        applicationAdapter?.submitList(viewModel.getAboutApplication(requireContext()))
    }

    private fun showToastyBasedOnType(type: String) {
        when (type) {
            LINKEDIN -> {
                val urlLink = "https://linkedin.com/"
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlLink))
                    startActivity(
                        Intent.createChooser(
                            intent,
                            "Open with:"
                        )
                    )
                } catch (e: Exception) {
                    requireContext().showSuccessToastyIcon("Please install browser app")
                }
            }

            GOOGLE_PLAY -> {
                val versionLink = "yet to publish"
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(versionLink))
                    startActivity(
                        Intent.createChooser(
                            intent,
                            "Open with:"
                        )
                    )
                } catch (e: Exception) {
                    requireContext().showSuccessToastyIcon("Please install browser app")
                }
            }

            GITHUB -> {
                val githubLink = "https://github.com/amitDev"
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubLink))
                    startActivity(
                        Intent.createChooser(
                            intent,
                            "Open with:"
                        )
                    )
                } catch (e: Exception) {
                    requireContext().showSuccessToastyIcon("Please install browser app")
                }
            }

            EMAIL -> {
                val email = "amit.the@gmail.com"
                try {
                    val intentEmail = Intent(
                        Intent.ACTION_SENDTO,
                        Uri.fromParts("mailto", email, null)
                    ).apply {
                        putExtra(Intent.EXTRA_EMAIL, email)
                        putExtra(Intent.EXTRA_SUBJECT, "")
                        putExtra(Intent.EXTRA_TEXT, "")
                    }
                    startActivity(
                        Intent.createChooser(
                            intentEmail,
                            "Send email"
                        )
                    )
                } catch (e: Exception) {
                    requireContext().showSuccessToastyIcon("Please install email app")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        applicationAdapter = null
    }

    companion object {
        const val LINKEDIN = "linkedIn"
        const val GOOGLE_PLAY = "google_play"
        const val GITHUB = "github"
        const val EMAIL = "email"
    }
}