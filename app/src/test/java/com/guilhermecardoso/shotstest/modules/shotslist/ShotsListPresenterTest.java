package com.guilhermecardoso.shotstest.modules.shotslist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import app.modules.shotslist.ShotsListContract;
import app.modules.shotslist.ShotsListPresenter;
import domain.network.ShotsAPIService;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by guilhermecardoso on 11/16/17.
 */

public class ShotsListTest {

    private ShotsListPresenter presenter;
    @Mock private ShotsListContract.View view;
    @Mock private CompositeDisposable sCompositeDisposable;
    @Mock private ShotsAPIService service;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFetchPage() {
        presenter.fetchPage(1);

        Mockito.verify(view);
    }
}
