package jpabook.model.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class TestMember {
	@Id
	@Column(name = "MEMBER_ID")
	@NonNull
	private String id;

	@NonNull
	private String username;

	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private TestTeam team;
}
