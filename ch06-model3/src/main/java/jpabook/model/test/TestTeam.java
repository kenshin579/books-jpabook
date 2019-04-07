package jpabook.model.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "TEST_TEAM")
public class TestTeam {
	@Id
	@Column(name = "TEAM_ID")
	private String id;

	private String name;

	//팀 -> 회원 방향
	@OneToMany(mappedBy = "team") //MappedBy 속성의 값은 연관관계의 주인인 Member.team
	private List<TestMember> members = new ArrayList<>();

	public void addMember(TestMember member) {
		this.members.add(member);
		if (member.getTeam() != this) { //무한루프에 빠지지 않도록 체크
			member.setTeam(this);
		}
	}

	public TestTeam(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
