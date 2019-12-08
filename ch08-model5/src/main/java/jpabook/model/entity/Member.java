package jpabook.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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
@Entity
public class Member extends BaseEntity {

	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	private String name;

	private String city;
	private String street;
	private String zipcode;

	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<Order>();
}
