package com.goforer.github_clean_architecture_mvp.domain.sort.comparator;

import com.goforer.github_clean_architecture_mvp.presentation.model.data.Repository;

import java.util.Comparator;

public class RepositoryComparator implements Comparator<Repository> {
    public RepositoryComparator() {
    }

    @Override
    public int compare(Repository repos1, Repository repos2) {
        return repos2.getStarCount() - repos1.getStarCount();
    }
}
