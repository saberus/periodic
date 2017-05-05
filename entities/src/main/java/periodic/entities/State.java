package periodic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "states")
public class State implements Identified<Long>, Serializable{

    private static final long SERIAL_VERSION_UID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name_state")
    private String state;


}
