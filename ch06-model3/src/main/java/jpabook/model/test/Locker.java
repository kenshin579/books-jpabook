package jpabook.model.test;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Setter
@Getter
public class Locker {
	@Id @GeneratedValue
	@Column(name = "LOCKER_ID")
	private Long id;
	private String name;

	@OneToOne
	@JoinColumn(name = "MEMBER_ID")
	private TestMember member;
}
