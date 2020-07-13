package com.example.gunmunity.model

import com.example.gunmunity.model.entity.RankingInfo

data class PxInfo (
    var list_total_count : Int = 0,
    var row : List<RankingInfo>?
)