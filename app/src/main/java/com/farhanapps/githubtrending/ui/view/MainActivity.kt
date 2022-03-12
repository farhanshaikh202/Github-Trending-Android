package com.farhanapps.githubtrending.ui.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.farhanapps.githubtrending.R
import com.farhanapps.githubtrending.data.Constants
import com.farhanapps.githubtrending.databinding.ActivityMainBinding
import com.farhanapps.githubtrending.ui.viewmodel.ReposViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ReposViewModel
    private var actionMode: ActionMode? = null
    lateinit var binding: ActivityMainBinding

    val callback = object : ActionMode.Callback {

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            actionMode = mode
            menuInflater.inflate(R.menu.contextual_action_bar, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            return when (item?.itemId) {
                R.id.share -> {
                    // Handle share icon press
                    true
                }
                else -> false
            }
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            viewModel.clearSelection()
            viewModel.refreshData()
            actionMode = null
        }
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            showCAB()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)

        viewModel = ViewModelProvider(this)[ReposViewModel::class.java]

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(receiver, IntentFilter(Constants.EVENT_SELECTION))

        showCAB()
    }

    private fun showCAB() {
        val size = viewModel.getSelectedRepos().size
        if (size > 0) {
            if (actionMode == null) startSupportActionMode(callback)
            actionMode?.title = "$size selected"
        } else {
            actionMode?.finish()
        }
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
        super.onDestroy()
    }
}