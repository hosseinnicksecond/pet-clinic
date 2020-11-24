package home.train.formatter;

import home.train.model.PetType;
import home.train.service.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String s, Locale locale) throws ParseException {
        Collection<PetType> petTypes=petTypeService.findAll();
        for (PetType p:petTypes){
            if(p.getName().equals(s)){
                return p;
            }
        }
        throw new ParseException("petType not found"+s,0);
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
