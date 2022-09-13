package com.rewards.calculation;


import static org.assertj.core.api.Assertions.assertThat;

import com.rewards.calculation.domain.Customer;
import com.rewards.calculation.domain.Spend;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RewardManagementApiApplicationTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	Integer customerId;

	@BeforeEach
	public void createCustomer() {
		String customerURL = "http://localhost:" + port + "/customers";
		Customer customer = new Customer();
		customer.setName("testCustomer");
		ResponseEntity<Customer> objCustomer = restTemplate.postForEntity(customerURL, customer, Customer.class);
		this.customerId = objCustomer.getBody().getId();
	}


	@Test
	public void recordPurchase_RetrievePurchase() {
		String purchaseURL = "http://localhost:" + port + "/spends";
		Spend purchase = new Spend();
		//  purchase.setTransactionDate(Calendar.getInstance().getTime());
		purchase.setCustomerId(customerId);
		purchase.setAmount(Double.valueOf(150));
		ResponseEntity<Spend> purchaseEntity =
				restTemplate.postForEntity(purchaseURL, purchase, Spend.class);
		assertThat(purchaseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		String customerURL = "http://localhost:" + port + "/customers/" + this.customerId;
		Customer customer = restTemplate.getForObject(customerURL, Customer.class);
		Assertions.assertTrue(customer.getSpend().size() == 1);
	}
}
