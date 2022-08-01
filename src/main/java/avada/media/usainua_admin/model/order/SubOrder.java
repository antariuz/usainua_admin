package avada.media.usainua_admin.model.order;

import avada.media.usainua_admin.model.common.MappedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubOrder extends MappedEntity {

    private String url;
    @Column(name = "quantity")
    private Integer qty;
    private Double price;
    @Column(name = "estimate_weight")
    private Double estimateWeight;

    @ElementCollection(targetClass = AdditionalServices.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "suborder_additional_service", joinColumns = @JoinColumn(name = "suborder_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "additional_services")
    private Set<AdditionalServices> additionalServices = new HashSet<>();
    @Lob
    private String description;
    @OneToOne
    private InvoiceFile invoiceFile;
    @Column(name = "tracking_number")
    private String trackingNumber;

    public enum AdditionalServices {
        TAKE_PHOTO,
        ADDITIONAL_PACKAGE,
        ON_OFF_CHECK
    }

}
