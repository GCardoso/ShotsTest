package app.modules.shotdetails;

import domain.entities.Shot;

/**
 * Created by guilhermecardoso on 11/14/17.
 */

public interface ShotDetailsContract {

    interface View {
        void showShotDetails(Shot shot);
        void showLoading();
        void hideLoading();
        void showError();
    }

    interface Actions {
        void loadShotDetails(long id);

    }
}
