package jpabook.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by holyeye on 2014. 3. 11..
 */
@Getter
@Setter
@Entity
public class Delivery {

	@Id @GeneratedValue
	@Column(name = "DELIVERY_ID")
	private Long id;

	@OneToOne(mappedBy = "delivery")
	private Order order;

	private String city;
	private String street;
	private String zipcode;

	@Enumerated(EnumType.STRING)
	private DeliveryStatus status; //ENUM [READY(준비), COMP(배송)]
}
