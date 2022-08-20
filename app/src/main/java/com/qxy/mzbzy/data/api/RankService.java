package com.qxy.mzbzy.data.api;

import com.qxy.mzbzy.data.bean.Rank;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RankService {
    @GET("discovery/ent/rank/item/moviesrankC")
    Call<Rank> getRankMovieC();

    @GET("discovery/ent/rank/item/moviesrankI")
    Call<Rank> getRankMovieI();
    @GET("discovery/ent/rank/item/TV")
    Call<Rank> getRankTV();
    @GET("discovery/ent/rank/item/Show")
    Call<Rank> getRankShow();
}
