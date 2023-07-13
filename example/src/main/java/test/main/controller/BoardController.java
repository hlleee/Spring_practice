package test.main.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import test.main.entity.Board;
import test.main.service.BoardService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
//	
//	 @PostMapping("/writedo")	//delete,insert,update일 때 postMapping ,,, select는 GetMapping
//	    public String writedo(Board board, Model model) {
//	        boardService.write(board);
//	        
//	    }
	 
	//게시판 목록.
	//pageable은 가져온 데이터를 페이지화 시키는것.
	//page는 0부터 10개의 데이터 단위로 나눔, Board Entity의 id를 기준으로 분류하고 오름차순으로 지정.
	//아이디를 기준으로 분류한다는게 뭐지?
	 @GetMapping("/list")
	 public String list(Model model,
			 			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageble,
			 			String searchKeyword
	) {
		 Page<Board> list = null; //게시판 초기화
		 
		 //searchKeyword가 없으면 select * from Board
		 if (searchKeyword == null) {
			 list = boardService.list(pageable); 
			 //->BoardService 클래스 list 함수 코딩 필요.
		 } else {
			 //select * from Board where title=searchKeyword
			 list = boardService.searchList(searchKeyword, pageable)
			//->BoardService 클래스 searchList 함수 코딩 필요
		 }
	 }
	 int nowPage = list.getPageable().getPageNumber() + 1;
	 int startPage = Math.max(nowPage -4, 1);
	 int endPage = Math.min(nowPage + 5, list.getTotalPages());
	 
	 model.addAttribute("list",list);
}










