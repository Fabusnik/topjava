package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Override
    @Transactional
    Meal save(Meal meal);

    //@Query("select m from Meal m where m.id=:id and m.user.id=:userId")
    //Optional<Meal> findByIdAndUserId(@Param("id") Integer id, @Param("userId") int userId);
    Optional<Meal> findByIdAndUserId(Integer id, int userId);

    @Transactional
    @Modifying
    //@Query("DELETE FROM Meal m WHERE m.id =:id AND m.user.id=:userId")
    int deleteMealByIdAndUserId(int id, int userId);


    //@Query("SELECT m from Meal m where m.user.id=:userId ORDER BY m.dateTime DESC ")
    List<Meal> findMealByUserIdOrderByDateTimeDesc(@Param("userId") int userId);

    @Query("select m from Meal m where m.user.id=:userId AND m.dateTime>=:startDateTime and m.dateTime<=:endDateTime order by m.dateTime desc")
    List<Meal> findBetween(@Param("startDateTime")LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime, @Param("userId") Integer userId);

}
