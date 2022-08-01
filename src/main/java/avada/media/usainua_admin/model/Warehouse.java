package avada.media.usainua_admin.model;

import avada.media.usainua_admin.model.common.MappedEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse extends MappedEntity {

    private String street;
    private String city;
    private String state;
    private String zip;
    @Column(name = "mobile_phone_number")
    private String mobilePhone;
    private boolean main;


}
