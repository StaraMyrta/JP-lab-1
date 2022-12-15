import java.util.Scanner;

public class PersonApp {
    public static void main(String[] args) throws PersonException {
        //Wywołanie metody wyboru akcji. Program kończy działanie dopiero
        // po wyborze opcji zakończenia go przez użytkownika.
        options();
    }

    private static void options() throws PersonException{

        //utworzenie obiektu aplikacji
        PersonApp application = new PersonApp();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\n\nWybierz akcję poprzez wprowadzenie jej numeru:\n\n" +
                "1. Wczytanie danych z pliku do kolekcji\n" +
                "2. Wczytanie danych z klawiatury do kolekcji (możliwość duplikacji)\n" +
                "3. Porównanie obiektów (niezrobione)\n" +
                "4. Usunięcie osoby\n" +
                "5. Wypisanie zawartości kolekcji\n" +
                "6. Zakończenie działania programu\n");
        int choose = keyboard.nextInt();
        int choose2;
        if(choose == 1) {
            //uruchomienie pętli wczytującej dane z pliku do kolekcji
            application.fileReader();
            choose = 0;
        }
        while(choose == 2){

            int count=0;

            System.out.println("\n1.Dodaj osobę\n" +
                    "2.Wróć do menu głównego\n");

            choose2 = keyboard.nextInt();

            if(choose2 == 1){

                count++;

                //uruchomienie metody wczytującej dane wprowadzone przez użytkownika do obiektu currentPerson
                Person currentPerson = Person.readFromKeyboard();

                //uruchomienie metody dodającej obiekt currentPerson do wszystkich kolekcji
                Person.addToCollections(currentPerson,count);

                System.out.println("\nCzy chcesz zduplikować wczytaną osobę?\n" +
                        "1. Tak\n" +
                        "2. Nie\n");
                int choose3 = keyboard.nextInt();
                if(choose3 == 1){
                    Person.addToCollections(currentPerson,count);
                }else{
                    if(choose3 != 2){
                        System.out.println("Chyba się pomyliłeś! Wprowadź numer wybranej opcji jeszcze raz.");
                    }
                }


            }else{
                if(choose2 == 2){
                    choose = 0;
                }else{
                    System.out.println("Chyba się pomyliłeś! Wprowadź numer wybranej opcji jeszcze raz.");
                }
            }
        }
        if(choose == 3){
        }
        while(choose == 4){

            System.out.println("\n1.Usuń osobę\n" +
                    "2.Wróć do menu głównego\n");

            choose2 = keyboard.nextInt();

            if(choose2 == 1){

                //uruchomienie metody wczytującej dane wprowadzone przez użytkownika do obiektu currentPerson
                Person currentPerson = Person.readFromKeyboard();

                System.out.println("\nWprowadź numer porządkowy osoby usuwanej:\n");
                int number = keyboard.nextInt();
                //uruchomienie metody usuwającej obiekt currentPerson do wszystkich kolekcji
                Person.removeFromCollections(currentPerson, number);

            }else{
                if(choose2 == 2){
                    choose = 0;
                }else{
                    System.out.println("Chyba się pomyliłeś! Wprowadź numer wybranej opcji jeszcze raz.");
                }
            }


        }
        if(choose == 5){
            //uruchomienie pętli wypisującej zawartość kolekcji
            application.collectionReader();
            choose = 0;
        }
        if(choose == 6){
        }
        if(choose == 0){
            options();
        }
    }

    private void fileReader() throws PersonException {
        String file_name = "Dane.txt";
        Person.readFromFile(file_name);
    }

    public void collectionReader(){

        System.out.println("Zawartość kolekcji HashSet:\n");

        for(Person person: Person.collectionHashSet){
            System.out.println(person.firstName + " " + person.lastName + " " + person.index + " " + person.studies);
        }

        System.out.println("\n\nZawartość kolekcji TreeSet:\n");

        for(Person person:Person.collectionTreeSet){
            System.out.println(person.firstName + " " + person.lastName + " " + person.index + " " + person.studies);
        }

        System.out.println("\n\nZawartość kolekcji ArrayList:\n");

        for(Person person: Person.collectionArrayList){
            System.out.println(person.firstName + " " + person.lastName + " " + person.index + " " + person.studies);
        }
        System.out.println("\nRozmiar:" + Person.collectionArrayList.size());

        System.out.println("\n\nZawartość kolekcji LinkedList:\n");

        for(Person person: Person.collectionLinkedList){
            System.out.println(person.firstName + " " + person.lastName + " " + person.index + " " + person.studies);
        }

        System.out.println("\n\nZawartość kolekcji HashMap:\n");

        for (Person person : Person.collectionHashMap.keySet()) {
            System.out.println(" Nr porządkowy: " + Person.collectionHashMap.get(person) + " | Dane: " + person.firstName + " " + person.lastName + " " + person.index + " " + person.studies);
        }


        System.out.println("\n\nZawartość kolekcji TreeMap:\n");

        for (Person person : Person.collectionTreeMap.keySet()) {
            System.out.println("Nr porządkowy: " + Person.collectionTreeMap.get(person) + " | Dane: " + person.firstName + " " + person.lastName + " " + person.index + " " + person.studies);
        }

    }
}
