package test.main.service;

import test.main.entity.Board;

public class BoardService {
	 // 글 작성 처리
    public void write(Board board) {
        boardRepository.save(board);
    }
	

}
