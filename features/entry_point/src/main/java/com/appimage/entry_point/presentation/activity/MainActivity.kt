package com.appimage.entry_point.presentation.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.appimage.arch.di.ViewModelFactory
import com.appimage.core.BaseApp
import com.appimage.core.di.qualifiers.MainActivityContainer
import com.appimage.entry_point.databinding.ActivityMainBinding
import com.appimage.entry_point.di.MainActivityComponent
import com.appimage.entry_point.presentation.viewmodel.MainActivityViewModel
import com.appimage.utils.navigation.safelyAddFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @JvmField
    @field:[Inject MainActivityContainer]
    var mainFragmentContainer: Int = 0

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels { viewModelFactory }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        MainActivityComponent.init((newBase.applicationContext as BaseApp).getApplicationProvider())
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.showMainScreen {
            beginTransaction(it)
        }
    }

    private fun beginTransaction(fragment: Fragment) {
        supportFragmentManager.safelyAddFragment(
            context = this,
            mainFragmentContainer,
            fragment,
            addToBackStack = false,
            clearBackStack = false
        )
    }
}
