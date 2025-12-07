package creatures;

import java.util.Objects;

public class Fact {
    public Human author;
    public String text;

    // Constructors
    public Fact(String text, Human author) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("text cannot be null or empty");
        }
        this.text = Objects.requireNonNull(text);
        this.author = author;
    }

    // Java object methods
    @Override
    public String toString() {
        return String.format("type: Fact\ntext: %s\nauthor: %s\n",
                text, author);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fact fact = (Fact) o;
        return Objects.equals(text, fact.text)
                && Objects.equals(author, fact.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), text, author);
    }
}
