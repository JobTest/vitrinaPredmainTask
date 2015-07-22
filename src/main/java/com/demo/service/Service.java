package com.demo.service;

import com.demo.dao.FermerDao;
import com.demo.domain.Fermer;

/**
 * Created by alexandr on 22.07.15.
 */
public class Service {

    private static FermerDao dao = new FermerDao();

    public static void main(String[] args) {
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        Fermer[] fermers = {new Fermer("Sasha1", "Lazarchuk"), new Fermer("Anya2", "Anya2"), new Fermer("Ruslan3", "Mushinsky"), new Fermer("Sasha4", "Lazarchuk"), new Fermer("Anya5", "Neznakomka"), new Fermer("Ruslan6", "Mushinsky")};
        dao.add(fermers);
        System.out.println("ADD:");

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.println("NAME-Sasha1: '" + dao.findByName("Sasha1") + "'");

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        Fermer fermer1 = dao.find(1);
        dao.delete(fermer1);
        System.out.println("DELETE-1:");

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.println("ID-2: '" + dao.find(2) + "'");

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        Fermer fermer2 = dao.find(2);
        fermer2.setVillage("Neznakomka");
        dao.update(fermer2);
        System.out.println("UPDATE-2:");

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        dao.getAll();
    }

}
