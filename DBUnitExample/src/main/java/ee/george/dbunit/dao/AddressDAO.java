package ee.george.dbunit.dao;

import org.springframework.stereotype.Repository;

import ee.george.dbunit.model.Address;

@Repository
public class AddressDAO extends JpaDAO<Address> implements IAddressDAO {

	public AddressDAO() {
		setClazz(Address.class);
	}

}
