package org.thisrc.calender.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.Optional;

@ToString
@Getter
@Setter
public class Participant {
    String name;
    String email;

    public Participant(String name){
        this.name=name;
        this.email = Optional.of(name)
                .map(fullName ->fullName.split(" "))
                .map(splitName -> splitName[0]+"."+splitName[1]+"@email.com")
                .orElse("error@email.com");
    }

    public String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return name.equals(that.name) &&
                email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
