package com.guilhermecardoso.shotstest.modules.shotdetails;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Callable;

import app.modules.shotdetails.ShotDetailsContract;
import app.modules.shotdetails.ShotDetailsPresenter;
import domain.entities.Shot;
import domain.network.ShotsAPIService;
import io.reactivex.Scheduler;
import io.reactivex.Single;
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

public class ShotDetailsPresenterTest {

    private ShotDetailsPresenter presenter;
    @Mock
    private ShotDetailsContract.View mockedView;
    @Mock private ShotsAPIService mockedService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new ShotDetailsPresenter(mockedView, new CompositeDisposable(), mockedService);
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
    public void cleanUp() {
//        presenter = null;
//        RxAndroidPlugins.reset();
        Schedulers.shutdown();
    }

    @Test
    public void shouldPresentDetails() {
        long id = 1;
        mockServiceResponse();

        presenter.loadShotDetails(id);

        verify(mockedView).showLoading();
        verify(mockedView).showShotDetails(any(Shot.class));
        verify(mockedView).hideLoading();
    }

    @Test
    public void shouldPresentError() {
        long id = 1;
        mockServiceError();

        presenter.loadShotDetails(id);

        verify(mockedView).showLoading();
        verify(mockedView).showError();
        verify(mockedView).hideLoading();
    }

    private void mockServiceError() {
        when(mockedService.getShot(any(Long.class))).thenReturn(Single.<Shot>error(new NullPointerException()));
    }

    private void mockServiceResponse() {
        when(mockedService.getShot(any(Long.class))).thenReturn(Single.<Shot>just(new Shot()));
    }
}
