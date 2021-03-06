package br.com.castgroup.app.infraestructure.storage.service;

import br.com.castgroup.app.model.entity.Status;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Interface Retrofit da API GitHub Status.
 * <p>
 * Created by falvojr on 1/9/17.
 */
public interface GitHubStatusService {

    //TODO (04) Retrofit: GitHub Status API

    String BASE_URL = "https://status.github.com/api/";

    @GET("last-message.json")
    Observable<Status> getLastStatus();
}
