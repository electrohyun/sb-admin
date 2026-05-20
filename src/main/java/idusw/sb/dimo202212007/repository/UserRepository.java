package idusw.sb.dimo202212007.repository;

import idusw.sb.dimo202212007.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MemberEntity, Integer> {

}
