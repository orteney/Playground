package com.orteney.playground.ui.expandablelist

class ExpandableModel(
    val id: String,
    val title: String,
    val subTitle: String,
    val items: List<ParcelModel> = emptyList(),
    var isExpanded: Boolean = false
)