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
import javax.persistence.Table;

@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TEST_MEMBER")
public class TestMember {
	@Id
	@Column(name = "MEMBER_ID")
	@NonNull
	private String id;

	@NonNull
	private String username;

	//회원 -> 팀 방향 (외래키를 가지고 있는 쪽인 연관관계의주인이 됨)
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private TestTeam team;

	public void setTeam(TestTeam team) {
		this.team = team;
		if (this.team != null) {
			this.team.getMembers().remove(this);
		}

		//무한루프에 빠지지 않도록 체크
		if (!team.getMembers().contains(this)) {
			team.getMembers().add(this); //편의 메서드 추가
		}
	}
}
