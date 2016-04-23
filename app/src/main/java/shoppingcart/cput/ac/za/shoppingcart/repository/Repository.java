package shoppingcart.cput.ac.za.shoppingcart.repository;

import java.util.Set;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-23
 */
public interface Repository<E, ID> {
    E findById (ID id);
    E save (E entity);
    E update (E entity);
    E delete (E entity);
    Set<E> findAll();
    int deleteAll();
}
