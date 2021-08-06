package com.example.daycoval_service.model


import com.google.gson.annotations.SerializedName

data class VersionGroupDetail(
    @SerializedName("level_learned_at")
    var levelLearnedAt: Int? = 0,
    @SerializedName("move_learn_method")
    var moveLearnMethod: MoveLearnMethod? = null,
    @SerializedName("version_group")
    var versionGroup: VersionGroup? = null
)