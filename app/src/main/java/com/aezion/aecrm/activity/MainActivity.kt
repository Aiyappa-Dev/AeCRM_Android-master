package com.aezion.aecrm.activity

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.View
import android.view.animation.AnimationUtils
import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aezion.aecrm.R
import com.aezion.aecrm.adapter.AppointmentAdapter
import com.aezion.aecrm.fragment.Accounts
import com.aezion.aecrm.fragment.DashBoard
import com.aezion.aecrm.fragment.Proposals
import com.aezion.aecrm.fragment.Settings
import com.aezion.aecrm.fragment.TestUI.ARC_ToolBar
import com.aezion.aecrm.fragment.TestUI.Diagonal_ToolBar
import com.aezion.aecrm.fragment.TestUI.Movie_ToolBar
import com.aezion.aecrm.fragment.bottomNav.ContactsFragment
import com.aezion.aecrm.fragment.bottomNav.DealsFragment
import com.aezion.aecrm.fragment.bottomNav.ProposalsFragment
import com.aezion.aecrm.fragment.bottomNav.ProspectsFragment
import com.aezion.aecrm.model.AppointModel
import com.github.fernandodev.easyratingdialog.library.EasyRatingDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var navHeader: View
    private var activityTitles: Array<String>? = null
    // flag to load home fragment when user presses back key
    private var easyRatingDialog: EasyRatingDialog? = null
    private var mHandler: Handler? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var sheetBehavior: BottomSheetBehavior<*>? = null
    var mFragment: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mHandler = Handler()
        // Navigation view header
        navHeader = nav_view!!.getHeaderView(0)
        // load toolbar titles from string resources
        activityTitles = resources.getStringArray(R.array.nav_item_activity_titles)
        // initializing navigation menu
        setUpNavigationView()
        // load nav menu header data
        loadNavHeader()
