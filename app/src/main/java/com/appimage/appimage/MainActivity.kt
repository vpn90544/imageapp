package com.appimage.appimage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.appimage.appimage.databinding.ActivityMainBinding
import com.appimage.appimage.di.MainActivityComponent
import com.appimage.arch.di.ViewModelFactory
import com.appimage.core.BaseApp
import com.appimage.mainscreen.presentation.MainScreenFragment
import javax.inject.Inject
import com.appimage.mainscreen_api.mediators.MainScreenMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    //private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    //@Inject
    //lateinit var mainScreenMediator: MainScreenMediator
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var binding:ActivityMainBinding
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
        //viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]

        val fr = MainScreenFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container,fr)
            .commit()

        viewModel.getNExtNAv()

    }
}
