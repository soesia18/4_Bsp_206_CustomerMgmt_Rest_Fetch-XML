package at.htlkaindorf.custmgmt.beans;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "customers")
public class CustomerList {
    @XmlElement(name="customer")
    private List<Customer> customerList;

    public void addCustomer (Customer c) {
        customerList.add(c);
    }

    public void removeCustomer (Customer c) {
        customerList.remove(c);
    }

    public Optional<Customer> findCustomerById (int id){
        return customerList.stream().filter(c -> c.getId() == id).findFirst();
    }

    public CustomerList() {
        customerList = new ArrayList<>();
    }
}
