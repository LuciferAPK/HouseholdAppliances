package com.example.householdappliances.ui.screen.search.extension

import com.example.householdappliances.R
import com.example.householdappliances.ui.screen.search.SearchFragment
import com.example.householdappliances.utils.CommonUtils


fun SearchFragment.hideIcSearch() {
    if (binding.toolbar.icSearch.width > resources.getDimensionPixelOffset(R.dimen.dp5))
    CommonUtils.animationSetWidth(
        resources.getDimensionPixelOffset(R.dimen.dp24),
        0,
        200,
        binding.toolbar.icSearch,
        callBackStartAnimation = {},
        callBackEndAnimation = {})
}

fun SearchFragment.hideIcClearText() {
    CommonUtils.animationSetWidth(
        resources.getDimensionPixelOffset(R.dimen.dp26),
        0,
        200,
        binding.toolbar.icClearText,
        callBackStartAnimation = {},
        callBackEndAnimation = {
            binding.toolbar.icClearText.alpha = 0F
        })
}

fun SearchFragment.showIcSearch() {
    CommonUtils.animationSetWidth(
        1,
        resources.getDimensionPixelOffset(R.dimen.dp24),
        200,
        binding.toolbar.icSearch,
        callBackStartAnimation = {},
        callBackEndAnimation = {})
}

fun SearchFragment.showIcClearText() {
    if (binding.toolbar.icClearText.width < resources.getDimensionPixelSize(R.dimen.dp10)) {
        CommonUtils.animationSetWidth(
            1,
            resources.getDimensionPixelOffset(R.dimen.dp26),
            200,
            binding.toolbar.icClearText,
            callBackStartAnimation = {
                binding.toolbar.icClearText.alpha = 1F
            },
            callBackEndAnimation = {})
    }
}