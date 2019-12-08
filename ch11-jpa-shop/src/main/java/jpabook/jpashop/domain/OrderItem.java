package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by holyeye on 2014. 3. 11..
 */
@Getter
@Setter
@ToString(exclude = {"item", "order"})
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

	@Id @GeneratedValue
	@Column(name = "ORDER_ITEM_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID")
	private Item item;      //주문 상품

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private Order order;    //주문

	private int orderPrice; //주문 가격
	private int count;      //주문 수량

	//==생성 메서드==//
	public static OrderItem createOrderItem(Item item, int orderPrice, int count) {

		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);

		item.removeStock(count);
		return orderItem;
	}

	//==비즈니스 로직==//

	/**
	 * 주문 취소
	 */
	public void cancel() {
		getItem().addStock(count);
	}

	//==조회 로직==//

	/**
	 * 주문상품 전체 가격 조회
	 */
	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}

//	@Override
//	public String toString() {
//		return "OrderItem{" +
//				"id=" + id +
//				", buyPrice=" + orderPrice +
//				", count=" + count +
//				'}';
//	}
}
