package br.com.zup.vivo.easy.da.infrastructure.repository.relational;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.vivo.easy.da.infrastructure.repository.relational.entity.PushCampaign;

@Repository
public interface PushCampaignRepository extends PagingAndSortingRepository<PushCampaign, UUID> {

    Optional<PushCampaign> findByIdAndDeletedOnIsNull(UUID id);

    PushCampaign findAllByAction(String action);

}
