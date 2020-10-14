package home.train.model;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person {

    private Set<Speciality> specialitySet=new HashSet<>();

    public Set<Speciality> getSpecialitySet() {
        return specialitySet;
    }

    public void setSpecialitySet(Set<Speciality> specialitySet) {
        this.specialitySet = specialitySet;
    }
}
