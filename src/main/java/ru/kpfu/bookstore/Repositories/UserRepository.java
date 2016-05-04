package ru.kpfu.bookstore.Repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.bookstore.entities.User;


@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Integer> {

}