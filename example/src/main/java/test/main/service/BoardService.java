package test.main.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import test.main.entity.Board;
import test.main.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;

	
	 // 글 작성 처리
    public void write(Board board) {
        boardRepository.save(board); //게시글 저장.
    }
    
    //게시글 리스트 처리
    public Page<Board> list(Pageable pageable){
    	return boardRepository.findAll(pageable);
    }
	
    //특정 게시글 상세보기
    public Board view(Long id) {
    	return boardRepository.findById(id)
    			.orElseThrow(() -> new RuntimeException("없는 id입니다."));
    }
    
    //특정 게시글 삭제
    public void deleteById(Long id) {
    	boardRepository.deleteById(id);
    }
    
    //게시글 찾기
    public Page<Board> searchList(String searchKeyword, Pageable pageable) {
    	return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }
}
