package com.bakangoa.annuaire.ecoles.infrastructure.repository;

import com.bakangoa.annuaire.ecoles.business.Ville;
import com.bakangoa.annuaire.ecoles.business.VilleSnapshot;
import com.bakangoa.annuaire.ecoles.infrastructure.dao.VilleEntity;
import com.bakangoa.annuaire.shared.Page;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PostgresVilleRepository {
    private final JpaVilleRepository repository;
    private final EntityManager entityManager;
    public PostgresVilleRepository(JpaVilleRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    public void save(Ville ville) {
        this.repository.save(VilleEntity.fromDomain(ville));
    }

    public Optional<Ville> findById(UUID id) {
        return this.repository.findByIdAndDeletedAtNull(id).map(VilleEntity::toDomain);
    }

    public Page<VilleSnapshot> search(
            String search,
            Integer page,
            Integer size
    ) {
        if (size == null || size == 0) return Page.of(List.of(), 0, page, 0);
        var offset = (page != null && page > 0) ? (page - 1) * size : 0;

        StringBuilder filterBuilder = new StringBuilder(
                "v.deletedAt = null"
        );
        Map<String, Object> params = new HashMap<>();

        if (search != null && !search.isBlank()) {
            filterBuilder.append(" AND LOWER(name) LIKE :pattern");
            params.put("pattern", "%" + search.toLowerCase() + "%");
        }

        var query = this.entityManager.createQuery(
                "SELECT v FROM VilleEntity v WHERE " + filterBuilder.toString(), VilleEntity.class);
        var totalQuery = this.entityManager.createQuery(
                "SELECT COUNT(v) FROM VilleEntity v WHERE " + filterBuilder.toString(), Integer.class
        );

        params.forEach(query::setParameter);
        params.forEach(totalQuery::setParameter);

        query.setFirstResult(offset);
        query.setMaxResults(size);

        var result = query.getResultStream().map((v) -> new VilleSnapshot(v.id, v.name, v.createdAt.toInstant(), v.updatedAt.toInstant())).toList();
        var total = totalQuery.getSingleResult();

        return Page.of(
                result,
                total,
                page == null ? 1 : page,
                size
        );
    }
}
