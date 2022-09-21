package com.controllers;

import com.models.AuthResponseObject;
import com.models.LoginUser;
import com.models.RegisterUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
public class Auth {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public AuthResponseObject registration(@RequestBody RegisterUser user ){

        AuthResponseObject resObject = new AuthResponseObject("","", null);

        if( user.getName().equals("") ) {

            resObject.setError("Name is mandatory ");

            return resObject;
        }

        if( user.getEmail().equals("") ) {

            resObject.setError("Email is mandatory ");

            return resObject;
        }


        if( user.getPassword().equals("") ) {

            resObject.setError("Password is mandatory ");

            return resObject;
        }

        if( user.getPassword().length() < 5) {

            resObject.setError("Password must be more than 4 characters ");

            return resObject;
        }

        try {

            Configuration con = new Configuration().configure().addAnnotatedClass(RegisterUser.class);

            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();

            Transaction txn = session.beginTransaction();


            Query q = session.createQuery("from RegisterUser  where email = :userEmail");
            q.setParameter("userEmail", user.getEmail());
            List<RegisterUser> list = q.getResultList();

            if (list.isEmpty()) {

                session.save(user);
                txn.commit();

            } else {
                resObject.setError("User already present with this Email");
            }

        }
        catch (Exception e) {
            resObject.setError(e.getMessage());
        }

        return resObject;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthResponseObject login(@RequestBody LoginUser user){

        AuthResponseObject resObject = new AuthResponseObject("","",null) ;

        if( user.getEmail().equals("")) {
            resObject.setError("Email is mandatory");
            return resObject;
        }

        if( user.getPassword().equals("")) {
            resObject.setError("Password is mandatory");
            return resObject;
        }

        try {
            Configuration con = new Configuration().configure().addAnnotatedClass(RegisterUser.class);

            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();

            Query q = session.createQuery("from RegisterUser  where email = :userEmail");
            q.setParameter("userEmail", user.getEmail());
            List<RegisterUser> list = q.getResultList();


            if (!list.isEmpty()) {
                if (list.get(0).getPassword().equals(user.getPassword())) {
                    resObject.setName(list.get(0).getName());
                    resObject.setEmail(list.get(0).getEmail());
                } else {
                    resObject.setError("Wrong password");
                }
            } else {
                resObject.setError("User not found");
            }
        }
        catch (Exception e) {
            resObject.setError(e.getMessage());
        }

        return resObject;
    }
}
