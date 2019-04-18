package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * User: HolyEyE
 * Date: 2013. 12. 3. Time: 오후 9:48
 */

@Repository
public class ItemRepository {

	@PersistenceContext
	EntityManager em;

	/**
	 * 저장
	 * - 신규 데이터
	 * - 변경된 데이터
	 *
	 * @param item
	 */
	public void save(Item item) {
		if (item.getId() == null) {
			em.persist(item);
		} else {
			//업데이트로 들어온 엔티티는 준영속성이라서 merge로 병함시켜 저장함
			em.merge(item);
		}
	}

	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}

	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class).getResultList();
	}
}
