import java.io.*;
import java.util.*;


//PersonException jest klasą wyjątków służącą do zgłaszania błędów w operacjach na obiektach klasy Person.
class PersonException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public PersonException(String message) {
        super(message);
    }

}

/**
 * Klasa Person reprezentuje osoby, które są opisane za pomocą czterech
 * atrybutów: imię, nazwisko, średnia ocen, studiowany kierunek. W klasie przyjęto
 * ograniczenia:
 * - pola firstName oraz lastName muszą zawierać niepusty ciąg
 * znaków
 * — pole index musi zawierać liczbę całkowitą z przedziału [260000 - 300000].
 * - pole job musi zawierać wyłącznie jedną z pozycji zdefiniowanych w typie wyliczeniowym enum PersonStudies.
 *
 * Powyższe ograniczenia są kontrolowane i w przypadku próby nadania
 * niedozwolonej wartości, któremuś z atrybutów jest zgłaszany wyjątek
 * zawierający stosowny komunikat.
 */
public class Person implements Comparable<Person> {

    protected String firstName;
    protected String lastName;
    protected int index;
    protected String studies;

    public Person(String first_name, String last_name, String index, String studies) throws PersonException {
        setFirstName(first_name);
        setLastName(last_name);
        setIndex(Integer.parseInt(index));
        setStudies(studies);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) throws PersonException {
        if ((first_name == null) || first_name.equals(""))
            throw new PersonException("Pole <Imię> musi być wypełnione.");

        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) throws PersonException {
        if ((last_name == null) || last_name.equals(""))
            throw new PersonException("Pole <Nazwisko> musi być wypełnione.");

        this.lastName = last_name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) throws PersonException {
        if ((index < 2600 || index > 3000))
            throw new PersonException("Indeks musi mieścić się w przedziale [2600 - 3000].");
        this.index = index;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }
    //deklaracja kolekcji
    public static Set<Person> collectionHashSet = new HashSet<>();
    public static Set<Person> collectionTreeSet = new TreeSet<>();
    public static ArrayList<Person> collectionArrayList = new ArrayList<>();
    public static LinkedList<Person> collectionLinkedList = new LinkedList<>();

    //HashMap i TreeMap są listami dwuargumentowymi, dlatego wprowadzona zostaje liczba całkowita zliczająca obiekty Person
    public static HashMap<Person, Integer> collectionHashMap = new HashMap<>();
    public static TreeMap<Person, Integer> collectionTreeMap = new TreeMap<>();

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public static void readFromFile(String file_name) throws PersonException {

        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file_name));

            String line = reader.readLine();

            Person currentPerson;
            int count=0;

            while (line != null) {

                count++;

                var txt = line.split(";");

                currentPerson = new Person(txt[0], txt[1], txt[2], txt[3]);

                addToCollections(currentPerson, count);

                line=reader.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new PersonException("Nie odnaleziono pliku " + file_name);
        } catch (IOException e) {
            throw new PersonException("Wystąpił błąd podczas odczytu danych z pliku.");
        }
    }

    public static Person readFromKeyboard() throws PersonException{

        Scanner keyboard = new Scanner(System.in);

        System.out.println("\nPodaj imię:\n");
        String first_name = keyboard.nextLine();

        System.out.println("\nPodaj nazwisko:\n");
        String last_name = keyboard.nextLine();

        System.out.println("\nPodaj indeks:\n");
        String index = keyboard.nextLine();

        if(Integer.parseInt(index) < 2600 || Integer.parseInt(index)>3000){
            System.out.println("\nIndeks musi zawierać się w przedziale 2600-3000\n");
            index = keyboard.nextLine();
        }

        System.out.println("\nPodaj kierunek:\n");
        String studies = keyboard.nextLine();

        return new Person(first_name,last_name,index,studies);
    }

    public static void addToCollections(Person person, int count){
        collectionHashSet.add(person);
        collectionTreeSet.add(person);
        collectionArrayList.add(person);
        collectionLinkedList.add(person);
        collectionHashMap.put(person, count);
        collectionTreeMap.put(person, count);

    }
    public static void removeFromCollections(Person person, int count){
        collectionHashSet.remove(person);
        collectionTreeSet.remove(person);
        collectionArrayList.remove(person);
        collectionLinkedList.remove(person);
        collectionHashMap.remove(person, count);
        collectionTreeMap.remove(person, count);
    }

    @Override
    public int compareTo(Person person) {
        if (equals(person))
            return 0;

        if (!getFirstName().equals(person.getFirstName()))
            return getFirstName().compareTo(person.getFirstName());
        else if (!getLastName().equals(person.getLastName()))
            return getLastName().compareTo(person.getLastName());
        else if (getIndex() < person.getIndex())
            return -1;
        else if (getIndex() > person.getIndex())
            return 1;
        else
            return getStudies().compareTo(person.getStudies());
    }


    public static Comparator<Person> FirstNameComparator = new Comparator<>() {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getFirstName().compareTo(p2.getFirstName());
        }
    };


    public static Comparator<Person> LastNameComparator = new Comparator<>() {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getLastName().compareTo(p2.getLastName());
        }
    };

    public static Comparator<Person> StudiesComparator = new Comparator <>() {
        @Override
        public int compare(Person p1, Person p2) {return p1.getStudies().compareTo(p2.getStudies());}
    };

    public static Comparator<Person> IndexComparator = new Comparator<>() {
        @Override
        public int compare(Person p1, Person p2) {
            return (int) (p2.getIndex() - p1.getIndex());
        }
    };
}