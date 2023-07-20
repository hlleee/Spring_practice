package test.main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import test.main.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	//검색 키워드
	Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);

}
