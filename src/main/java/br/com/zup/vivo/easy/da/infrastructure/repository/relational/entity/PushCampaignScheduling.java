package br.com.zup.vivo.easy.da.infrastructure.repository.relational.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PushCampaignScheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "push_campaign_id", referencedColumnName = "id")
    private PushCampaign pushCampaign;

    @Column(name = "scheduler_id", nullable = false)
    private Long schedulerId;

    @Column(name = "scheduled_date", nullable = false)
    private LocalDateTime scheduledDate;

    @Column(name = "deleted_on")
    private LocalDateTime deletedOn;

    public PushCampaignScheduling delete() {
        this.deletedOn = LocalDateTime.now();
        return this;
    }
}
