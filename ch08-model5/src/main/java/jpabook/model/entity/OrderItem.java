package jpabook.model.entity;

import jpabook.model.entity.item.Item;
import lombok.Getter;
import lombok.Setter;

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
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

	@Id @GeneratedValue
	@Column(name = "ORDER_ITEM_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY) //**
	@JoinColumn(name = "ITEM_ID")
	private Item item;      //주문 상품

	@ManyToOne(fetch = FetchType.LAZY) //**
	@JoinColumn(name = "ORDER_ID")
	private Order order;    //주문

	private int orderPrice; //주문 가격
	private int count;      //주문 수량

	@Override
	public String toString() {
		return "OrderItem{" +
				"id=" + id +
				", buyPrice=" + orderPrice +
				", count=" + count +
				'}';
	}
}
