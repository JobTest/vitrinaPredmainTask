package com;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by alexandr on 15.07.15.
 * {@link http://www.mkyong.com/java/jaxb-hello-world-example/}
 * ************************
 * JAXB hello world example
 */

//public class JAXBExample implements MyParser {
public class JaxbParser {

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setId(100);
        customer.setName("mkyong");
        customer.setAge(29);

        try {
            /* # 2 */
            Customer customer2 = (Customer) getObject(new File("customer.xml"), Customer.class);
            System.out.println(customer2);
        } catch (JAXBException e) { e.printStackTrace(); }
    }


//    @Override
    public static Object getObject(File f, Class c) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(f);
        return object;
    }

//    @Override
    public static void saveObject(File f, Object o) throws JAXBException {
        JAXBContext contect = JAXBContext.newInstance(o.getClass());
        Marshaller marshaller = contect.createMarshaller();
        marshaller.marshal(o, f);
    }

}
