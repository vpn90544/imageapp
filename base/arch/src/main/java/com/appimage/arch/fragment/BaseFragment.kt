package com.appimage.arch.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.appimage.arch.di.ViewModelFactory
import com.appimage.arch.uistate.BaseUiState
import com.appimage.arch.viewmodel.BaseViewModel
import com.appimage.core.BaseApp
import com.appimage.core.di.providers.ApplicationProvider
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

abstract class BaseFragment <
        UiState : BaseUiState,
        ViewModel : BaseViewModel<UiState>,
        ViewBinding : androidx.viewbinding.ViewBinding
> : Fragment(), DefaultLifecycleObserver {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: ViewModel
        private set

    private var _viewBinding: ViewBinding? = null
    val viewBinding get() = requireNotNull(_viewBinding) { "Binding not initialized" }

    private var _fragment: BaseFragment<UiState,ViewModel, ViewBinding>? = null
    protected val fragment: BaseFragment<UiState,ViewModel, ViewBinding>
        get() = _fragment!!

//    protected val context: Context
//        get() = fragment.requireContext()
//
//    protected val resources: Resources
//        get() = fragment.resources

    protected val lifecycleScope: LifecycleCoroutineScope
        get() = fragment.viewLifecycleOwner.lifecycleScope

    private var _lifecycleObserver: DefaultLifecycleObserver? = null
    private val lifecycleObserver: DefaultLifecycleObserver
        get() = _lifecycleObserver!!

    protected abstract fun getViewModelClass(): Class<ViewModel>

    private fun getApplicationProvider(context: Context): ApplicationProvider {
        return (context.applicationContext as BaseApp).getApplicationProvider()
    }

    fun attach(fragment: BaseFragment<UiState, ViewModel, ViewBinding>) {
        _fragment = fragment
        _lifecycleObserver = object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) = this@BaseFragment.onCreate(owner)
            override fun onStart(owner: LifecycleOwner) = this@BaseFragment.onStart(owner)
            override fun onResume(owner: LifecycleOwner) = this@BaseFragment.onResume(owner)
            override fun onPause(owner: LifecycleOwner) = this@BaseFragment.onPause(owner)
            override fun onStop(owner: LifecycleOwner) = this@BaseFragment.onStop(owner)
            override fun onDestroy(owner: LifecycleOwner) = this@BaseFragment.onDestroy(owner)
        }
        fragment.viewLifecycleOwner.lifecycle.addObserver(lifecycleObserver)
    }

    @CallSuper
    override fun onAttach(context: Context) {
        injectFeatureComponent(getApplicationProvider(context))
        viewModel = ViewModelProvider(this, viewModelFactory)[getViewModelClass()]
        viewModel.initialize()
        super.onAttach(context)
    }

    protected abstract fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _viewBinding = createViewBinding(inflater, container)
        attach(this)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavigator(this)
    }

    fun detach() {
        fragment.viewLifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        _lifecycleObserver = null
        _fragment = null
    }

    @CallSuper
    override fun onDestroyView() {
        detach()
        _viewBinding = null
        super.onDestroyView()
    }

    @CallSuper
    override fun onCreate(owner: LifecycleOwner) {
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collectLatest(::handleUiState)
        }
    }

    protected open fun handleUiState(uiState: UiState) {
    }

    protected abstract fun injectFeatureComponent(applicationProvider: ApplicationProvider)
}