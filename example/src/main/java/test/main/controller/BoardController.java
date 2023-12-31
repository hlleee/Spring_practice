package test.main.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import test.main.entity.Board;
import test.main.service.BoardService;
import org.springframework.ui.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Setter
@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/write")
	public String write() {
		return "board/write";//board 폴더의 write.html 호출
	}
	
	@GetMapping("/writedo")
	public String writedo(Board board, Model model) {
		
		boardService.write(board);
		
		model.addAttribute("message", "글 작성이 완료되었습니다.");
		model.addAttribute("searchUrl", "/board/list");
		
		return "board/message"; //board 폴더의 meassage 폴더 호출
		
	}
	 
	//게시판 목록.
	//pageable은 가져온 데이터를 페이지화 시키는것.
	//page는 0부터 10개의 데이터 단위로 나눔, Board Entity의 id를 기준으로 분류하고 오름차순으로 지정.
	//아이디를 기준으로 분류한다는게 뭐지?
	 @GetMapping("/list")
	 public String list(Model model,
			 			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			 			String searchKeyword
	) {
		 Page<Board> list = null; //게시판 초기화
		 
		 //searchKeyword가 없으면 select * from Board
		 if (searchKeyword == null) {
			 list = boardService.list(pageable);
			 //->BoardService 클래스 list 함수 코딩 필요.
		 } else {
			 //select * from Board where title=searchKeyword
			 list = boardService.searchList(searchKeyword, pageable);
			//->BoardService 클래스 searchList 함수 코딩 필요
		 }
		 int nowPage = list.getPageable().getPageNumber() + 1;//현재 페이지
		 int startPage = Math.max(nowPage -4, 1);	//시작 페이지
		 int endPage = Math.min(nowPage + 5, list.getTotalPages());	//끝 페이지
		 
		 model.addAttribute("list", list);
		 model.addAttribute("nowPage", nowPage);
		 model.addAttribute("startPage", startPage);
		 model.addAttribute("endPage", endPage);
		 
		 return "board/list";
	 }
	 
	 @GetMapping("/view/{id}") //url에 view/id 번호 입력 혹은 a태그로 view/{id} 호출
	 public String view(Model model, @PathVariable("id") Long id) {
		//@PathVariable은 {변수} 안에 괄호를 열어 URL에 사용될 변수명으로 사용. 
		 
		 model.addAttribute("board", boardService.view(id));
		 return "board/view";
	 }
	 
	 @GetMapping("/delete/{id}")
	 public String delete(@PathVariable("id") Long id) {
		 boardService.deleteById(id);
		 
		 return "redirect:/board/list";  //redirect는 이전 페이지를 다시 호출할 때 사용 없으면 에러
	 }
	 
	 @GetMapping("/modify/{id}") //???
	 public String modify(@PathVariable("id") Long id, Model model) {
		 model.addAttribute("board", boardService.view(id));
		 
		 return "board/modify";
	 }
	 
	 @GetMapping("/update/{id}")
	 public String update(@PathVariable("id") Long id, Board board) {
		 
		 Board boardTemp = boardService.view(id);
		 boardTemp.setTitle(board.getTitle()); //title을 매개변수 board에 담긴 title로 수정
		 boardTemp.setContent(board.getContent());
		 
		 boardService.write(boardTemp);
		 
		 return "redirect:/board/list";
	 }
	 
}


//model에 데이터를 담을 때 addAttribute() 메소드를 사용함.
//사용 방법 
//#1 Model addAttribute(String name, Object value)
//-> value 객체를 name 이름으로 추가한다. 뷰 코드에서는 name으로 지정한 이름을 통해서 value를 사용한다.









