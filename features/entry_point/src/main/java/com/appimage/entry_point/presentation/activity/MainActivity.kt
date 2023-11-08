package com.appimage.entry_point.presentation.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.appimage.arch.di.ViewModelFactory
import com.appimage.core.BaseApp
import com.appimage.entry_point.R
import com.appimage.entry_point.databinding.ActivityMainBinding
import com.appimage.entry_point.di.MainActivityComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    //private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    //@Inject
    //lateinit var mainScreenMediator: MainScreenMediator
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var binding: ActivityMainBinding
    private val viewModel: com.appimage.entry_point.presentation.viewmodel.MainActivityViewModel by viewModels { viewModelFactory }

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



        viewModel.getNExtNAv({
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container,it)
                .commit()

        })

    }
}
