package com.example.daycoval_service.data.model


import com.google.gson.annotations.SerializedName

data class Move(
    @SerializedName("move")
    var move: MoveX? = null,
    @SerializedName("version_group_details")
    var versionGroupDetails: List<VersionGroupDetail>? = null
)