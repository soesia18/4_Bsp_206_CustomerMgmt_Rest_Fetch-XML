package at.htlkaindorf.custmgmt.db;

import at.htlkaindorf.custmgmt.beans.Customer;
import at.htlkaindorf.custmgmt.beans.CustomerList;
import jakarta.servlet.ServletContext;
import jakarta.xml.bind.JAXB;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CustomerDB {
    private static CustomerDB theInstance;
    private CustomerList customerList;

    private File file;

    private CustomerDB() {
        customerList = new CustomerList();

        // customerList.add(new Customer(1, "Josef GmbH", "Tulwitz", 20000000));
        // customerList.add(new Customer(2, "Hansi KG", "Feldbach", 700));
        // customerList.add(new Customer(3, "Daimler AG", "Stuttgart", 100000000));
        // customerList.add(new Customer(4, "Audi GmbH und Co KG", "Ingolstadt", 250000000));

    }


    public void loadDB (File file) {
        this.file = file;
        customerList = JAXB.unmarshal(file, CustomerList.class);
    }

    public synchronized static CustomerDB getInstance() {
        if (theInstance == null) theInstance = new CustomerDB();
        return theInstance;
    }

    public void addCustomer(Customer customer) throws KeyAlreadyExistsException {
        if (findCustomerById(customer.getId()).isPresent()) throw new KeyAlreadyExistsException();
        else customerList.getCustomerList().add(customer);

        // write File in target only
        JAXB.marshal(customerList, file);
    }

    public Optional<Customer> findCustomerById(int id) {
        //return customerList.getCustomerList().stream().filter(customer -> customer.getId() == id).findFirst();
        return customerList.findCustomerById(id);
    }

    public Customer getCustomer(int id) throws NoSuchElementException {

//        Optional<Customer> optionalCustomer = findCustomerById(id);
//
//        if (optionalCustomer.isPresent()) {
//            return optionalCustomer.get();
//        } else {
//            throw new NoSuchElementException();
//        }

        return findCustomerById(id).get();
    }

    public CustomerList getAllCustomer () {
        return customerList;
    }

    public Customer removeCustomer(int id) throws NoSuchElementException{
        Customer customer = getCustomer(id);
        customerList.getCustomerList().remove(customer);

        saveXMLData();
        return customer;
    }

    public void replaceCustomer(Customer customer) throws NoSuchElementException {
        Customer oldCustomer = getCustomer(customer.getId());

        customerList.getCustomerList().remove(oldCustomer);
        customerList.getCustomerList().add(customer);

        saveXMLData();
    }

    public void saveXMLData() {
        JAXB.marshal(customerList, file);
    }


}
