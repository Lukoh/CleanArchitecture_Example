package com.goforer.clean_architecture.domain.sort.comparator;

import com.goforer.clean_architecture.repository.model.data.Repository;

import java.util.Comparator;

public class RepositoryComparator implements Comparator<Repository> {
    public RepositoryComparator() {
    }

    @Override
    public int compare(Repository repos1, Repository repos2) {
        return repos2.stargazers_count() - repos1.stargazers_count();
    }
}
