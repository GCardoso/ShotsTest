package com.guilhermecardoso.shotstest.modules.shotslist;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import app.modules.shotslist.ShotsListContract;
import app.modules.shotslist.ShotsListPresenter;
import domain.entities.Shot;
import domain.network.ShotsAPIService;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by guilhermecardoso on 11/16/17.
 */

public class ShotsListPresenterTest {

    private ShotsListPresenter presenter;
    @Mock
    private ShotsListContract.View mockedView;
    private CompositeDisposable mockedCompositeDisposable;
    @Mock private ShotsAPIService mockedService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new ShotsListPresenter(mockedView, new CompositeDisposable(), mockedService);
    }


    @Before
    public void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
            new Function<Callable<Scheduler>, Scheduler>() {
               @Override
               public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                   return Schedulers.newThread();
               }
           }
        );
        RxJavaPlugins.setNewThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @After
    public void tearDown() {
        RxAndroidPlugins.reset();
        presenter = null;
    }

    @Test
    public void shouldLoadShotDetails() {
        //Given a shot ID
        int shotID = 1;

        //When we try to load it
        presenter.onShotClicked(shotID);
        //We should ask the mockedView to show it
        verify(mockedView).showShotDetails(any(Long.class));
    }

    @Test
    public void shouldFetchShots() {
        //Given a page and a successful completable
        int page = 1;
        mockServiceResponse();

        //When we ask the presenter to load it
        presenter.fetchPage(page);

        //The view should be asked to show it
        verify(mockedView).showShots(any(List.class));
    }

    private void mockServiceResponse() {
        when(mockedService.listShots(any(Integer.class))).thenReturn(Observable.<List<Shot>>just(new ArrayList<Shot>()));
    }

}
