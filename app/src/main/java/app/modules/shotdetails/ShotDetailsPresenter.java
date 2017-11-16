package app.modules.shotdetails;

import domain.entities.Shot;
import domain.network.ServiceFactory;
import domain.network.ShotsAPIService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by guilhermecardoso on 11/14/17.
 */

public class ShotDetailsPresenter implements ShotDetailsContract.Actions {

    private static final String TAG = "ShotDetailsPresenter";
    private ShotDetailsContract.View view;
    private CompositeDisposable compositeDisposable;
    private ShotsAPIService service;

    public ShotDetailsPresenter(ShotDetailsContract.View view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
        service = ServiceFactory.createService(ShotsAPIService.class);
    }


    @Override
    public void loadShotDetails(long id) {
        view.showLoading();

        compositeDisposable.add(service.getShot(id)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Shot>() {
            @Override
            public void accept(Shot shot) throws Exception {
                view.hideLoading();
                view.showShotDetails(shot);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                view.hideLoading();
                view.showError();
            }
        }));
    }
}
