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
    private var repoList = MutableLiveData<ResourceState<ArrayList<RepoModel>>>()

    init {
        repoList = MutableLiveData<ResourceState<ArrayList<RepoModel>>>()
        loadTrendingRepos()
    }

    fun getRepoList(): LiveData<ResourceState<ArrayList<RepoModel>>> {
        return repoList
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
                            repoList.postValue(ResourceState.success(data = ArrayList(it)))
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