package com.guilhermecardoso.shotstest.modules.shotslist;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Callable;

import app.modules.shotslist.ShotsListContract;
import app.modules.shotslist.ShotsListPresenter;
import domain.network.ShotsAPIService;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by guilhermecardoso on 11/16/17.
 */

public class ShotsListPresenterTest {

    private ShotsListPresenter presenter;
    @Mock private ShotsListContract.View view;
//    @Mock private CompositeDisposable sCompositeDisposable;
    @Mock private ShotsAPIService service;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new ShotsListPresenter(view);

    }


    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
            new Function<Callable<Scheduler>, Scheduler>() {
               @Override
               public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                   return Schedulers.trampoline();
               }
           }
        );
    }

    @After
    public void tearDown() {
        RxAndroidPlugins.reset();
    }

    @Test
    public void shouldLoadShot() {
        //Given a page
        int page = 1;

        //When we try to fetch
        presenter.onShotClicked(page);
        //We should ask the view to show it
        Mockito.verify(view).showShotDetails(Mockito.any(Long.class));
    }
}
