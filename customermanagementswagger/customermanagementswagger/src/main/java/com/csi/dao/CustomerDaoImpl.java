package com.csi.dao;

import com.csi.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDaoImpl implements CustomerDao {

    SessionFactory factory=new AnnotationConfiguration().configure().buildSessionFactory();


    @Override
    public void saveData(Customer customer) {
        Session session=factory.openSession();
        Transaction transaction=session.beginTransaction();
        session.save(customer);
        transaction.commit();

    }

    @Override
    public Customer getDataById(int custId) {
        Session session= factory.openSession();
        Customer customer=new Customer();
        for(Customer cust:getAllData()){
            if(cust.getCustId()==custId){
                customer=cust;
            }
        }
        return  customer;
    }

    @Override
    public List<Customer> getAllData() {
        Session session= factory.openSession();
        return session.createQuery("from Customer").list();
    }

    @Override
    public void updateData(Customer customer) {
        Session session=factory.openSession();
        Transaction transaction=session.beginTransaction();

        session.update(customer);
        transaction.commit();


    }

    @Override
    public void deleteDataById(int custId) {
        Session session=factory.openSession();
        Transaction transaction=session.beginTransaction();
        Customer customer1=getDataById(custId);
        session.delete(customer1);
        transaction.commit();


    }
}
