package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by holyeye on 2014. 3. 11..
 */

@Getter
@Setter
@ToString(exclude = {"orders"})
@Entity
public class Member {

	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	private String name;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<Order>();
}
