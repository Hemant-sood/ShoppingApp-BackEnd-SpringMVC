package com.controllers;

import com.models.Product;
import com.models.RegisterUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class Home {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Product> getProducts(){

        Configuration con = new Configuration().configure().addAnnotatedClass(Product.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();

        Transaction txn = session.beginTransaction();

        List<Product> list = session.createQuery("from Product ").getResultList();

        System.out.println(list);

        return list;
    }


}
