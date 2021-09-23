package br.com.zup.vivo.easy.da.infrastructure.repository.relational.entity;

import static org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PushCampaign {

    @Id
    @GeneratedValue
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;

    private String title;

    private String description;

    private String segment;

    private String action;

    @Convert(converter = LocalDateConverter.class)
    private LocalDate initialDate;

    @Convert(converter = LocalDateConverter.class)
    private LocalDate endDate;

    private LocalTime releaseHour;

    private String period;

    private Integer frequency;

   /* @SuppressWarnings("JpaAttributeTypeInspection")
    @Convert(converter = HashMapConverter.class)
    private Map<String, String> parameters;*/

    /*@Enumerated(EnumType.STRING)
    @Column(columnDefinition = "text[]")
    @Type(type = "br.com.zup.vivo.easy.campaign.infrastructure.repository.CustomStringArrayType")
    private List<EasyPrimeTag> primeTags;*/

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    private LocalDateTime deletedOn;

    private Boolean active = true;

    public static boolean isAvailableValidation(PushCampaign pushCampaign) {
        return (LocalDate.now().isEqual(pushCampaign.getInitialDate()) && LocalTime.now().isAfter(pushCampaign.getReleaseHour()) &&
                (LocalDate.now().isEqual(pushCampaign.getEndDate()) && LocalTime.now().isBefore(pushCampaign.getReleaseHour()))) ||
                (LocalDate.now().isEqual(pushCampaign.getInitialDate()) && LocalTime.now().isAfter(pushCampaign.getReleaseHour()) &&
                        (LocalDate.now().isBefore(pushCampaign.getEndDate()))) || (LocalDate.now().isAfter(pushCampaign.getInitialDate()) &&
                LocalDate.now().isBefore(pushCampaign.getEndDate()));
    }

    public static boolean isScheduledValidation(PushCampaign pushCampaign) {
        return (LocalDate.now().isEqual(pushCampaign.getInitialDate()) && LocalTime.now().isBefore(pushCampaign.getReleaseHour())) ||
                LocalDate.now().isBefore(pushCampaign.getInitialDate());
    }

    public static boolean isExpiredValidation(PushCampaign pushCampaign) {
        return (LocalDate.now().isEqual(pushCampaign.getEndDate()) && LocalTime.now().isAfter(pushCampaign.getReleaseHour())) ||
                LocalDate.now().isAfter(pushCampaign.getEndDate());
    }
}
