package com.dandycat.naviexam

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.dandycat.naviexam.databinding.ActivityMainBinding
import com.dandycat.naviexam.fragment.profile.ProfileFragmentDirections
import com.dandycat.naviexam.util.DynamicLinkUtil
import com.dandycat.naviexam.util.Logger
import com.dandycat.naviexam.viewmodel.MainActivityViewModel
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private val vm : MainActivityViewModel by viewModels() // Activity용 ViewModel을 선언해준다

    private lateinit var binding : ActivityMainBinding
    private var currentFragment = ""
    @Inject lateinit var mDynamicLinkUtil: DynamicLinkUtil


    private val visibleBtmNav : (CharSequence) -> Int = { destination ->
        when(destination){
            getString(R.string.label_main),
            getString(R.string.label_profile) -> View.VISIBLE
            else -> View.GONE
        }
    }

    private val checkedBtmNavMenu : (CharSequence) -> Unit = { destination ->
        when(destination){
            getString(R.string.label_main) -> {
                binding.btmNav.menu.findItem(R.id.home).isChecked = true
            }
            getString(R.string.label_profile) -> {
                binding.btmNav.menu.findItem(R.id.profile).isChecked = true
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d("onCreate")

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        intent?.let {
            decodeDynamicLink(it)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener {_,destination,_ ->
            destination.label?.let { label ->
                Logger.d("currentDestination Label : $label")
                currentFragment = label.toString()
                val viewStatus = visibleBtmNav(label)
                binding.btmNav.visibility = viewStatus
                if(viewStatus==View.VISIBLE){
                    checkedBtmNavMenu(label)
                }
            }
        }

        binding.btmNav.setOnItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.home->{
                findNavController(R.id.nav_host).navigate(R.id.move_main)
                true
            }
            R.id.profile->{
                findNavController(R.id.nav_host).navigate(R.id.move_profile)
                true
            }
            else -> false
        }
    }

    private fun createDeepLinkIntent(uri : Uri) = Intent().apply {
        data = uri
    }

    //onNewIntent로 넘어오는 경우 해당 부분서 처리 해준다!!
    override fun onNewIntent(intent: Intent?) {
        Logger.e("onNewIntent")
        super.onNewIntent(intent)
        intent?.let {
            val uri = it.data
            uri?.let {
                Logger.d("인텐트 동작된다!! - uri : $uri")
                val convertUri = convertAppUriLink(uri.toString())
                findNavController(R.id.nav_host).navigate(convertUri)
            }
        }
    }

    private fun decodeDynamicLink(intent : Intent){
        mDynamicLinkUtil.decodeDynamicLink(intent){
            it?.let {
                Logger.d("다이나믹 링크 인텐트 동작 시킨다!! - uri : $it")
                //findNavController(R.id.nav_host).navigate(Uri.parse(it))
                val uri = convertAppUriLink(it)
                Logger.d("convertAppUri : $uri")
                if(isSplash()){ // 만약 스플래시 화면일 경우 해당 부분서 동작 시켜준다
                    vm.setAppLink(uri)
                }else{
                    findNavController(R.id.nav_host).navigate(uri)
                }
            }?: kotlin.run {
                Logger.d("제대로 풀리지 않았으니 일루 온다")
            }
        }
    }

    private val convertAppUriLink : (String) -> Uri = { link ->
        val appDeepLink = getString(R.string.app_prefix)
        val linkSplit = link.split("?")
        Uri.parse(appDeepLink+linkSplit[linkSplit.size-1])
    }

    private fun isSplash() = currentFragment.equals(getString(R.string.label_splash),true)
}