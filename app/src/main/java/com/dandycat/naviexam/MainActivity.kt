package com.dandycat.naviexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.dandycat.naviexam.databinding.ActivityMainBinding
import com.dandycat.naviexam.fragment.profile.OtherProfileFragmentDirections
import com.dandycat.naviexam.util.DynamicLinkUtil
import com.dandycat.naviexam.util.Logger
import com.dandycat.naviexam.viewmodel.MainActivityViewModel
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding : ActivityMainBinding
    private var currentFragment = ""
    @Inject lateinit var mDynamicLinkUtil: DynamicLinkUtil

    private val vm : MainActivityViewModel by viewModels() // Activity용 ViewModel을 선언해준다

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
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

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
                findNavController(R.id.nav_host).navigate(R.id.action_fragment_main_to_fragment_profile)
                true
            }
            else -> false
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Logger.d("데이터 들어왔다!!")
        mDynamicLinkUtil.decodeDynamicLink(intent){
            it?.let {
                Logger.d("userName : $it")
                if(!it.equals(vm.getLoginName(),true) &&
                        !currentFragment.equals(getString(R.string.label_profile),true)){
                    val args = OtherProfileFragmentDirections.moveProfileOther(it)
                    findNavController(R.id.nav_host).navigate(args)
                }
            }?: kotlin.run {
                Logger.e("정상데이터 아니다!!")
            }
        }
    }
}