package creatures;

import java.util.ArrayList;
import java.util.Objects;

public class Name {
    public String firstName;
    public String middleName;
    public String lastName;
    public ArrayList<String> nicknames;

    // Constructors
    public Name(String firstName, String middleName, String lastName) {
        this.firstName = Objects.requireNonNull(firstName);
        this.middleName = Objects.requireNonNull(middleName);
        this.lastName = Objects.requireNonNull(lastName);
        nicknames = new ArrayList<>();
    }

    public Name(String firstName, String middleName, String lastName, ArrayList<String> nicknames) {
        this.firstName = Objects.requireNonNull(firstName);
        this.middleName = Objects.requireNonNull(middleName);
        this.lastName = Objects.requireNonNull(lastName);
        this.nicknames = new ArrayList<>(Objects.requireNonNull(nicknames));
    }

    // Java object methods
    @Override
    public String toString() {
        return String.format("type: Name\nfirst:%s\nmiddle:%s\nlast:%s\nnicknames:%s\n",
                firstName, middleName, lastName, nicknames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;
        return Objects.equals(firstName, name.firstName)
                && Objects.equals(middleName, name.middleName)
                && Objects.equals(lastName, name.lastName)
                && Objects.equals(nicknames, name.nicknames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), firstName, middleName, lastName, nicknames);
    }
}
