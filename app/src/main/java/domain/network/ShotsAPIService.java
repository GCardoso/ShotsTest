package domain.network;

import java.util.List;

import domain.entities.Shot;
import retrofit2.http.GET;

/**
 * Created by guilhermecardoso on 11/12/17.
 */

public interface ShotsAPIService {
    String SERVICE_ENDPOINT = "https://api.dribbble.com/v1/";
    String SERVICE_TOKEN = "ba02f781fdeb260f92ffa4a1a198bfd63d1e0353b327c3656ad7496a8f122ef8";

    @GET("shots/")
    io.reactivex.Observable<List<Shot>> listShots();

}