//i wiil check my side , if possible send me Screen shot for my clear idea
        if (savedInstanceState == null) {
            navItemIndex = 0
            CURRENT_TAG = TAG_DASHBOARD
            loadHomeFragment("")
        }
        val navigation = findViewById<View>(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        toolbar!!.title = "Prospects"
        mFragment = ProspectsFragment()
        loadFragment(mFragment as ProspectsFragment)
        easyRatingDialog = EasyRatingDialog(this)
        fillPop()
    }


    private fun fillPop() {
        sheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        sheetBehavior!!.isHideable = false //Important to add
        /**
         * bottom sheet state change listener
         * we are changing button text when sheet changed state
         * */
        sheetBehavior!!.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {

                drawer_layout!!.closeDrawers()

                when (newState) {
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {

                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        lin_id.visibility = View.GONE
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        lin_id.visibility = View.VISIBLE
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        if (sheetBehavior!!.state != BottomSheetBehavior.STATE_EXPANDED) {
                            lin_id.visibility = View.GONE
                        } else {
                            lin_id.visibility = View.VISIBLE
                        }
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }

            override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {

            }
        })

        //appointment_list = findViewById<View>(R.id.appointment_list) as RecyclerView
        appointment_list!!.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        appointment_list!!.layoutManager = layoutManager
        appointment_list!!.itemAnimator = DefaultItemAnimator()
        data = ArrayList()
        for (i in timeArr.indices) {
            data!!.add(
                AppointModel(
                    timeArr[i],
                    dateArr[i],
                    monthArr[i],
                    headerArr[i],
                    descArr[i]
                )
            )
        }
        appointment_list!!.adapter = AppointmentAdapter(this, data!!)

        appoint_close.setOnClickListener {
            lin_id.visibility = View.VISIBLE
            sheetBehavior!!.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }

        lin_id.setOnClickListener {
            lin_id.visibility = View.GONE
            sheetBehavior!!.setState(BottomSheetBehavior.STATE_EXPANDED)

        }
    }


    fun addAnimationToList(recyclerView: RecyclerView, activity: Activity) {

        recyclerView.setHasFixedSize(true)
        val layoutMan = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutMan
        recyclerView.itemAnimator = DefaultItemAnimator()

        val resId = R.anim.layout_animation_fall_down
        val animation = AnimationUtils.loadLayoutAnimation(activity, resId)
        recyclerView.layoutAnimation = animation
    }

    private val homeFragment: Fragment
        get() {
            when (navItemIndex) {
                0 -> {
                    return DashBoard()
                }
                1 -> {
                    return Accounts()
                }
                2 -> {
                    return Settings()
                }
                3 -> {
                    return Proposals()
                }
                4 -> {
                    return ARC_ToolBar()
                }
                5 -> {
                    return Diagonal_ToolBar()
                }
                6 -> {
                    return Movie_ToolBar()
                }
                else -> return DashBoard()
            }
        }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        drawer_layout!!.closeDrawers()

        lin_id.visibility = View.VISIBLE
        sheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED


        when (item.itemId) {
            R.id.navigation_prospects -> {
                toolbar!!.title = "Prospects"
                mFragment = ProspectsFragment()
                loadFragment(mFragment as ProspectsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_contacts -> {
                toolbar!!.title = "Contacts"
                mFragment = ContactsFragment()
                loadFragment(mFragment as ContactsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_deals -> {
                toolbar!!.title = "Deals"
                mFragment = DealsFragment()
                loadFragment(mFragment as DealsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_proposals -> {
                toolbar!!.title = "Proposals"
                mFragment = ProposalsFragment()
                loadFragment(mFragment as ProposalsFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private fun loadNavHeader() {
        // name, website
        navHeader.name!!.text = getString(R.string.anna_farmer)
        navHeader.website!!.text = getString(R.string.it_director)
        // loading header background image
        /*   Glide.with(this).load(urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);*/
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private fun loadHomeFragment(mClick: String) {
        // selecting appropriate nav menu item
        selectNavMenu()
        // set toolbar title
        //setToolbarTitle()
        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (supportFragmentManager.findFragmentByTag(CURRENT_TAG) != null) {
            //drawer!!.closeDrawers()
            drawer_layout!!.closeDrawers()
            return
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        if (mClick == "click") {
            val mPendingRunnable = Runnable {
                // update the main content by replacing fragments
                val fragment = homeFragment
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.setCustomAnimations(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG)
                fragmentTransaction.commitAllowingStateLoss()
            }
            // If mPendingRunnable is not null, then add to the message queue
            mHandler!!.post(mPendingRunnable)
        }
        //Closing drawer on item click
        drawer_layout!!.closeDrawers()
        // refresh toolbar menu
        invalidateOptionsMenu()
    }


    /*private fun setToolbarTitle() {
        supportActionBar!!.title = activityTitles!![navItemIndex]
    }*/

    private fun selectNavMenu() {
        nav_view!!.menu.getItem(navItemIndex).isChecked = true
    }

    private fun setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        nav_view!!.setNavigationItemSelectedListener { menuItem ->
            // This method will trigger on item Click of navigation menu
            //Check to see which item was being clicked and perform appropriate action

            drawer_layout!!.closeDrawers()

            lin_id.visibility = View.VISIBLE
            sheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED

            when (menuItem.itemId) {
                //Replacing the main content with ContentFragment Which is our Inbox View;
                R.id.nav_dashboard -> {
                    navItemIndex = 0
                    CURRENT_TAG = TAG_DASHBOARD
                }
                R.id.nav_accounts -> {
                    navItemIndex = 1
                    CURRENT_TAG = TAG_ACCOUNTS

                }
                R.id.nav_settings -> {
                    navItemIndex = 2
                    CURRENT_TAG = TAG_SETTINGS
                }

                R.id.nav_proposals -> {
                    navItemIndex = 3
                    CURRENT_TAG = TAG_PROPOSALS
                }
                R.id.nav_arc -> {
                    navItemIndex = 4
                    CURRENT_TAG = TAG_ARC
                }
                R.id.nav_diagonal -> {
                    navItemIndex = 5
                    CURRENT_TAG = TAG_DIAGONAL
                }
                R.id.nav_movie -> {
                    navItemIndex = 6
                    CURRENT_TAG = TAG_MOVIE
                }

                else -> navItemIndex = 0
            }

            //Checking if the item is in checked state or not, if not make it in checked state
            menuItem.isChecked = !menuItem.isChecked
            menuItem.isChecked = true

            loadHomeFragment("click")

            true
        }


        val actionBarDrawerToggle = object : ActionBarDrawerToggle( this,
            drawer_layout, toolbar, R.string.openDrawer, R.string.closeDrawer ) {
            override fun onDrawerOpened(drawerView: View) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView)

                lin_id.visibility = View.VISIBLE
                sheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        //Setting the actionbarToggle to drawer layout
        drawer_layout!!.setDrawerListener(actionBarDrawerToggle)

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState()
    }

    override fun onBackPressed() {
        if (drawer_layout!!.isDrawerOpen(GravityCompat.START)) {
            drawer_layout!!.closeDrawers()
            return
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (navItemIndex != 0) {
            navItemIndex = 0
            CURRENT_TAG = TAG_DASHBOARD
            loadHomeFragment("")
            return
        }
        finish()
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        // when fragment is notifications, load the menu created for notifications

        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val searchViewItem = menu.findItem(R.id.app_bar_search)


        val searchView = MenuItemCompat.getActionView(searchViewItem) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                /*   if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }*/
                return false

            }

            override fun onQueryTextChange(newText: String): Boolean {
                when {
                    mFragment.toString().contains("ContactsFragment") -> {
                        (mFragment as ContactsFragment).mAdapter!!.filter.filter(newText)
                    }
                    mFragment.toString().contains("DealsFragment") -> {
                        //(mFragment as DealsFragment).mAdapter!!.filter.filter(newText)
                    }
                    mFragment.toString().contains("ProposalsFragment") -> {
                        (mFragment as ProposalsFragment).mAdapter!!.filter.filter(newText)
                    }
                    mFragment.toString().contains("ProspectsFragment") -> {
                        (mFragment as ProspectsFragment).mAdapter!!.filter.filter(newText)
                    }
                    //adapter.getFilter().filter(newText)
                }
                //adapter.getFilter().filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


    companion object {
        // index to identify current nav menu item
        var navItemIndex = 0

        // tags used to attach the fragments
        private const val TAG_DASHBOARD = "Dashboard"
        private const val TAG_ACCOUNTS = "Accounts"
        private const val TAG_SETTINGS = "Settings"
        private const val TAG_PROPOSALS = "Proposals"
        private const val TAG_ARC = "ARC"
        private const val TAG_DIAGONAL = "Diagonal"
        private const val TAG_MOVIE = "Movie"
        var CURRENT_TAG = TAG_DASHBOARD

        private var data: ArrayList<AppointModel>? = null
        internal var timeArr = arrayOf("14:20", "08:00", "15:45", "23:15")
        internal var dateArr = arrayOf("20", "21", "01", "30")
        internal var monthArr = arrayOf("OCT", "OCT", "NOV", "NOV")
        internal var headerArr =
            arrayOf("ECI Patient portal discovery", "ECIP Proposed", "Cases module", "Follow up and final review")
        internal var descArr = arrayOf("First time", "Proposal review", "Follow-up Session", "Dealbreak")
    }

    override fun onStart() {
        super.onStart()
        easyRatingDialog!!.onStart()
    }

    override fun onResume() {
        super.onResume()
        easyRatingDialog!!.showIfNeeded()
    }

}
