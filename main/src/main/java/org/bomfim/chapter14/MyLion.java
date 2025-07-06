package org.bomfim.chapter14;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MyLion extends Mamal implements Serializable {
    String name;
    int age;
    transient String type;
    private static String version = "1.0"; //Not serializable
//    Mamal mamal = new Mamal(); //java.io.NotSerializableException

    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
        System.out.println("READ OBJECT - ObjectInputStream");
        in.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        // custom logic
        System.out.println("WRITE OBJECT - ObjectOutputStream");
        out.defaultWriteObject();
    }

    {
        this.type = "N/A";
    }

    public MyLion() {
        System.out.println("MyLion created with default constructor");
    }

    public MyLion(String name, int age, String type) {
        this.name = name;
        this.age = age;
        this.type = type;
        System.out.println("MyLion created");
    }

    @Override
    public String toString() {
        return "MyLion{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
