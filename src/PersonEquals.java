public class PersonEquals extends Person {

    public PersonEquals(Person person) throws PersonException {
        super(person.getFirstName(), person.getLastName(), Integer.toString(person.getIndex()), person.getStudies());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        // can use this when both objects have the same class
        PersonEquals person = (PersonEquals) obj;

        if (getIndex() != person.getIndex())
            return false;

        if (getFirstName() != null ? !getFirstName().equals(person.getFirstName()) : person.getFirstName() != null)
            return false;

        if (getLastName() != null ? !getLastName().equals(person.getLastName()) : person.getLastName() != null)
            return false;

        return getStudies().equals(person.getStudies());
    }

    @Override
    public int hashCode() {
        double result;
        result = getFirstName() != null ? getFirstName().hashCode() : 0;

        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getIndex();
        result = 31 * result + getStudies().hashCode();

        return (int) result;
    }
}