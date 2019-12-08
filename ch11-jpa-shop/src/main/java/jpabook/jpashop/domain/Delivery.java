package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.w3c.dom.Node;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
@NoArgsConstructor
@ToString(exclude = {"order"})
@Entity
public class Delivery {

	@Id @GeneratedValue
	@Column(name = "DELIVERY_ID")
	private Long id;

	@OneToOne(mappedBy = "delivery")
	private Order order;

	@Embedded
	private Address address;

	@Enumerated(EnumType.STRING)
	private DeliveryStatus status; //ENUM [READY(준비), COMP(배송)]

	public Delivery(Address address) {
		this.address = address;
		this.status = DeliveryStatus.READY;
	}

//	@Override
//	public String toString() {
//		return "Delivery{" +
//				"id=" + id +
//				", address=" + address +
//				", status=" + status +
//				'}';
//	}
}
