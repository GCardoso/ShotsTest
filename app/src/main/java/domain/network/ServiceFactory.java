package domain.network;

import domain.network.interceptor.AuthenticationInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by guilhermecardoso on 11/12/17.
 */

public class ServiceFactory {


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(ShotsAPIService.SERVICE_ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

//    public static <S> S createService(Class<S> serviceClass) {
//        return createService(serviceClass, null);
//    }

    public static <S> S createService(
            Class<S> serviceClass) {
//        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(ShotsAPIService.SERVICE_TOKEN);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
            }

            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            retrofit = builder.build();
//        }

        return retrofit.create(serviceClass);
    }

}
