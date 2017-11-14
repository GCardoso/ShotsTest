package app.modules.shotslist;

import java.util.List;

import domain.entities.Shot;

/**
 * Created by guilhermecardoso on 11/13/17.
 */

public interface ShotsListContract {

    interface View {
        void showShows(List<Shot> shots);
        void showLoading();
        void hideLoading();
    }

    interface Actions {
        void loadShots();
    }
}
