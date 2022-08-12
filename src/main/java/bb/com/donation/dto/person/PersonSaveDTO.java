package bb.com.donation.dto.person;

import bb.com.donation.model.Person;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PersonSaveDTO implements PersonGenericDTO{
    private String name;
    @Override
    public Person toPerson() {
        Person person = new Person();
        person.setName(this.getName());
        return Person.builder()
                .id(null)
                .name(name)
                .build();
    }
}