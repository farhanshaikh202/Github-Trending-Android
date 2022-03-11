package com.farhanapps.githubtrending.ui.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.farhanapps.githubtrending.AppController
import com.farhanapps.githubtrending.data.api.API
import com.farhanapps.githubtrending.data.api.ApiCallback
import com.farhanapps.githubtrending.data.model.ApiResponseModel
import com.farhanapps.githubtrending.data.model.RepoModel
import com.farhanapps.githubtrending.utils.AppUtils
import com.farhanapps.githubtrending.utils.ResourceState

class ReposViewModel : ViewModel() {
    private lateinit var originalData: List<RepoModel>
    private var repoList = MutableLiveData<ResourceState<ArrayList<RepoModel>>>()
    private var selectedRepoList = ArrayList<RepoModel>()

    init {
        repoList = MutableLiveData<ResourceState<ArrayList<RepoModel>>>()
        try {
            loadTrendingRepos()
        } catch (e: Exception) {
        }
    }

    fun getRepoList(): LiveData<ResourceState<ArrayList<RepoModel>>> {
        return repoList
    }

    fun getSelectedRepos(): ArrayList<RepoModel> {
        return selectedRepoList
    }

    fun isSelected(repo: RepoModel): Boolean {
        return selectedRepoList.contains(repo)
    }

    fun selectOrRemoveRepo(repoModel: RepoModel) {
        val existingModel = selectedRepoList.find { it.repo == repoModel.repo }
        if (existingModel == null)
            selectedRepoList.add(repoModel)
        else selectedRepoList.remove(repoModel)
    }

    fun refreshData() {
        selectedRepoList.clear()
        repoList.postValue(ResourceState.success(data = ArrayList(originalData)))
    }

    fun loadTrendingRepos() {
        if (!AppUtils.isOnline(AppController.instance())) {
            repoList.postValue(ResourceState.error("No internet connection", null))
            return
        }

        repoList.postValue(ResourceState.loading(null))

        API.get().getTrendingRepos("daily").enqueue(
            object : ApiCallback<ApiResponseModel<List<RepoModel>>> {
                override fun onResponse(data: ApiResponseModel<List<RepoModel>>?) {
                    if (data == null) {
                        repoList.postValue(ResourceState.error("No data found", null))
                    }
                    data?.let { data ->
                        data.items?.let {
                            originalData = it
                            refreshData()
                        }
                    }
                }

                override fun onFail(message: String?) {
                    repoList.postValue(ResourceState.error(message.toString(), null))
                }

                override fun getView(): View? {
                    return null
                }
            }
        )
    }

}