package ee.george.dbunit.dao;

import org.springframework.stereotype.Repository;

import ee.george.dbunit.model.Address;

@Repository
public interface IAddressDAO extends IJpaDAO<Address> {

}
