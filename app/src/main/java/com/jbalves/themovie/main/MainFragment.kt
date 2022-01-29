package com.jbalves.themovie.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jbalves.themovie.BuildConfig
import com.jbalves.themovie.R
import com.jbalves.themovie.main.remoto.BaseResponse
import com.jbalves.themovie.main.remoto.MovieResponse
import com.jbalves.themovie.main.remoto.ServiceProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment() {

    private lateinit var movies: RecyclerView

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movies = view.findViewById(R.id.movies)

        getMovies()
    }

    private fun getMovies() {
        ServiceProvider.service.getMovies(BuildConfig.tmdbToken)
            .enqueue(object:Callback<BaseResponse<List<MovieResponse>>>{
                override fun onResponse(
                    call: Call<BaseResponse<List<MovieResponse>>>,
                    response: Response<BaseResponse<List<MovieResponse>>>
                ) {
                    if(response.isSuccessful){
                        val data = response.body()?.results
                        movies.adapter = MoviesAdapter(data ?: emptyList())
                    }
                }

                override fun onFailure(
                    call: Call<BaseResponse<List<MovieResponse>>>,
                    t: Throwable
                ) {

                }

            })
    }
}