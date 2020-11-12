package home.train.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="vets")
public class Vet extends Person {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "vet_specialities", joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialitySet=new HashSet<>();

    @Builder
    public Vet(Long id, String firstName, String lastName, Set<Speciality> specialitySet) {
        super(id, firstName, lastName);
        this.specialitySet = specialitySet;
    }
}
