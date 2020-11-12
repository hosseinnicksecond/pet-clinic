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
@Table(name = "owner")
public class Owner extends Person {
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String PhoneNumber;
    @Column(name = "city")
    private String city;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Pet> pets=new HashSet<>();

    @Builder
    public Owner(Long id, String firstName, String lastName, String address,
                 String phoneNumber, String city, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        PhoneNumber = phoneNumber;
        this.city = city;
        this.pets = pets;
    }
}
