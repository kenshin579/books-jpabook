package jpabook.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by 1001218 on 15. 4. 5..
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

	private Date createdDate;       //등록일
	private Date lastModifiedDate;  //수정일
}
