package org.tomat.spbsu.sakod.maphash;

import java.util.*;

public class MapAndSet {
    public static void main(String[] args) {
        new MapAndSet().runSet();
        new MapAndSet().runMap();
    }

    public void runSet() {
        Scanner sc = new Scanner(System.in);
        Set<Person> people = new HashSet<>();
        while (true) {
            Person p = new Person(sc.next(), sc.next());
            if (!people.contains(p)) {
                people.add(p);
            } else {
                System.out.println("Repeat");
                System.exit(0);
            }
        }
    }

    public void runMap() {
        Scanner sc = new Scanner(System.in);
        Map<String, Person> map = new HashMap<>();
        while (true) {
            String name = sc.next();
            String surname = sc.next();
            if (!map.containsKey(name + surname)) {
                map.put(name + surname, new Person(name, surname));
            } else {
                System.out.println("Repeat");
                System.exit(0);
            }
        }
    }


    public static class Person {
        private final String firstName;
        private final String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
            return !(lastName != null ? !lastName.equals(person.lastName) : person.lastName != null);

        }

        @Override
        public int hashCode() {
            int result = firstName != null ? firstName.hashCode() : 0;
            result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
            return result;
        }
    }
}
