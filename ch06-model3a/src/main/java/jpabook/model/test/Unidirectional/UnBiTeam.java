package jpabook.model.test.Unidirectional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "TEST_TEAM")
public class UnBiTeam {
	@Id @GeneratedValue
	@Column(name = "TEAM_ID")
	private String id;

	private String name;

	//팀 -> 회원 방향
	@OneToMany
	@JoinColumn(name = "TEAM_ID") //MEMBER 테이블의 TEAM_ID (FK)
	private List<TestMember> members = new ArrayList<>();

	public void addMember(TestMember member) {
		this.members.add(member);
		if (member.getTeam() != this) { //무한루프에 빠지지 않도록 체크
			member.setTeam(this);
		}
	}

	public UnBiTeam(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
