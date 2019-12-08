package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by holyeye on 2014. 3. 15..
 */
@Getter
@Setter
public class OrderSearch {

	private String memberName;      //회원 이름
	private OrderStatus orderStatus;//주문 상태

}
