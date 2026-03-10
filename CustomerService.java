package service;

import model.Customer;
import java.util.*;

public class CustomerService {
    private static CustomerService instance;
    private final Map<String, Customer> custs = new HashMap<>();

    private CustomerService() {}

    public static CustomerService getInstance() {
        if (instance == null) instance = new CustomerService();
        return instance;
    }

    public void addCustomer(String f, String l, String em) {
        if (custs.containsKey(em)) {
            throw new IllegalArgumentException("Customer already exists.");
        }
        custs.put(em, new Customer(f, l, em));
    }

    public Customer getCustomer(String em) {
        return custs.get(em);
    }

    public Collection<Customer> getAllCustomers() {
        return custs.values();
    }
}