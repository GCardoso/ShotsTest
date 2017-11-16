package app.modules.shotslist;

import java.util.List;

import domain.entities.Shot;

/**
 * Created by guilhermecardoso on 11/13/17.
 */

public interface ShotsListContract {

    interface View {
        void showShots(List<Shot> shots);
        void showShotDetails(long shot);
        void showError();
    }

    interface Actions {
        void onShotClicked(long id);
        void fetchPage(int page);
    }
}
