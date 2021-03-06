package br.com.castgroup.app.model.repository;

import java.util.List;

import br.com.castgroup.app.model.entity.Repo;
import br.com.castgroup.app.model.entity.User;
import io.reactivex.Observable;

/**
 * Repository interface da API GitHub Status.
 * <p>
 * Created by falvojr on 1/13/17.
 */
public interface GitHubRepository {
    Observable<User> getUser(String credential);
    Observable<List<Repo>> getRepos(String credential);
}
