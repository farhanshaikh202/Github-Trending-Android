package com.farhanapps.githubtrending.ui.viewmodel;

import androidx.lifecycle.Observer;

import com.farhanapps.githubtrending.data.model.RepoModel;
import com.farhanapps.githubtrending.utils.ResourceState;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

@RunWith(JUnit4.class)
public class ReposViewModelTest {

    RepoModel sampleModel = new RepoModel(
            "Test",
            "",
            "",
            "",
            "",
            "",
            "",
            new ArrayList()

    );
    private ReposViewModel viewModel;
    private Observer<? super ResourceState<ArrayList<RepoModel>>> observer;

    @Before
    public void setup() {
        viewModel = new ReposViewModel();
    }

    @Test
    public void testRepoSelect() {
        viewModel.selectOrRemoveRepo(sampleModel);
        assert viewModel.getSelectedRepos().size() > 0;
    }

    @Test
    public void testUnselect() {
        viewModel.selectOrRemoveRepo(sampleModel);
        viewModel.selectOrRemoveRepo(sampleModel);
        assert viewModel.getSelectedRepos().size() == 0;
    }
}