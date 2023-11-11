package com.appimage.utils.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

class Navigator(
    private val sourceFragment: Fragment,
) {

    fun parentNavigate(
        @IdRes
        containerViewId: Int,
        fragment: Fragment,
        singleTop: Boolean = false,
        animationAttrs: IntArray? = null
    ) {
        val fragmentManager = sourceFragment.parentFragmentManager
        if (singleTop && fragmentManager.fragments.last().javaClass.canonicalName == fragment.javaClass.canonicalName) {
            return
        }
        fragmentManager.safelyAddFragment(
            context = sourceFragment.requireContext(),
            containerViewId = containerViewId,
            fragment = fragment,
            addToBackStack = true,
            clearBackStack = false,
            animationAttrs = animationAttrs
        )
    }

    fun childNavigate(
        @IdRes
        containerViewId: Int,
        fragment: Fragment,
        singleTop: Boolean = false,
        animationAttrs: IntArray? = null,
        args: Bundle? = null
    ) {
        val fragmentManager = sourceFragment.childFragmentManager
        if (singleTop && fragmentManager.fragments.last().javaClass.canonicalName == fragment.javaClass.canonicalName) {
            return
        }
        fragmentManager.safelyAddFragment(
            context = sourceFragment.requireContext(),
            containerViewId = containerViewId,
            fragment = fragment,
            addToBackStack = true,
            clearBackStack = false,
            animationAttrs = animationAttrs,
            args = args
        )
    }

    fun navigate(
        @IdRes
        containerViewId: Int,
        fragment: Fragment,
        animationAttrs: IntArray? = null,
        args: Bundle? = null
    ) {
        val activity = sourceFragment.activity ?: return
        activity.supportFragmentManager.safelyAddFragment(
            context = sourceFragment.requireContext(),
            containerViewId = containerViewId,
            fragment = fragment,
            addToBackStack = true,
            clearBackStack = false,
            animationAttrs = animationAttrs,
            args = args
        )
    }

    fun navigateAndClearBackStack(
        @IdRes
        containerViewId: Int,
        fragment: Fragment,
        animationAttrs: IntArray? = null,
        clearBackStackUpTo: String? = null
    ) {
        val activity = sourceFragment.activity ?: return
        if (clearBackStackUpTo != null) {
            activity.supportFragmentManager.popBackStack(clearBackStackUpTo, 0)
        } else {
            repeat(activity.supportFragmentManager.backStackEntryCount) {
                activity.supportFragmentManager.popBackStack()
            }
        }
        activity.supportFragmentManager.safelyAddFragment(
            context = sourceFragment.requireContext(),
            containerViewId = containerViewId,
            fragment = fragment,
            addToBackStack = false,
            clearBackStack = true,
            animationAttrs = animationAttrs
        )
    }

    fun parentNavigateAndClearBackStack(
        @IdRes
        containerViewId: Int,
        fragment: Fragment,
        singleTop: Boolean = false,
        animationAttrs: IntArray? = null
    ) {
        val fragmentManager = sourceFragment.parentFragmentManager

        if (fragmentManager.fragments.isNotEmpty() &&
            singleTop &&
            fragmentManager.fragments.last().javaClass.canonicalName == fragment.javaClass.canonicalName
        ) {
            return
        }

        fragmentManager.safelyAddFragment(
            context = sourceFragment.requireContext(),
            containerViewId = containerViewId,
            fragment = fragment,
            addToBackStack = false,
            clearBackStack = true,
            animationAttrs = animationAttrs
        )
    }

    fun navigateBack() {
        sourceFragment.onBackPressed()
    }

    fun childPopBackStack() {
        sourceFragment.childFragmentManager.popBackStack()
    }

    fun childInfoBackStack(): Int {
        return sourceFragment.childFragmentManager.backStackEntryCount
    }
}