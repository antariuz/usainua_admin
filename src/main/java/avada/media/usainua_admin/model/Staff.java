package avada.media.usainua_admin.model;

import avada.media.usainua_admin.model.common.MappedEntity;
import avada.media.usainua_admin.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Data @NoArgsConstructor @AllArgsConstructor
public class Staff extends MappedEntity {

    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

}
