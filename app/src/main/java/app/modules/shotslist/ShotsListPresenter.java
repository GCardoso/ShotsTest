package app.modules.shotslist;

import android.util.Log;

import java.util.List;

import domain.entities.Shot;
import domain.network.ServiceFactory;
import domain.network.ShotsAPIService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by guilhermecardoso on 11/13/17.
 */

public class ShotsListPresenter implements ShotsListContract.Actions {

    private static final String TAG = "ShotsListPresenter";
    private ShotsListContract.View view;
    private CompositeDisposable sCompositeDisposable;
    private ShotsAPIService service;

    public ShotsListPresenter(ShotsListContract.View view) {
        this.view = view;
        sCompositeDisposable = new CompositeDisposable();
        service = ServiceFactory.createService(ShotsAPIService.class);
    }

    @Override
    public void loadShots() {
        sCompositeDisposable.add(service.listShots()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Shot>>() {
            @Override
            public void accept(List<Shot> shots) throws Exception {
                for(Shot actualShot : shots) {
                    Log.i(TAG, actualShot.getTitle());
                }
            }
        }));
    }

}