package com.appimage.utils

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.content.withStyledAttributes
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.FragmentManager
import com.appimage.basetheme.R as basethemeR

private val defaultAnimAttrs = intArrayOf(
    basethemeR.attr.defaultTransitionEnter,
    basethemeR.attr.defaultTransitionExit,
    basethemeR.attr.defaultTransitionPopEnter,
    basethemeR.attr.defaultTransitionPopExit,
)

@SuppressWarnings("ResourceType", "MagicNumber")
fun FragmentManager.safelyAddFragment(
    context: Context,
    @IdRes containerViewId: Int,
    fragment: Fragment,
    addToBackStack: Boolean,
    clearBackStack: Boolean,
    animationAttrs: IntArray? = null,
    args: Bundle? = null
) {
    if (clearBackStack) {
        repeat(this.backStackEntryCount) {
            this.popBackStack()
        }
    }

    args?.let {
        fragment.arguments = it
    }

    commit(allowStateLoss = true) {
        setReorderingAllowed(true)
        context.withStyledAttributes(
            resourceId = basethemeR.style.Theme_MyTheme,
            attrs = defaultAnimAttrs
        ) {
            setCustomAnimations(
                animationAttrs?.get(0) ?: getResourceId(0, 0),
                animationAttrs?.get(1) ?: getResourceId(1, 0),
                animationAttrs?.get(2) ?: getResourceId(2, 0),
                animationAttrs?.get(3) ?: getResourceId(3, 0),
            )
        }
        fragments.lastOrNull()?.let { hide(it) }
        replace(containerViewId, fragment, fragment::class.java.canonicalName)
        if (addToBackStack) {
            addToBackStack(fragment::class.java.canonicalName)
        }
    }
}