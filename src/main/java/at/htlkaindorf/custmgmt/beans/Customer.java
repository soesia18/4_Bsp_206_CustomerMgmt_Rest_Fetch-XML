package at.htlkaindorf.custmgmt.beans;

import jakarta.xml.bind.JAXB;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

    @XmlAttribute
    private int id;
    @XmlElement(name = "customerName")
    private String name;
    @XmlElement(name = "customerAddress")
    private String address;
    private float sales;

    public static void main(String[] args) {
        /*
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer(1, "Simon", "Großfelgitsch", 10.0f);
        Customer customer2 = new Customer(1, "Simon", "Großfelgitsch", 10.0f);

        customers.add(customer);
        customers.add(customer2);

        CustomerList customerList = new CustomerList(customers);

        JAXB.marshal(customerList, "C:\\Users\\simon\\OneDrive - HTBLA Kaindorf\\4. Klasse\\POS\\Projects\\_Q2\\Bsp_206_CustomerMgmt_Rest_Fetch - XML\\src\\main\\webapp\\xml\\test.xml");
        */

        File file = new File("C:\\Users\\simon\\OneDrive - HTBLA Kaindorf\\4. Klasse\\POS\\Projects\\_Q2\\Bsp_206_CustomerMgmt_Rest_Fetch - XML\\src\\main\\webapp\\xml\\customersRes.xml");
        CustomerList customerList = JAXB.unmarshal(file, CustomerList.class);

        System.out.println(customerList);
        //ServletContext context;

        //context.getRealPath("/xml/customersRes.xml");

    }
}
