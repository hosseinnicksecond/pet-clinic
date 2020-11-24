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
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "city")
    private String city;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Pet> pets=new HashSet<>();

    @Builder
    public Owner(Long id, String firstName, String lastName, String address,
                 String phoneNumber, String city, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        telephone = phoneNumber;
        this.city = city;
        this.pets = pets;
    }

    public void addPet(Pet pet){
        this.getPets().add(pet);
        pet.setOwner(this);
    }

    public Pet getPet(String name){
       return getPet(name,false);
    }

    public Pet getPet(String name,boolean ignoreNew){
        name= name.toLowerCase();
        for (Pet pet:pets){
            if(!ignoreNew || !pet.isNew()){
                String compName=pet.getName().toLowerCase();
                if (compName.equals(name)){
                    return pet;
                }
            }
        }
        return null;
    }
}
