package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new User(null, "userName1", "email1", "password1", Role.ROLE_ADMIN));
        save(new User(null, "userName2", "email2", "password2", Role.ROLE_USER));
    }

    @Override
    public boolean delete(int id) {
        /*if (!repository.containsKey(id)) {
            return false;
        }
        log.info("delete {}", id);

        repository.remove(id);
        return true;*/
        log.info("delete {}", id);
        return repository.remove(id) == null ? false : true;

    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        log.info("save {}", user);
        return user;
    }

    @Override
    public User get(int id) {
        log.info("get {}", repository.get(id));
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        List<User> userList = repository.entrySet().stream()
                .map(iter -> iter.getValue())
                .collect(Collectors.toList());
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (User iter : userList)
            log.info("users {}", iter);
        return userList;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);

        return repository.values().stream()
                .filter(iter -> email.equalsIgnoreCase(iter.getEmail()))
                .findFirst()
                .orElse(null);

    }
}
