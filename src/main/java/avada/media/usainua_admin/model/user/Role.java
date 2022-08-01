package avada.media.usainua_admin.model.user;

import avada.media.usainua_admin.model.common.MappedEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Role extends MappedEntity {

    @Column(unique = true)
    private String name;

}
