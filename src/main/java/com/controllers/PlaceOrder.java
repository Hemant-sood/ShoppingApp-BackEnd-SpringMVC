package com.controllers;

import com.models.LoginUser;
import com.models.Orders;
import com.models.Product;
import com.models.ResponseObj;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PlaceOrder {

    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public ResponseObj placeOrder(@RequestBody List<Orders> orders) {

        ResponseObj responseObj = new ResponseObj();

        try {

            Configuration con = new Configuration().configure().addAnnotatedClass(Orders.class);

            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();

            Transaction txn = session.beginTransaction();

            for(Orders order : orders) {
                if( order.getQty() > 0)
                    session.save(order);
            }

            txn.commit();
            responseObj.setMessage("Order Placed");

        }
        catch (Exception e) {
            responseObj.setError(e.getMessage());
        }


        return responseObj;

    }




    @RequestMapping(value = "/getAllOrders", method = RequestMethod.POST)
    public List<Orders> getAllOrders(@RequestBody LoginUser user) {

        List<Orders> list = null;

        try {
            Configuration con = new Configuration().configure().addAnnotatedClass(Orders.class);

            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();

            Transaction txn = session.beginTransaction();

            Query q = session.createQuery("from Orders where userEmail = :userEmail");
            q.setParameter("userEmail", user.getEmail());
            list = q.getResultList();

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

}
