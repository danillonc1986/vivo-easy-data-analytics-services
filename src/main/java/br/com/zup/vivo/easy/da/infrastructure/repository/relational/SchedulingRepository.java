package br.com.zup.vivo.easy.da.infrastructure.repository.relational;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.vivo.easy.da.infrastructure.repository.relational.entity.PushCampaignScheduling;


@Repository
public interface SchedulingRepository extends CrudRepository<PushCampaignScheduling, Long> {

    List<PushCampaignScheduling> findAllByPushCampaignIdAndDeletedOnIsNull(UUID id);

}
