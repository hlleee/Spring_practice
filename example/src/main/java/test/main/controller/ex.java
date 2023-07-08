package test.main.controller;

public class ex {

}
//url 타고 들어갈 때
//localhost/board/list -> 게시판 메인 페이지
//localhost/board/write -> 게시판 작성 페이지
//
//
//코딩 순서 
//먼저 프론트엔드 html부터 복붙 -> Boardcontroller 코딩 
//getMapping(
//list -> write -> writedo -> view/{id} -> delete/{id},modify/{id} -> update/{id} 순으로 코딩
//
//
//
//package com.example.demo.board.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.example.demo.board.entity.Board;
//import com.example.demo.board.repository.BoardRepository;
//import com.example.demo.board.service.BoardService;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Controller
//@RequestMapping("/board")
//@RequiredArgsConstructor
//public class BoardController {
//	 private final BoardService boardService;
//
//	    @GetMapping("/write") //url에 board/write입력시
//	    public String write() {
//	        return "board/write"; //board폴더의 write.html 호출
//	    }
//
//
//	    @PostMapping("/writedo") //url에 board/writedo 입력시 혹은 a태그 등으로 호출 시
//	    public String writedo(Board board, Model model) {
//	    	
//	        boardService.write(board);
//	        
//	        model.addAttribute("message", "글 작성이 완료되었습니다.");
//	        model.addAttribute("searchUrl", "/board/list");
//
//	        return "board/message"; //board 폴더의 message폴더 호출
//	    }
//
//	    /**
//	     * pageable은 가져온데이터를 페이지화 시키는 것
//	     * page는 0부터 10개의 데이터단위로 나눔, Board Entity의 id를 기준으로 분류하고 오름차순으로 지정
//	     * 
//	     * @param model
//	     * @param pageable
//	     * @param searchKeyword
//	     * @return
//	     */
//	    @GetMapping("/list") 
//	    public String list(Model model,
//	                       @PageableDefault(page = 1, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
//	                       String searchKeyword
//	    ) {
//	        Page<Board> list = null;
//
//	        //searchKeyword가 없으면 select * from Board
//	        if (searchKeyword == null) {
//	            list = boardService.list(pageable);// ->BoardService 클래스 list 함수 코딩필요
//	        } else {
//	        	//select * from Board where title=searchKeyword
//	            list = boardService.searchList(searchKeyword, pageable); // ->BoardService 클래스 searchList 함수 코딩필요
//	        }
//
//	        int nowPage = list.getPageable().getPageNumber() + 1; //현재 페이지 
//	        int startPage = Math.max(nowPage - 4, 1); //시작 페이지
//	        int endPage = Math.min(nowPage + 5, list.getTotalPages()); //끝 페이지
//
//	        model.addAttribute("list", list);
//	        model.addAttribute("nowPage", nowPage);
//	        model.addAttribute("startPage", startPage);
//	        model.addAttribute("endPage", endPage);
//
//	        return "board/list";
//	    }
//
//	    @GetMapping("/view/{id}")// url에 view/id번호 입력 혹은 a태그로 view/{id} 호출
//	    public String view(Model model, @PathVariable("id") Long id) {
//	        model.addAttribute("board", boardService.view(id));
//	        return "board/view";
//	    }
//
//	    @GetMapping("/delete/{id}")//url에 delete/id번호 입력 혹은 a태그로 delete/{id} 호출
//	    public String delete(@PathVariable("id") Long id) {
//	        boardService.deleteById(id);//135
//
//	        return "redirect:/board/list"; //redirect는 이전 페이지를 다시 호출할 때 사용 없으면 에러
//	    }
//
//	    @GetMapping("/modify/{id}")//url에 modify/id번호 입력 혹은 a태그로 modify/{id} 호출
//	    public String modify(@PathVariable("id") Long id,
//	                         Model model) {
//	        model.addAttribute("board", boardService.view(id));
//
//	        return "board/modify";
//	    }
//
//	    @PostMapping("/update/{id}")
//	    public String update(@PathVariable("id") Long id, Board board) {
//
//	        Board boardTemp = boardService.view(id); 
//	        boardTemp.setTitle(board.getTitle()); //title을 매개변수 board에 담긴 title로 수정
//	        boardTemp.setContent(board.getContent());
//
//	        boardService.write(boardTemp);
//
//	        return "redirect:/board/list";
//	    }
//}
//
