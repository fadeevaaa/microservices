package ru.fadeevaaa.staff.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fadeevaaa.staff.employeeservice.model.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllUsersByCompanyId(long companyId);
}
