package cn.qyd.blogroom.user.dao;

import cn.qyd.blogroom.user.entity.Attention;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-26 下午1:11
 **/
public interface AttentionDao extends JpaRepository<Attention, Long> {
    @Query(value = "SELECT attention.user2_id FROM attention WHERE attention.user1_id= ?1", nativeQuery = true)
    List<Long> findIdsByUser1Id(Long user1Id);

    @Query(value = "SELECT attention.user1_id FROM attention WHERE attention.user2_id= ?1", nativeQuery = true)
    List<Long> findIdsByUser2Id(Long user2Id);

    Long countByUser2Id(Long id);

    Attention findByUser1IdAndUser2Id(Long user1Id, Long user2Id);
    void deleteByUser1IdAndUser2Id(Long user1Id, Long user2Id);
    Page<Attention> findAll(Specification<Attention> specification, Pageable pageable);
}
