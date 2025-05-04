import unique.Vehicle;

public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN = "\u001B[36m";

    public static void main(String[] args) {
        System.out.println(CYAN + "=== Tworzenie pojazdów ===" + RESET);

        Vehicle car1 = new Vehicle("Toyota", "Corolla", "ABC123");
        Vehicle car2 = new Vehicle("Honda", "Civic", "XYZ789");

        System.out.println(GREEN + "✔ Utworzone pojazdy:" + RESET);
        for (Vehicle v : Vehicle.getExtent()) {
            System.out.println("  " + v);
        }

        System.out.println(CYAN + "\n=== Próba utworzenia pojazdu z duplikatem rejestracji ===" + RESET);
        try {
            Vehicle car3 = new Vehicle("Ford", "Focus", "ABC123");
        } catch (IllegalArgumentException e) {
            System.out.println(RED + "✖ Błąd tworzenia pojazdu: " + e.getMessage() + RESET);
        }

        System.out.println(CYAN + "\n=== Próba ustawienia duplikatu rejestracji dla car2 ===" + RESET);
        try {
            car2.setRegistrationNumber("ABC123");
        } catch (IllegalArgumentException e) {
            System.out.println(RED + "✖ Błąd aktualizacji pojazdu: " + e.getMessage() + RESET);
        }

        System.out.println(CYAN + "\n=== Aktualizacja numeru rejestracyjnego car1 ===" + RESET);
        car1.setRegistrationNumber("DEF456");
        System.out.println(GREEN + "✔ Zaktualizowany car1: " + RESET + car1);

        System.out.println(CYAN + "\n=== Ponowna próba ustawienia numeru rejestracyjnego 'ABC123' dla car2 (teraz wolny) ===" + RESET);
        car2.setRegistrationNumber("ABC123");
        System.out.println(GREEN + "✔ Zaktualizowany car2: " + RESET + car2);
    }
}