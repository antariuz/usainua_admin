package avada.media.usainua_admin.model.user;

import avada.media.usainua_admin.model.common.MappedEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "personal_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalData extends MappedEntity {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    private User user;
    private Double balance;
    private String firstName;
    private String lastName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthdate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;

    public enum Gender {
        MALE,
        FEMALE
    }

}
