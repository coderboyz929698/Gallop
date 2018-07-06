package io.github.umangjpatel.gallop.repositories;

public class CatalogRepository {

    private static CatalogRepository sCatalogRepository = new CatalogRepository();

    private CatalogRepository() {

    }

    public static CatalogRepository getInstance() {
        return sCatalogRepository;
    }
}
