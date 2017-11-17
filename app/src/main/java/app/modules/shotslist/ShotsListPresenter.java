package app.modules.shotslist;

import java.util.List;

import domain.entities.Shot;
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

    public ShotsListPresenter(ShotsListContract.View view, CompositeDisposable compositeDisposable, ShotsAPIService service) {
        this.view = view;
        sCompositeDisposable = compositeDisposable;
        this.service = service;
    }

    @Override
    public void onShotClicked(long id) {
        view.showShotDetails(id);
    }

    @Override
    public void fetchPage(int page) {
        sCompositeDisposable.add(service.listShots(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Shot>>() {
                    @Override
                    public void accept(List<Shot> shots) throws Exception {
                        view.showShots(shots);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        view.showError();
                    }
                }));
    }

    @Override
    public void dispose() {
        this.sCompositeDisposable.dispose();
    }
}
